package interfaces;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class SearchInterface extends JPanel {
	
	private BufferedImage searchImage;
	private Color backgroundColor = new Color (255, 229, 204);
	private JTextField textName, textSurname, textTelf, textMov, textEmail, textAddress, textCity, textCountry;
	private JLabel lblName, lblSurname, lblTelf, lblMob, lblEmail, lblAddress, lblCity, lblCountry, lblNotes;
	private JTextPane textNotes;


	/**
	 * Create the panel.
	 */
	public SearchInterface() {
		this.setLayout(null);
		try {
			searchImage = ImageIO.read(new File("res/search.png"));
		}catch (IOException e) {
			e.printStackTrace();
		}
		setOpaque(true);
		this.setBounds(0, 0, 500, 700);
		this.setBackground(backgroundColor);
		
		JLabel labelImage = new JLabel(new ImageIcon(searchImage));
		labelImage.setSize(400, 100);
		labelImage.setLocation(50, 10);
		//labelImage.setBounds(10, 11, 46, 37);
		add(labelImage);
		
		lblName = new JLabel("NAME");
		lblName.setBounds(20, 220, 50, 15);
		add(lblName);
		
		lblSurname = new JLabel("SURNAME");
		lblSurname.setBounds(250, 220, 90, 15);
		add(lblSurname);
		
		lblTelf = new JLabel("TELF");
		lblTelf.setBounds(20, 280, 90, 15);
		add(lblTelf);
		
		lblMob = new JLabel("MOBILE");
		lblMob.setBounds(250, 280, 90, 15);
		add(lblMob);
		
		lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(20, 340, 90, 15);
		add(lblEmail);
		
		lblAddress = new JLabel("ADDRESS");
		lblAddress.setBounds(20, 400, 90, 15);
		add(lblAddress);
		
		lblCity = new JLabel("CITY");
		lblCity.setBounds(20, 460, 90, 15);
		add(lblCity);
		
		lblCountry = new JLabel("COUNTRY");
		lblCountry.setBounds(250, 460, 90, 15);
		add(lblCountry);
		
		lblNotes = new JLabel("NOTES");
		lblNotes.setBounds(20, 520, 90, 15);
		add(lblNotes);
		
		textName = new JTextField();
		textName.setBounds(20, 240, 200, 20);
		add(textName);
		textName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textName.setColumns(10);
		
		textSurname = new JTextField();
		textSurname.setBounds(250, 240, 200, 20);
		add(textSurname);
		textSurname.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textSurname.setColumns(10);
		
		textTelf = new JTextField();
		textTelf.setBounds(20, 300, 200, 20);
		add(textTelf);
		textTelf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textTelf.setColumns(10);
		
		textMov = new JTextField();
		textMov.setBounds(250, 300, 200, 20);
		add(textMov);
		textMov.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textMov.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(20, 360, 300, 20);
		add(textEmail);
		textEmail.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textEmail.setColumns(10);
		
		textAddress = new JTextField();
		textAddress.setBounds(20, 420, 300, 20);
		add(textAddress);
		textAddress.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textAddress.setColumns(10);
		
		textCity = new JTextField();
		textCity.setBounds(20, 480, 200, 20);
		add(textCity);
		textCity.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textCity.setColumns(10);
		
		textCountry = new JTextField();
		textCountry.setBounds(250, 480, 200, 20);
		add(textCountry);
		textCountry.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textCountry.setColumns(10);
		
		textNotes = new JTextPane();
		textNotes.setBounds(20, 540, 430, 72);
		textNotes.setAutoscrolls(true);
		textNotes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(textNotes);		
		
		
		//this.add(interfacePanel);

		loadPanel();
	}
	
	public void loadPanel() {
	
	}
}
