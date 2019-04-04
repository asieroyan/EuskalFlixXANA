package view;

import java.util.HashMap;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controler.Film;
import controler.FilmCatalogue;

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
		comboBox.setVisible(true);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnBuscar.setBounds(669, 90, 115, 29);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(123, 393, 709, 720);
		contentPane.add(scrollPane);
		
		JTextArea ratings = new JTextArea();
		ratings.setFont(new Font("Arial", Font.PLAIN, 16));
		ratings.setColumns(10);
		scrollPane.setRowHeaderView(ratings);
		
		
		JTextArea tags = new JTextArea();
		tags.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPane.setViewportView(tags);
		tags.setEditable(false);
		
		JLabel lblNewLabel = new JLabel("New label");
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		JLabel lblDatos = new JLabel("Datos");
		scrollPane.setColumnHeaderView(lblDatos);

		
		btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	String film = comboBox.getSelectedItem().toString();
            	//Search for the film
            	Film myFilm = FilmCatalogue.getFilmCatalogue().getList().getByName(film);            	
            	//To do
            	tags.setText(myFilm.allTags());
            	ratings.setText(myFilm.allRatings());
            	
            };
		});			
		
		

	}
}