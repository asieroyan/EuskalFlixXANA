package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packEuskoFlix.FilmCatalogue;
import packEuskoFlix.RatingCatalogue;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingConstants;

public class MainMenu extends JFrame implements Observer {

	private JPanel CentralPanel;
	private final JLabel lblEuskoflix = new JLabel("EUSKOFLIX");
	private JTextField UserIDText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		FilmCatalogue filmCatalogue = FilmCatalogue.getFilmCatalogue();
		filmCatalogue.initializeTitles();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		CentralPanel = new JPanel();
		CentralPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(CentralPanel);
		CentralPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		CentralPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		JLabel UserID = new JLabel("User ID=");
		panel_1.add(UserID);
		
		UserIDText = new JTextField();
		panel_1.add(UserIDText);
		UserIDText.setColumns(10);
		
		JButton btnRecommend = new JButton("RECOMMEND");
		panel_1.add(btnRecommend);
		
		JButton btnNormalized = new JButton("Normalized");
		btnNormalized.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(btnNormalized);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JComboBox ValorationMode = new JComboBox();
		panel_3.add(ValorationMode, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane, BorderLayout.CENTER);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JList list_1 = new JList();
		scrollPane.setRowHeaderView(list_1);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.WEST);
		panel_5.setLayout(new GridLayout(3, 1, 0, 2));
		
		JButton btnNewButton = new JButton("Show Films");
		panel_5.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Settings");
		panel_5.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		panel_5.add(btnNewButton_2);
		
		JLabel lblFilms = new JLabel("Films");
		lblFilms.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblFilms, BorderLayout.NORTH);
		lblEuskoflix.setFont(new Font("Dialog", Font.BOLD, 20));
		CentralPanel.add(lblEuskoflix, BorderLayout.NORTH);
		
		//Button RecommendFilm
		btnRecommend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//chckbxNormalized.get
				//RatingCatalogue.getRatingCatalogue().changeNormalizeMode(); //cambia el modo
				RatingCatalogue.getRatingCatalogue().recommendFilm(Integer.parseInt(UserIDText.getText()));
			};
		});
	}
	
	

	@Override
	public void update(Observable o, Object arg) {
		
	}

}
