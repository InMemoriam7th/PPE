package interface_graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import personnel.Employe;

public class ModifierEmploye{
	private Employe employe;
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
	
	
	
	public ModifierEmploye(Employe employe, Ligue_edit ligue_edit) {
		this.employe = employe;
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
		main_frame.add(label_date_arrivee());
		main_frame.add(date_arrivee());
		main_frame.add(label_date_depart());
		main_frame.add(date_depart());
		main_frame.add(valider());
		main_frame.add(suprimer());
	}
	
	private JPanel item_frame() {
		JPanel item_frame = new JPanel();
		item_frame.setLayout(new BoxLayout(item_frame, BoxLayout.LINE_AXIS));
		return item_frame;
	}
	private JPanel titre() {
		JPanel item_frame = item_frame();
		JLabel titre = new JLabel("Modifier un employe.");
		item_frame.add(titre);
		return item_frame;
	}
	
	private JPanel nomPrenom(){
		JPanel item_frame = item_frame();
		JLabel nom_label = new JLabel("*Nom : ");
		JLabel prenom_label = new JLabel("*Prénom : ");
		nom.setMaximumSize(new Dimension(150, 20));
		prenom.setMaximumSize(new Dimension(150, 20));
		nom.setText(employe.getNom());
		prenom.setText(employe.getPrenom());
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
		mail.setText(employe.getMail());
		password.setText(employe.getPassword());
		item_frame.add(mail_label);
		item_frame.add(mail);
		item_frame.add(password_label);
		item_frame.add(password);
		return item_frame;
	}
	
	private JPanel label_date_arrivee() {
		JPanel item_frame = item_frame();
		JLabel dateArrivee_label = new JLabel("Date d'arrivée : ");
		item_frame.add(dateArrivee_label);
		return item_frame;
	}
	
	private JPanel date_arrivee() {
		JPanel item_frame = item_frame();
		JLabel dateArrivee_jour_label = new JLabel("Jour : ");
		JLabel dateArrivee_mois_label = new JLabel("Mois: ");
		JLabel dateArrivee_annee_label = new JLabel("Année : ");
		
		dateArrivee_jour.setMaximumSize(new Dimension(150, 20));
		dateArrivee_mois.setMaximumSize(new Dimension(150, 20));
		dateArrivee_annee.setMaximumSize(new Dimension(150, 20));
		
		item_frame.add(dateArrivee_jour);
		item_frame.add(dateArrivee_mois);
		item_frame.add(dateArrivee_annee);
		
		item_frame.add(dateArrivee_jour_label);
		item_frame.add(dateArrivee_jour);
		item_frame.add(dateArrivee_mois_label);
		item_frame.add(dateArrivee_mois);
		item_frame.add(dateArrivee_annee_label);
		item_frame.add(dateArrivee_annee);
		return item_frame;
	}
	
	private JPanel label_date_depart() {
		JPanel item_frame = item_frame();
		JLabel dateDepart_label = new JLabel("Date de départ: ");
		item_frame.add(dateDepart_label);
		return item_frame;
	}
	
	private JPanel date_depart() {
		JPanel item_frame = item_frame();
		JLabel dateDepart_jour_label = new JLabel("Jour : ");
		JLabel dateDepart_mois_label = new JLabel("Mois: ");
		JLabel dateDepart_annee_label = new JLabel("Année : ");
		dateDepart_jour.setMaximumSize(new Dimension(150, 20));
		dateDepart_mois.setMaximumSize(new Dimension(150, 20));
		dateDepart_annee.setMaximumSize(new Dimension(150, 20));
		
		item_frame.add(dateDepart_jour);
		item_frame.add(dateDepart_mois);
		item_frame.add(dateDepart_annee);
		
		item_frame.add(dateDepart_jour_label);
		item_frame.add(dateDepart_jour);
		item_frame.add(dateDepart_mois_label);
		item_frame.add(dateDepart_mois);
		item_frame.add(dateDepart_annee_label);
		item_frame.add(dateDepart_annee);
		return item_frame;
	}
	
	private JPanel valider() {
		JPanel item_frame = item_frame();
		JButton modifier = new JButton("modifier");
	    modifier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update_employe();
			}
		});
		item_frame.add(modifier);
		return item_frame;
	}
	
	private JPanel suprimer() {
		JPanel item_frame = item_frame();
		JButton suprimer = new JButton("Suprimer");
		suprimer.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				suprimer_employe();	
			}
		});
		item_frame.add(suprimer);
		return item_frame;
	}

	
	private void update_employe() {
		JOptionPane message_erreur = new JOptionPane();
		if(nom.getText() != null && prenom.getText() != null && mail.getText() != null && password.getPassword() != null) {
		employe.setNom(nom.getText());
		employe.setPrenom(prenom.getText());
		employe.setMail(mail.getText());
		employe.setPassword(String.valueOf(password.getPassword()));
		try {
			LocalDate date = LocalDate.of(Integer.parseInt(dateArrivee_annee.getText()), Integer.parseInt(dateArrivee_mois.getText()), Integer.parseInt(dateArrivee_jour.getText()));
			employe.setDateArrivee(date);
		}catch (Exception e) {
			// TODO: handle exception
		}
		try {
			LocalDate date = LocalDate.of(Integer.parseInt(dateDepart_annee.getText()), Integer.parseInt(dateDepart_mois.getText()), Integer.parseInt(dateDepart_jour.getText()));
			employe.setDateDepart(date);
		} catch (Exception e) {
			// TODO: handle exception
		}
		root_frame.dispose();
		ligue_edit.generate_liste_employee();
		}else {
			message_erreur.showMessageDialog(root_frame, "Des champs obligatoire sont vide", "Erreur" ,JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void suprimer_employe() {
		employe.remove();
		root_frame.dispose();
		ligue_edit.generate_liste_employee();
	}

}
