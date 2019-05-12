package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packEuskoFlix.RatingCatalogue;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class LoginMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	/**
	 * Create the frame.
	 */
	public LoginMenu() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(395, 153, 295, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblUserId = new JLabel("User Id");
		lblUserId.setBounds(329, 156, 69, 20);
		panel.add(lblUserId);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(395, 237, 395, 217);
		panel.add(lblNewLabel);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu menu = (MainMenu) getFrames()[1];				
				if (textField.getText()!=null && RatingCatalogue.getRatingCatalogue().getAllUsers().contains(Integer.parseInt(textField.getText()))) {
					menu.setVisible(true);
					getFrames()[0].setVisible(false);
					menu.setUser(textField.getText());
					lblNewLabel.setText("");
				}else {
					textField.setText("");
					lblNewLabel.setText("Invalid user Id try again");
				};				
				
			}
		});
		
		btnLogIn.setBounds(733, 139, 149, 50);
		panel.add(btnLogIn);
		
		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("Logo.png"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblNewLabel_1, BorderLayout.NORTH);
		

		
		javax.swing.ImageIcon Imagen = new javax.swing.ImageIcon("/home/lsi/Descargas/a.png");
		
		
	}
}
