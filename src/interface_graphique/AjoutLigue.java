package interface_graphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.JTextField;

public class AjoutLigue {// implements ActionListener{
	private JFrame root_frame = new JFrame();
	private JPanel main_frame = new JPanel();
	private JLabel titre = new JLabel("Ajouter une ligue.");
	private JLabel ligue_label = new JLabel("Entrer le nom de la ligue : ");
	private JTextField ligue = new JTextField();
	private JButton valider = new JButton("valider");
	private JButton retour = new JButton("retour");
	
	public AjoutLigue() {
		Root_frame();
		Main_frame();
	}

	private void Root_frame() {
		root_frame.setSize(300,300);
		root_frame.setVisible(true);
		root_frame.getContentPane().add(main_frame);
		root_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void Main_frame() {
		main_frame.setLayout(new BoxLayout(main_frame, BoxLayout.PAGE_AXIS));
		main_frame.add(titre());
		main_frame.add(ligue());
		main_frame.add(validerRetour());
	}
	private JPanel item_frame() {
		JPanel item_frame = new JPanel();
		item_frame.setLayout(new BoxLayout(item_frame, BoxLayout.LINE_AXIS));
		return item_frame;
	}
	private JPanel titre() {
		JPanel item_frame = item_frame();
		item_frame.add(titre);
		return item_frame;
	}
	private JPanel ligue() {
		JPanel item_frame = item_frame();
		ligue.setMaximumSize(new Dimension(150, 20));
		item_frame.add(ligue_label);
		item_frame.add(ligue);
		return item_frame;
	}
	private JPanel validerRetour() {
		JPanel item_frame = item_frame();
	//	valider.addActionListener(this);
	//  retour.addActionListener(this);
		item_frame.add(valider);
		item_frame.add(retour);
		return item_frame;
	}
	public static void main(String[] args) {
		new AjoutLigue();
	}
}
