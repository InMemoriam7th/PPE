package interface_graphique;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;

public class Display_Ligue{
	
	private GestionPersonnel gestionPersonnel;
	private Employe employe;

	private JFrame root_frame = new JFrame();
	private JPanel main_frame = new JPanel();
	
	private DefaultListModel listModel = new DefaultListModel();
	private JList list = new JList(listModel);
	
	public Display_Ligue(GestionPersonnel gestionPersonnel, Employe employe) {
		this.gestionPersonnel = gestionPersonnel;
		this.employe = employe;
		Main_frame();
		Root_frame();
	}
	
	private void Root_frame() {
		root_frame.setSize(600,500);
		root_frame.setVisible(true);
		root_frame.getContentPane().add(main_frame);
		root_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		root_frame.setLocationRelativeTo(null);
	}
	
	private void Main_frame() {
		main_frame.setLayout(new BoxLayout(main_frame, BoxLayout.PAGE_AXIS));
		main_frame.add(title());
		main_frame.add(list());
		main_frame.add(button_addligue());
		main_frame.add(button_valider());
	}
	
	private JPanel item_frame() {
		JPanel item_frame = new JPanel();
		item_frame.setLayout(new BoxLayout(item_frame, BoxLayout.LINE_AXIS));
		return item_frame;
	}
	
	private JPanel title() {
		JPanel item_frame = item_frame();
		JLabel title = new JLabel("Selectionner une ligue");
		title.setFont(new Font("Verdana", Font.PLAIN, 20));
		title.setBorder(new EmptyBorder(10, 0, 20, 0));
		item_frame.add(title);
		return item_frame;
	}
	
	public void generate_liste() {
		listModel.removeAllElements();
		SortedSet<Ligue> list_ligue = gestionPersonnel.getLigues();
		for(Ligue ligue : list_ligue) {
			listModel.addElement(ligue);
		}
		
	}
	
	private JPanel list() {
		generate_liste();
		JPanel item_frame = item_frame();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		item_frame.add(list);
		item_frame.setMaximumSize(new Dimension(200, 250));
		JScrollPane listScrollPane = new JScrollPane(list);
		item_frame.add(listScrollPane);
		return item_frame;
	}
	
	private JPanel button_addligue() {
		JPanel item_frame = item_frame();
		JButton add_ligue = new JButton("Ajouter une ligue");
		add_ligue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add_ligue();
				
			}
		});
		if(!employe.estRoot())
			add_ligue.setEnabled(false);
		item_frame.add(add_ligue);
		item_frame.setBorder(new EmptyBorder(20, 0, 20, 0));
		return item_frame;
	}
	
	private JPanel button_valider() {
		JPanel item_frame = item_frame();
		JButton valider = new JButton("Sélectionner une ligue");
		valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				valider();
				
			}
		});
		item_frame.add(valider);
		return item_frame;
	}
	
	
	private void add_ligue() {
		new AjoutLigue(gestionPersonnel, this);
	}
	
	private void valider() {
		if(list.getSelectedValue() != null) {
			new Ligue_edit(gestionPersonnel, employe, (Ligue) list.getSelectedValue(), this);
		}else{
			JOptionPane message_erreur = new JOptionPane();
			message_erreur.showMessageDialog(root_frame, "Vous n'avez pas sélectionné de ligue", "Erreur" ,JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	

}
