package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class LoginMenu extends JFrame {

	private JPanel contentPane;
	private JTextField txtIntroduceYourId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMenu frame = new LoginMenu();
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
	public LoginMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblEuskoflix = new JLabel("EUSKOFLIX");
		lblEuskoflix.setFont(new Font("Dialog", Font.BOLD, 20));
		contentPane.add(lblEuskoflix, "4, 2, 3, 1");
		
		JButton btnEnglish = new JButton("Settings");
		contentPane.add(btnEnglish, "6, 4");
		
		JLabel lblIntroduceYourId = new JLabel("Introduce your ID");
		contentPane.add(lblIntroduceYourId, "4, 10");
		
		txtIntroduceYourId = new JTextField();
		txtIntroduceYourId.setToolTipText("Introduce your ID");
		contentPane.add(txtIntroduceYourId, "4, 12, 3, 1, fill, default");
		txtIntroduceYourId.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		contentPane.add(btnSubmit, "6, 14");
		JPanel panel = new JPanel();
		contentPane.add(panel, "4, 18, 3, 1, fill, fill");
		
		javax.swing.ImageIcon Imagen = new javax.swing.ImageIcon("/home/lsi/Descargas/a.png");
		javax.swing.JLabel Img = new javax.swing.JLabel(Imagen);
		Img.setSize(120, 160);
		panel.add(Img);
		
		
		
	}

}
