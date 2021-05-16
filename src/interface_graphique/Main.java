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
	private JFrame rootframe = new JFrame();
	private JPanel mainframe = new JPanel();
	
	
	public Main(GestionPersonnel gestionPersonnel, Employe employe){
		this.gestionPersonnel = gestionPersonnel;
		this.employe = employe;
		RootFrame();
		MainFrame();
	}
	
	private void RootFrame() {
		rootframe.setSize(400,200);
		rootframe.setVisible(true);
		rootframe.getContentPane().add(mainframe);
		rootframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rootframe.setLocationRelativeTo(null);
	}
	
	private void MainFrame() {
		mainframe.setLayout(new BoxLayout(mainframe, BoxLayout.PAGE_AXIS));
		mainframe.add(title());
		mainframe.add(Message());
		mainframe.add(ButtonLigue());
		mainframe.add(ButtonRoot());
	}
	
	private JPanel ItemFrame() {
		JPanel item_frame = new JPanel();
		item_frame.setLayout(new BoxLayout(item_frame, BoxLayout.LINE_AXIS));
		return item_frame;
	}
	
	private JPanel title() {
		JPanel itemframe = ItemFrame();
		JLabel title = new JLabel("Gestion Des Ligues");
		title.setFont(new Font("Verdana", Font.PLAIN, 25));
		itemframe.add(title);
		return itemframe;
	}
	
	private JPanel Message() {
		JPanel itemframe = ItemFrame();
		itemframe.add(new JLabel("Bienvenue dans votre espace " + employe.getNom()));
		return itemframe;
	}
	
	private JPanel ButtonLigue() {
		JPanel itemframe = ItemFrame();
		JButton buttonligue = new JButton("Gérer les ligues");
		buttonligue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DisplayLigue(gestionPersonnel, employe);	
			}
		});
		buttonligue.setMaximumSize(new Dimension(170, 40));
		itemframe.add(buttonligue);
		return itemframe;
	}
	
	private JPanel ButtonRoot() {
		JPanel itemframe = ItemFrame();
		JButton buttonroot = new JButton("Gérer le compte root");
		if(employe.estRoot()) {
		buttonroot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Selected root");
				
			}
		});
		buttonroot.setMaximumSize(new Dimension(170, 40));
		itemframe.add(buttonroot);
		}
		return itemframe;
	}
	
}
