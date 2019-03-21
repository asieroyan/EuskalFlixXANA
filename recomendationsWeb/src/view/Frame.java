package view;

import java.util.HashMap;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packEuskoFlix.Film;
import packEuskoFlix.FilmCatalogue;
import packEuskoFlix.Ratings;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class Frame extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Create the frame.
	 */
	public Frame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1036, 1285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPeliculas = new JLabel("Peliculas");
		lblPeliculas.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
		lblPeliculas.setBounds(32, 16, 312, 59);
		contentPane.add(lblPeliculas);
		lblPeliculas.setVisible(true);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBox.setBounds(32, 91, 619, 26);
		contentPane.add(comboBox);
		
		FilmCatalogue films = FilmCatalogue.getFilmCatalogue();
		Film[] myFilms=films.getList().getHashMap().values().toArray(new Film[films.getList().size()]);
		int size=myFilms.length;
		for (int i=0;i<size;i++) {
			comboBox.addItem(myFilms[i].getName());
		}
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnBuscar.setBounds(669, 90, 115, 29);
		contentPane.add(btnBuscar);
		
		JScrollPane tagScrollPane = new JScrollPane();
		tagScrollPane.setBounds(59, 393, 214, 270);
		contentPane.add(tagScrollPane);
		
		JTextArea ratings = new JTextArea();
		ratings.setFont(new Font("Arial", Font.PLAIN, 16));
		ratings.setColumns(10);
		tagScrollPane.setViewportView(ratings);
		
		JLabel lblDatos = new JLabel("Ratings");
		lblDatos.setFont(new Font("Arial", Font.BOLD, 15));
		tagScrollPane.setColumnHeaderView(lblDatos);
		
		JTextArea users = new JTextArea();
		users.setFont(new Font("Arial", Font.PLAIN, 16));
		tagScrollPane.setRowHeaderView(users);
		
		JScrollPane RatingscrollPane = new JScrollPane();
		RatingscrollPane.setBounds(289, 393, 655, 270);
		contentPane.add(RatingscrollPane);
		
		JTextArea tags = new JTextArea();
		tags.setBounds(0, 0, 210, 250);
		tags.setFont(new Font("Arial", Font.PLAIN, 16));
		RatingscrollPane.setViewportView(tags);
		
		JLabel lblTags = new JLabel("Tags");
		lblTags.setFont(new Font("Arial", Font.BOLD, 15));
		RatingscrollPane.setColumnHeaderView(lblTags);
		
		JLabel lblIdFilm = new JLabel("Id pelicula");
		lblIdFilm.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 17));
		lblIdFilm.setBounds(669, 130, 115, 26);
		contentPane.add(lblIdFilm);
		

		

		
		btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {//When buscar clicked            	
            	String film = comboBox.getSelectedItem().toString();
            	//Search for the film
            	Film myFilm = FilmCatalogue.getFilmCatalogue().getList().getByName(film);            	
            	Ratings myRatings = Ratings.getRatings();
            	tags.setText(myFilm.allTags());
            	ratings.setText(myRatings.allRatings(myFilm.getID()));
            	users.setText(myRatings.allUsers(myFilm.getID()));
            	lblIdFilm.setText(myFilm.getID().toString());
            	
            };
		});			
		
		

	}
}
