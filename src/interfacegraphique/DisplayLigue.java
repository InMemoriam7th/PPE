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

public class DisplayLigue{
	
	private GestionPersonnel gestionPersonnel;
	private Employe employe;

	private JFrame rootframe = new JFrame();
	private JPanel mainframe = new JPanel();
	
	private DefaultListModel listModel = new DefaultListModel();
	private JList list = new JList(listModel);
	
	public DisplayLigue(GestionPersonnel gestionPersonnel, Employe employe) {
		this.gestionPersonnel = gestionPersonnel;
		this.employe = employe;
		MainFrame();
		RootFrame();
	}
	
	private void RootFrame() {
		rootframe.setSize(600,500);
		rootframe.setVisible(true);
		rootframe.getContentPane().add(mainframe);
		rootframe.setLocationRelativeTo(null);
		rootframe.setTitle("Ligues");
	}
	
	private void MainFrame() {
		mainframe.setLayout(new BoxLayout(mainframe, BoxLayout.PAGE_AXIS));
		mainframe.add(Title());
		mainframe.add(List());
		mainframe.add(ButtonAddligue());
		mainframe.add(ButtonValider());
	}
	
	private JPanel ItemFrame() {
		JPanel itemframe = new JPanel();
		itemframe.setLayout(new BoxLayout(itemframe, BoxLayout.LINE_AXIS));
		return itemframe;
	}
	
	private JPanel Title() {
		JPanel itemframe = ItemFrame();
		JLabel title = new JLabel("Selectionner une ligue");
		title.setFont(new Font("Verdana", Font.PLAIN, 20));
		title.setBorder(new EmptyBorder(10, 0, 20, 0));
		itemframe.add(title);
		return itemframe;
	}
	
	public void GenerateListe() {
		listModel.removeAllElements();
		SortedSet<Ligue> listligue = gestionPersonnel.getLigues();
		for(Ligue ligue : listligue) {
			listModel.addElement(ligue);
		}
		
	}
	
	private JPanel List() {
		GenerateListe();
		JPanel itemframe = ItemFrame();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemframe.add(list);
		itemframe.setMaximumSize(new Dimension(200, 250));
		JScrollPane listScrollPane = new JScrollPane(list);
		itemframe.add(listScrollPane);
		return itemframe;
	}
	
	private JPanel ButtonAddligue() {
		JPanel itemframe = ItemFrame();
		JButton addligue = new JButton("Ajouter une ligue");
		addligue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AddLigue();
				
			}
		});
		if(!employe.estRoot())
			addligue.setEnabled(false);
		itemframe.add(addligue);
		itemframe.setBorder(new EmptyBorder(20, 0, 20, 0));
		return itemframe;
	}
	
	private JPanel ButtonValider() {
		JPanel itemframe = ItemFrame();
		JButton valider = new JButton("Sélectionner une ligue");
		valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Valider();
				
			}
		});
		itemframe.add(valider);
		return itemframe;
	}
	
	
	private void AddLigue() {
		new AjoutLigue(gestionPersonnel, this);
	}
	
	private void Valider() {
		if(list.getSelectedValue() != null) {
			new LigueEdit(gestionPersonnel, employe, (Ligue) list.getSelectedValue(), this);
		}else{
			JOptionPane messageerreur = new JOptionPane();
			messageerreur.showMessageDialog(rootframe, "Vous n'avez pas sélectionné de ligue", "Erreur" ,JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	

}
