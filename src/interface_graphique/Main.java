package interface_graphique;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import personnel.Employe;
import personnel.GestionPersonnel;

public class Main implements ActionListener{
	
	private GestionPersonnel gestionPersonnel;
	private Employe employe;
	private JFrame root_frame = new JFrame();
	private JPanel main_frame = new JPanel();
	private JLabel title = new JLabel("Gestion Des Ligues");
	private JLabel message = new JLabel();
	private JButton button_ligue = new JButton("Gérer les ligues");
	private JButton button_root = new JButton("Gérer le compte root");
	
	
	public Main(GestionPersonnel gestionPersonnel, Employe employe){
		this.gestionPersonnel = gestionPersonnel;
		this.employe = employe;
		Root_frame();
		Main_frame();
	}
	
	private void Root_frame() {
		root_frame.setSize(400,200);
		root_frame.setVisible(true);
		root_frame.getContentPane().add(main_frame);
		root_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		root_frame.setLocationRelativeTo(null);
	}
	
	private void Main_frame() {
		main_frame.setLayout(new BoxLayout(main_frame, BoxLayout.PAGE_AXIS));
		main_frame.add(title());
		main_frame.add(message());
		main_frame.add(button_ligue());
		main_frame.add(button_root());
	}
	
	private JPanel item_frame() {
		JPanel item_frame = new JPanel();
		item_frame.setLayout(new BoxLayout(item_frame, BoxLayout.LINE_AXIS));
		return item_frame;
	}
	
	private JPanel title() {
		JPanel item_frame = item_frame();
		title.setFont(new Font("Verdana", Font.PLAIN, 25));
		item_frame.add(title);
		return item_frame;
	}
	
	private JPanel message() {
		JPanel item_frame = item_frame();
		message.setText("Bienvenue dans votre espace " + employe.getNom());
		item_frame.add(message);
		return item_frame;
	}
	
	private JPanel button_ligue() {
		JPanel item_frame = item_frame();
		button_ligue.addActionListener(this);
		button_ligue.setMaximumSize(new Dimension(170, 40));
		button_ligue.setName("ligue");
		item_frame.add(button_ligue);
		return item_frame;
	}
	
	private JPanel button_root() {
		JPanel item_frame = item_frame();
		if(employe.estRoot()) {
		button_root.addActionListener(this);
		button_root.setMaximumSize(new Dimension(170, 40));
		button_root.setName("root");
		item_frame.add(button_root);
		}
		return item_frame;
	}
	

	 @Override
	 public void actionPerformed(ActionEvent e)
	 {

	  JButton button = (JButton) e.getSource();
	  switch (button.getName()) {
	  case "ligue":new Display_Ligue(gestionPersonnel, employe);break;
	  case "root":System.out.println("Selected root");break;
	}
	 }
}
