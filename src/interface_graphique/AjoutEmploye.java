package interface_graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import personnel.Employe;
import personnel.Ligue;
import personnel.SauvegardeImpossible;

public class AjoutEmploye implements ActionListener{
	private Ligue ligue;
	private Ligue_edit ligue_edit;
	private JOptionPane message_erreur = new JOptionPane();
	private JFrame root_frame = new JFrame();
	private JPanel main_frame = new JPanel();
	private JLabel titre = new JLabel("Ajouter un employe.");
	private JLabel nom_label = new JLabel("*Nom : ");
	private JTextField nom  = new JTextField();
	private JLabel prenom_label = new JLabel("*Prénom : ");
	private JTextField prenom = new JTextField();
	private JLabel mail_label = new JLabel("*Mail : ");
	private JTextField mail = new JTextField();
	private JLabel password_label = new JLabel("*Mot de passe : ");
	private JPasswordField password  = new JPasswordField();
	private JLabel dateArrivee_label = new JLabel("Date d'arrivée : ");
	private JLabel dateArrivee_jour_label = new JLabel("Jour : ");
	private JLabel dateArrivee_mois_label = new JLabel("Mois: ");
	private JLabel dateArrivee_annee_label = new JLabel("Année : ");
	private JLabel dateDepart_jour_label = new JLabel("Jour : ");
	private JLabel dateDepart_mois_label = new JLabel("Mois: ");
	private JLabel dateDepart_annee_label = new JLabel("Année : ");
	private JTextField dateArrivee_jour = new JTextField();
	private JTextField dateArrivee_mois = new JTextField();
	private JTextField dateArrivee_annee = new JTextField();
	private JTextField dateDepart_jour = new JTextField();
	private JTextField dateDepart_mois = new JTextField();
	private JTextField dateDepart_annee = new JTextField();
	private JLabel dateDepart_label = new JLabel("Date de départ: ");
	private JTextField dateDepart = new JTextField();
	private JButton ajouter = new JButton("Ajouter");
	
	public AjoutEmploye(Ligue ligue, Ligue_edit ligue_edit) {
		this.ligue = ligue;
		this.ligue_edit = ligue_edit;
		Root_frame();
		Main_frame();
		
	}
	
	private void Root_frame() {
		root_frame.setSize(500,400);
		root_frame.setVisible(true);
		root_frame.getContentPane().add(main_frame);
	}
	
	private void Main_frame() {
		main_frame.setLayout(new BoxLayout(main_frame, BoxLayout.PAGE_AXIS));
		main_frame.add(titre());
		main_frame.add(nomPrenom());
		main_frame.add(mailPwd());
		main_frame.add(ajouter());
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
	
	private JPanel nomPrenom(){
		JPanel item_frame = item_frame();
		nom.setMaximumSize(new Dimension(150, 20));
		prenom.setMaximumSize(new Dimension(150, 20));
		item_frame.add(nom_label);
		item_frame.add(nom);
		item_frame.add(prenom_label);
		item_frame.add(prenom);
		return item_frame;
	}
	
	private JPanel mailPwd() {
		JPanel item_frame = item_frame();
		mail.setMaximumSize(new Dimension(150, 20));
		password.setMaximumSize(new Dimension(150, 20));
		item_frame.add(mail_label);
		item_frame.add(mail);
		item_frame.add(password_label);
		item_frame.add(password);
		return item_frame;
	}
	
	private JPanel ajouter() {
		JPanel item_frame = item_frame();
	    ajouter.addActionListener(this);
	    ajouter.setName("ajouter");
		item_frame.add(ajouter);
		return item_frame;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
			if(nom.getText() != null && prenom.getText() != null && mail.getText() != null && password.getPassword() != null) {
				try {
					ligue.addEmploye(nom.getText(), prenom.getText(), mail.getText(), String.valueOf(password.getPassword()), null, null);
				} catch (SauvegardeImpossible e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					root_frame.dispose();
					ligue_edit.generate_liste_employee();
				}
			}else {
				message_erreur.showMessageDialog(root_frame, "Des champs obligatoire sont vide", "Erreur" ,JOptionPane.ERROR_MESSAGE);
			}
		  }
		
	}
	


