package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packEuskoFlix.FilmCatalogue;
import packEuskoFlix.RatingCatalogue;
import packEuskoFlix.VectorInteger;

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
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class MainMenu extends JFrame {

	private JPanel CentralPanel;
	private final JLabel lblEuskoflix = new JLabel(new ImageIcon("Logo.png"));
	private JTextField UserIDText;


	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setResizable(false);
		FilmCatalogue filmCatalogue = FilmCatalogue.getFilmCatalogue();
		filmCatalogue.initializeTitles();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		CentralPanel = new JPanel();
		CentralPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(CentralPanel);
		CentralPanel.setLayout(new BorderLayout(0, 0));
		lblEuskoflix.setBackground(Color.BLACK);
		lblEuskoflix.setFont(new Font("Dialog", Font.BOLD, 24));
		CentralPanel.add(lblEuskoflix, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		CentralPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		JLabel UserID = new JLabel("User ID=");
		panel_1.add(UserID);
		
		UserIDText = new JTextField();
		UserIDText.setEditable(false);
		panel_1.add(UserIDText);
		UserIDText.setColumns(10);
		
		JButton btnRecommend = new JButton("RECOMMEND");
		panel_1.add(btnRecommend);
		

		JButton btnNormalized = new JButton("Not normalized");
		btnNormalized.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(btnNormalized);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JComboBox ValorationMode = new JComboBox();
		ValorationMode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ValorationMode.setToolTipText("Select the filter mode, then press change.");
		panel_3.add(ValorationMode, BorderLayout.CENTER);
		ValorationMode.addItem("Filter by user");
		ValorationMode.addItem("Filter by content");
		ValorationMode.addItem("Filter by product");
		
		JButton btnNewButton = new JButton("Change");
		btnNewButton.setToolTipText("Press it to change the filter type");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectFilterType((String) ValorationMode.getSelectedItem());
			}
		});
		panel_3.add(btnNewButton, BorderLayout.EAST);
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea FilmsTextArea = new JTextArea();
		FilmsTextArea.setFont(new Font("Arial", Font.BOLD, 16));
		FilmsTextArea.setForeground(Color.WHITE);
		FilmsTextArea.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(FilmsTextArea);
		FilmsTextArea.setEditable(false);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_4.add(panel_5, BorderLayout.WEST);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] {113};
		gbl_panel_5.rowHeights = new int[] {0, 30, 30, 0, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl_panel_5.columnWeights = new double[]{0.0};
		gbl_panel_5.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel_5.setLayout(gbl_panel_5);
		
		JButton exit = new JButton("Exit");
		exit.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		
		JButton showFilms = new JButton("Show Films");
		showFilms.setFont(new Font("Tahoma", Font.PLAIN, 22));
		GridBagConstraints gbc_showFilms = new GridBagConstraints();
		gbc_showFilms.insets = new Insets(0, 0, 5, 0);
		gbc_showFilms.gridx = 0;
		gbc_showFilms.gridy = 0;
		panel_5.add(showFilms, gbc_showFilms);
		showFilms.setToolTipText("");
		
		JButton btnChangeUser = new JButton("Change User");
		btnChangeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				getFrames()[0].setVisible(true);
				getFrames()[1].setVisible(false);
				
			}
		});
		
		GridBagConstraints gbc_btnChangeUser = new GridBagConstraints();
		gbc_btnChangeUser.fill = GridBagConstraints.VERTICAL;
		gbc_btnChangeUser.insets = new Insets(0, 0, 5, 0);
		gbc_btnChangeUser.gridx = 0;
		gbc_btnChangeUser.gridy = 2;
		panel_5.add(btnChangeUser, gbc_btnChangeUser);
		GridBagConstraints gbc_exit = new GridBagConstraints();
		gbc_exit.gridx = 0;
		gbc_exit.gridy = 16;
		panel_5.add(exit, gbc_exit);
		
		JLabel lblFilms = new JLabel("Films");
		lblFilms.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblFilms, BorderLayout.NORTH);
		
		//Button RecommendFilm
		btnRecommend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				RatingCatalogue ratings=RatingCatalogue.getRatingCatalogue();
				VectorInteger recommendedFilmTitles = ratings.recommendFilm(Integer.parseInt(UserIDText.getText()));
				String recommendedFilmTitlesText = FilmCatalogue.getFilmCatalogue().obtainFilmTitlesFromVector(recommendedFilmTitles);
				FilmsTextArea.setText(recommendedFilmTitlesText);
			};
		});
		
		btnNormalized.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnNormalized.getText().equals("Normalized")) {
					btnNormalized.setText("Not normalized");
					System.out.println("Not normalized");
					RatingCatalogue.getRatingCatalogue().changeToNonNormalized();
				}else {
					btnNormalized.setText("Normalized");
					System.out.println("Normalized");
					RatingCatalogue.getRatingCatalogue().changeToNormalize();
				}
			};
		});
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		showFilms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String FilmTitlesText = filmCatalogue.getFilmCatalogue().getAllFilmsTitles();
				FilmsTextArea.setText(FilmTitlesText);
				FilmsTextArea.setCaretPosition(0);
			}
		});
	}
	
	public void setUser(String pUser) {
		UserIDText.setText(pUser);
		
	}	
	
	private void selectFilterType(String pFilter) {
		if (pFilter.equals("Filter by user")) {
			System.out.println("User");
			RatingCatalogue.getRatingCatalogue().changeValorationMode("userfilter");
		}else if(pFilter.equals("Filter by content")){
			System.out.println("Content");
			RatingCatalogue.getRatingCatalogue().changeValorationMode("contentfilter");
		}
		else {
			System.out.println("Product");
			RatingCatalogue.getRatingCatalogue().changeValorationMode("productfilter");
			
		}
		
	}


}
