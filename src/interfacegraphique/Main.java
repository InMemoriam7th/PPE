package interfacegraphique;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		rootframe.setSize(400,220);
		rootframe.setVisible(true);
		rootframe.setResizable(false);
		rootframe.getContentPane().add(mainframe);
		rootframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rootframe.setTitle("Gestion Des Ligues");
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
		itemframe.setBorder(new EmptyBorder(10, 0, 10, 0));
		return itemframe;
	}
	
	private JPanel ButtonLigue() {
		JPanel itemframe = ItemFrame();
		JButton buttonligue = new JButton("G�rer les ligues");
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
		JButton buttonroot = new JButton("G�rer le compte root");
		if(employe.estRoot()) {
		buttonroot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EditRoot(gestionPersonnel, employe);
				
			}
		});
		buttonroot.setMaximumSize(new Dimension(170, 40));
		itemframe.add(buttonroot);
		}
		return itemframe;
	}
	
}
