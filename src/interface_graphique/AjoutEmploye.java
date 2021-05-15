package interface_graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import personnel.Employe;
import personnel.Ligue;
import personnel.SauvegardeImpossible;

public class AjoutEmploye{
	private Ligue ligue;
	private Ligue_edit ligue_edit;
	
	private JFrame root_frame = new JFrame();
	private JPanel main_frame = new JPanel();

	private JTextField nom  = new JTextField();
	
	private JTextField prenom = new JTextField();
	private JTextField mail = new JTextField();
	
	private JPasswordField password  = new JPasswordField();

	private JTextField dateArrivee_jour = new JTextField();
	private JTextField dateArrivee_mois = new JTextField();
	private JTextField dateArrivee_annee = new JTextField();
	private JTextField dateDepart_jour = new JTextField();
	private JTextField dateDepart_mois = new JTextField();
	private JTextField dateDepart_annee = new JTextField();
	
	private JTextField dateDepart = new JTextField();
	
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
		JLabel titre = new JLabel("Ajouter un employe.");
		item_frame.add(titre);
		return item_frame;
	}
	
	private JPanel nomPrenom(){
		JPanel item_frame = item_frame();
		JLabel nom_label = new JLabel("*Nom : ");
		JLabel prenom_label = new JLabel("*Prénom : ");
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
		JLabel mail_label = new JLabel("*Mail : ");
		JLabel password_label = new JLabel("*Mot de passe : ");
		mail.setMaximumSize(new Dimension(150, 20));
		password.setMaximumSize(new Dimension(150, 20));
		item_frame.add(mail_label);
		item_frame.add(mail);
		item_frame.add(password_label);
		item_frame.add(password);
		return item_frame;
	}
	
	private JPanel ajouter() {
		JOptionPane message_erreur = new JOptionPane();
		JPanel item_frame = item_frame();
		JButton ajouter = new JButton("Ajouter");
	    ajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nom.getText() == "" && prenom.getText() == "" && mail.getText() == "" && String.valueOf(password.getPassword()) == "") {
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
		});
		item_frame.add(ajouter);
		return item_frame;
	}
		
	}
	


