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

public class Ligue_edit implements ActionListener{
	private GestionPersonnel gestionPersonnel;
	private Employe employe;
	private Ligue ligue;
	private Display_Ligue display_Ligue;
	private JFrame root_frame = new JFrame();
	private JTabbedPane tabbed_frame = new JTabbedPane();
	private JPanel edit_ligue = new JPanel();
	private JPanel edit_employe = new JPanel();
	/* Ligue */
	private JLabel title_ligue = new JLabel("Éditer Ligue");
	private JLabel nom_ligue = new JLabel("Nom de la ligue : ");
	private JTextField nom_text_ligue = new JTextField();
	private JButton button_update_nom_ligue = new JButton("Changer");
	private JLabel admin_label = new JLabel();
	private JLabel admin_change_label = new JLabel("Changer Administrateur : ");
	private JComboBox<Employe> admin_combo = new JComboBox<Employe>();
	private JButton button_update_adminligue = new JButton("Changer Administrateur");
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
		edit_ligue.add(display_admin());
		edit_ligue.add(change_admin_label());
		edit_ligue.add(change_admin_combobox());
		edit_ligue.add(change_admin_button());
		edit_ligue.add(button_suprimer());
		return edit_ligue;
	}
	

	private JPanel title_ligue() {
		JPanel item_frame = item_frame();
		title_ligue.setFont(new Font("Verdana", Font.PLAIN, 20));
		item_frame.add(title_ligue);
		return item_frame;
	}
	
	private JPanel frame_nom_ligue() {
		JPanel item_frame = item_frame();
		nom_text_ligue.setText(ligue.getNom());
		nom_text_ligue.setMaximumSize(new Dimension(150, 20));
		button_update_nom_ligue.addActionListener(this);
		button_update_nom_ligue.setName("update_ligue");
		item_frame.add(nom_ligue);
		item_frame.add(nom_text_ligue);
		item_frame.add(button_update_nom_ligue);
		return item_frame;
	}
	
	private JPanel display_admin() {
		JPanel item_frame = item_frame();
		Employe admin = ligue.getAdministrateur();
		admin_label.setText("Administrateur de la ligue : " + admin.getNom() + " " + admin.getPrenom());
		item_frame.add(admin_label);
		item_frame.setBorder(new EmptyBorder(20, 0, 20, 0));
		return item_frame;
	}
	
	private void generate_list_admin() {
		SortedSet<Employe> list_admin = ligue.getEmployes();
		for(Employe employe : list_admin) {
			admin_combo.addItem(employe);
		}
	}
	
	private JPanel change_admin_label() {
		JPanel item_frame = item_frame();
		item_frame.add(admin_change_label);
		return item_frame;
	}
	
	private JPanel change_admin_combobox() {
		generate_list_admin();
		JPanel item_frame = item_frame();
		admin_combo.setMaximumSize(new Dimension(750, 20));
		item_frame.add(admin_combo);
		item_frame.add(button_update_adminligue);
		return item_frame;
	}
	
	private JPanel change_admin_button() {
		JPanel item_frame = item_frame();
		button_update_adminligue.addActionListener(this);
		button_update_adminligue.setName("update_admin");
		item_frame.add(button_update_adminligue);
		return item_frame;
	}
	
	private JPanel button_suprimer() {
		JPanel item_frame = item_frame();
		button_delete_ligue.addActionListener(this);
		button_delete_ligue.setName("delete_ligue");
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
		button_modifier_employee.addActionListener(this);
		button_modifier_employee.setName("modifier_employee");
		item_frame.add(button_modifier_employee);
		return item_frame;
	}
	private JPanel button_add_employe() {
		JPanel item_frame = item_frame_t();
		button_ajouter_employee.addActionListener(this);
		button_ajouter_employee.setName("ajouter_employee");
		item_frame.add(button_ajouter_employee);
		return item_frame;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		  JButton button = (JButton) e.getSource();
		  switch (button.getName()) {
		  case "update_ligue":update_name_ligue();break;
		  case "delete_ligue":delete_ligue();break;	
		  case "modifier_employee":modifier_employee();break;	
		  case "ajouter_employee":ajouter_employee();break;	
		  }
	}
	
	/* Ligue */
	
	private void update_name_ligue() {
		ligue.setNom(nom_text_ligue.getText());
		display_Ligue.generate_liste();
	}
	
	private void update_admin_ligue() {
		Employe employe = (Employe) admin_combo.getSelectedItem();
		ligue.setAdministrateur(employe);
		admin_label.setText("Administrateur de la ligue : " + employe.getNom() + " " + employe.getPrenom());
	}
	
	private void delete_ligue() {
		ligue.remove();
		display_Ligue.generate_liste();
		root_frame.dispose();
	}
	
	/* Employee */
	
	private void modifier_employee() {
		if(list.getSelectedValue() != null) {
			new Modifier_Employe((Employe) list.getSelectedValue(), this);
		}

	}
	
	private void ajouter_employee() {
		new AjoutEmploye(ligue, this);
	}
}

	
	
	


