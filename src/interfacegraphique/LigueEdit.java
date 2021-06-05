package interfacegraphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedSet;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;

public class LigueEdit{
	private GestionPersonnel gestionPersonnel;
	private Employe employe;
	private Ligue ligue;
	private DisplayLigue displayLigue;
	private JFrame rootframe = new JFrame();
	private JTabbedPane tabbedframe = new JTabbedPane();
	private JPanel editligue = new JPanel();
	private JPanel editemploye = new JPanel();
	
	/* Ligue */
	JLabel titleligue = new JLabel();
	private JTextField nomTextLigue = new JTextField();
	private JLabel labeladministrateur = new JLabel();
	
	/* Employee */
	JLabel titleemployee = new JLabel();
	private DefaultListModel listModel = new DefaultListModel();
	private JList list = new JList(listModel);
	
	

	
	public LigueEdit(GestionPersonnel gestionPersonnel, Employe employe, Ligue ligue, DisplayLigue displayLigue){
		this.gestionPersonnel = gestionPersonnel;
		this.employe = employe;
		this.ligue = ligue;
		this.displayLigue = displayLigue;
		TabbedFrame();
		RootFrame();
		UpdateTitre();
	}
	
	private void RootFrame() {
		rootframe.setSize(1000,500);
		rootframe.setVisible(true);
		rootframe.getContentPane().add(tabbedframe);
		rootframe.setLocationRelativeTo(null);
		rootframe.setTitle(ligue.getNom());
	}
	
	private void TabbedFrame() {
		tabbedframe.add("Éditer Ligue", EditLigue());
		tabbedframe.add("Éditer Employee", EditEmploye());
	}
	
	private JPanel ItemFrameLigue() {
		JPanel itemframe = new JPanel();
		itemframe.setLayout(new BoxLayout(itemframe, BoxLayout.LINE_AXIS));
		return itemframe;
	}
	
	/* Page edition ligue */
	
	private JPanel EditLigue() {
		editligue.setLayout(new BoxLayout(editligue, BoxLayout.PAGE_AXIS));
		editligue.add(TitleLigue());
		editligue.add(DisplayAdmin());
		editligue.add(FrameNomligue());
		editligue.add(ButtonSupprimer());
		return editligue;
	}
	

	private JPanel TitleLigue() {
		JPanel itemframe = ItemFrameLigue();
		titleligue.setFont(new Font("Verdana", Font.PLAIN, 20));
		itemframe.add(titleligue);
		itemframe.setBorder(new EmptyBorder(10, 0, 10, 0));
		return itemframe;
	}
	
	private JPanel FrameNomligue() {
		JPanel itemframe = ItemFrameLigue();
		JLabel nomligue = new JLabel("Nom de la ligue : ");
		JButton buttonUpdateNomLigue = new JButton("Changer");
		if(!(employe.estRoot() || employe.estAdmin(ligue))) {
			buttonUpdateNomLigue.setEnabled(false);
			nomTextLigue.setEditable(false);
		}
		nomTextLigue.setText(ligue.getNom());
		nomTextLigue.setMaximumSize(new Dimension(150, 20));
		buttonUpdateNomLigue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateNameLigue();
				
			}
		});
		itemframe.add(nomligue);
		itemframe.add(nomTextLigue);
		itemframe.add(buttonUpdateNomLigue);
		itemframe.setBorder(new EmptyBorder(10, 0, 10, 0));
		return itemframe;
	}
	

	
	private JPanel ButtonSupprimer() {
		JPanel itemframe = ItemFrameLigue();
		JButton buttondeleteligue = new JButton("Suprimer la ligue");
		if(!(employe.estRoot() || employe.estAdmin(ligue)))
			buttondeleteligue.setEnabled(false);
		buttondeleteligue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteLigue();
			}
		});
		itemframe.add(buttondeleteligue);
		return itemframe;
	}
	
	public void UpdateAdmin() {
		Employe admin = ligue.getAdministrateur();
		labeladministrateur.setText("Administrateur : " + admin.getNom() + " " + admin.getPrenom());
	}
	
	private JPanel DisplayAdmin() {
		JPanel itemframe = ItemFrameLigue();
		UpdateAdmin();
		itemframe.add(labeladministrateur);
		return itemframe;
	}
	
	/* Page edition Employee */
	
	
	private JPanel EditEmploye() {
		editemploye.setLayout(new GridLayout(4, 0));
		editemploye.add(TitleEmployee());
		editemploye.add(ListEmployee());
		editemploye.add(ButtonModifierEmploye());
		editemploye.add(ButtonAddEmployee());
		return editemploye;
	}
	
	private JPanel ItemFrameEmployee() {
		JPanel itemframe = new JPanel();
		return itemframe;
	}
	
	public void GenerateListeEmployee() {
		listModel.removeAllElements();
		SortedSet<Employe> listligue = ligue.getEmployes();
		for(Employe ligue : listligue) {
			listModel.addElement(ligue);
		}
	}
	
	private JPanel TitleEmployee() {
		JPanel itemframe = ItemFrameEmployee();
		titleemployee.setFont(new Font("Verdana", Font.PLAIN, 20));
		itemframe.setBorder(new EmptyBorder(10, 0, 0, 0));
		itemframe.add(titleemployee);
		return itemframe;
	}
	
	private JPanel ListEmployee() {
		GenerateListeEmployee();
		JPanel itemframe = ItemFrameEmployee();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setPreferredSize(new Dimension(800,100));
		itemframe.add(list);
		JScrollPane listScrollPane = new JScrollPane(list);
		itemframe.add(listScrollPane);
		return itemframe;
	}
	
	private JPanel ButtonModifierEmploye() {
		JPanel itemframe = ItemFrameEmployee();
		JButton buttonmodifieremployee = new JButton("Modifier");
		if(!(employe.estRoot() || employe.estAdmin(ligue)))
			buttonmodifieremployee.setEnabled(false);
		buttonmodifieremployee.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ModifierEmployee();	
			}
		});
		itemframe.add(buttonmodifieremployee);
		return itemframe;
	}
	private JPanel ButtonAddEmployee() {
		JPanel itemframe = ItemFrameEmployee();
		JButton buttonajouteremployee = new JButton("Ajouter un employé");
		if(!(employe.estRoot() || employe.estAdmin(ligue)))
			buttonajouteremployee.setEnabled(false);
		buttonajouteremployee.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AjouterEmployee();	
			}
		});
		itemframe.add(buttonajouteremployee);
		return itemframe;
	}
	
	private void UpdateTitre() {
		titleligue.setText("Éditer la ligue " + ligue.getNom());
		titleemployee.setText("Employé de la ligue " + ligue.getNom());
	}
	
	/* Ligue */
	
	private void UpdateNameLigue() {
		ligue.setNom(nomTextLigue.getText());
		rootframe.setTitle(nomTextLigue.getText());
		displayLigue.GenerateListe();
		UpdateTitre();
	}
	
	
	private void DeleteLigue() {
		ligue.remove();
		displayLigue.GenerateListe();
		rootframe.dispose();
	}
	
	/* Employee */
	
	private void ModifierEmployee() {
		if(list.getSelectedValue() != null) {
			new ModifierEmploye((Employe) list.getSelectedValue(), ligue ,this);
		}

	}
	
	private void AjouterEmployee() {
		new AjoutEmploye(ligue, this);
	}
}

	
	
	


