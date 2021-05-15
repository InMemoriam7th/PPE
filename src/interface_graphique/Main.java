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

public class Main{
	
	private GestionPersonnel gestionPersonnel;
	private Employe employe;
	private JFrame root_frame = new JFrame();
	private JPanel main_frame = new JPanel();
	
	
	
	
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
		JLabel title = new JLabel("Gestion Des Ligues");
		title.setFont(new Font("Verdana", Font.PLAIN, 25));
		item_frame.add(title);
		return item_frame;
	}
	
	private JPanel message() {
		JPanel item_frame = item_frame();
		item_frame.add(new JLabel("Bienvenue dans votre espace " + employe.getNom()));
		return item_frame;
	}
	
	private JPanel button_ligue() {
		JPanel item_frame = item_frame();
		JButton button_ligue = new JButton("Gérer les ligues");
		button_ligue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Display_Ligue(gestionPersonnel, employe);	
			}
		});
		button_ligue.setMaximumSize(new Dimension(170, 40));
		item_frame.add(button_ligue);
		return item_frame;
	}
	
	private JPanel button_root() {
		JPanel item_frame = item_frame();
		JButton button_root = new JButton("Gérer le compte root");
		if(employe.estRoot()) {
		button_root.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Selected root");
				
			}
		});
		button_root.setMaximumSize(new Dimension(170, 40));
		item_frame.add(button_root);
		}
		return item_frame;
	}
	
}
