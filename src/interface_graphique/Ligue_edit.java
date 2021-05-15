package interface_graphique;

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

public class Ligue_edit{
	private GestionPersonnel gestionPersonnel;
	private Employe employe;
	private Ligue ligue;
	private Display_Ligue display_Ligue;
	private JFrame root_frame = new JFrame();
	private JTabbedPane tabbed_frame = new JTabbedPane();
	private JPanel edit_ligue = new JPanel();
	private JPanel edit_employe = new JPanel();
	/* Ligue */
//	private JLabel title_ligue = new JLabel("Éditer Ligue");
	private JLabel nom_ligue = new JLabel("Nom de la ligue : ");
	private JTextField nomTextLigue = new JTextField();
	private JButton buttonUpdateNomLigue = new JButton("Changer");
	private JButton button_delete_ligue = new JButton("Suprimer la ligue");
	/* Employee */
	private JLabel title_employee = new JLabel("Employee");
	private DefaultListModel listModel = new DefaultListModel();
	private JList list = new JList(listModel);
	private JButton button_modifier_employee = new JButton("Modifier");
	private JButton button_ajouter_employee = new JButton("Ajouter un employee");

	
	public Ligue_edit(GestionPersonnel gestionPersonnel, Employe employe, Ligue ligue, Display_Ligue display_Ligue){
		this.gestionPersonnel = gestionPersonnel;
		this.employe = employe;
		this.ligue = ligue;
		this.display_Ligue = display_Ligue;
		tabbed_frame();
		Root_frame();
	}
	
	private void Root_frame() {
		root_frame.setSize(800,500);
		root_frame.setVisible(true);
		root_frame.getContentPane().add(tabbed_frame);
		root_frame.setLocationRelativeTo(null);
	}
	
	private void tabbed_frame() {
		tabbed_frame.add("Éditer Ligue", edit_ligue());
		tabbed_frame.add("Éditer Employee", edit_employe());
	}
	
	private JPanel item_frame() {
		JPanel item_frame = new JPanel();
		item_frame.setLayout(new BoxLayout(item_frame, BoxLayout.LINE_AXIS));
		return item_frame;
	}
	
	/* Page edition ligue */
	
	private JPanel edit_ligue() {
		edit_ligue.setLayout(new BoxLayout(edit_ligue, BoxLayout.PAGE_AXIS));
		edit_ligue.add(title_ligue());
		edit_ligue.add(frame_nom_ligue());
		edit_ligue.add(button_suprimer());
		return edit_ligue;
	}
	

	private JPanel title_ligue() {
		JPanel item_frame = item_frame();
		JLabel title_ligue = new JLabel("Éditer Ligue");
		title_ligue.setFont(new Font("Verdana", Font.PLAIN, 20));
		item_frame.add(title_ligue);
		return item_frame;
	}
	
	private JPanel frame_nom_ligue() {
		JPanel item_frame = item_frame();
		nomTextLigue.setText(ligue.getNom());
		nomTextLigue.setMaximumSize(new Dimension(150, 20));
		buttonUpdateNomLigue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				update_name_ligue();
				
			}
		});
		item_frame.add(nom_ligue);
		item_frame.add(nomTextLigue);
		item_frame.add(buttonUpdateNomLigue);
		return item_frame;
	}
	

	
	private JPanel button_suprimer() {
		JPanel item_frame = item_frame();
		button_delete_ligue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delete_ligue();
			}
		});
		item_frame.add(button_delete_ligue);
		return item_frame;
	}
	
	/* Page edition Employee */
	
	private JPanel edit_employe() {
		edit_employe.setLayout(new GridLayout(4, 0));
		edit_employe.add(title_employee());
		edit_employe.add(list_employee());
		edit_employe.add(button_modifier_employe());
		edit_employe.add(button_add_employe());
		return edit_employe;
	}
	
	private JPanel item_frame_t() {
		JPanel item_frame = new JPanel();
		return item_frame;
	}
	
	public void generate_liste_employee() {
		listModel.removeAllElements();
		SortedSet<Employe> list_ligue = ligue.getEmployes();
		for(Employe ligue : list_ligue) {
			listModel.addElement(ligue);
		}
		
	}
	
	private JPanel title_employee() {
		JPanel item_frame = item_frame_t();
		title_employee.setFont(new Font("Verdana", Font.PLAIN, 20));
		item_frame.setBorder(new EmptyBorder(10, 0, 0, 0));
		item_frame.add(title_employee);
		return item_frame;
	}
	
	private JPanel list_employee() {
		generate_liste_employee();
		JPanel item_frame = item_frame_t();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		item_frame.add(list);
		item_frame.setMinimumSize(new Dimension(300, 500));
		item_frame.setMaximumSize(new Dimension(300, 500));
		JScrollPane listScrollPane = new JScrollPane(list);
		item_frame.add(listScrollPane);
		return item_frame;
	}
	
	private JPanel button_modifier_employe() {
		JPanel item_frame = item_frame_t();
		button_modifier_employee.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modifier_employee();	
			}
		});
		item_frame.add(button_modifier_employee);
		return item_frame;
	}
	private JPanel button_add_employe() {
		JPanel item_frame = item_frame_t();
		button_ajouter_employee.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ajouter_employee();	
			}
		});
		item_frame.add(button_ajouter_employee);
		return item_frame;
	}
	
	
	
	/* Ligue */
	
	private void update_name_ligue() {
		ligue.setNom(nomTextLigue.getText());
		display_Ligue.generate_liste();
	}
	
	
	private void delete_ligue() {
		ligue.remove();
		display_Ligue.generate_liste();
		root_frame.dispose();
	}
	
	/* Employee */
	
	private void modifier_employee() {
		if(list.getSelectedValue() != null) {
			new ModifierEmploye((Employe) list.getSelectedValue(), this);
		}

	}
	
	private void ajouter_employee() {
		new AjoutEmploye(ligue, this);
	}
}

	
	
	


