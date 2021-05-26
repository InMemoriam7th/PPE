package interface_graphique;

import javax.swing.*;

import exception.DateException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import personnel.Employe;
import personnel.Ligue;

public class ModifierEmploye{
	private Employe employe;
	private LigueEdit ligueedit;
	private Ligue ligue;
	private JFrame rootframe = new JFrame();
	private JPanel mainframe = new JPanel();
	private JTextField nom  = new JTextField();
	private JTextField prenom = new JTextField();
	private JTextField mail = new JTextField();
	private JPasswordField password  = new JPasswordField();
	private JTextField dateArriveejour = new JTextField();
	private JTextField dateArriveemois = new JTextField();
	private JTextField dateArriveeannee = new JTextField();
	private JTextField dateDepartjour = new JTextField();
	private JTextField dateDepartmois = new JTextField();
	private JTextField dateDepartannee = new JTextField();
	
	private JTextField dateDepart = new JTextField();
	
	
	
	public ModifierEmploye(Employe employe,Ligue ligue ,LigueEdit ligueedit) {
		this.employe = employe;
		this.ligueedit = ligueedit;
		this.ligue = ligue;
		RootFrame();
		MainFrame();
		
	}
	
	private void RootFrame() {
		rootframe.setSize(500,400);
		rootframe.setVisible(true);
		rootframe.getContentPane().add(mainframe);
	}
	
	private void MainFrame() {
		mainframe.setLayout(new BoxLayout(mainframe, BoxLayout.PAGE_AXIS));
		mainframe.add(Titre());
		mainframe.add(NomPrenom());
		mainframe.add(MailPwd());
		mainframe.add(LabelDateArrivee());
		mainframe.add(DateArrivee());
		mainframe.add(LabelDateDepart());
		mainframe.add(DateDepart());
		mainframe.add(Valider());
		mainframe.add(Supprimer());
		mainframe.add(SetAdministrateur());
	}
	
	private JPanel ItemFrame() {
		JPanel itemframe = new JPanel();
		itemframe.setLayout(new BoxLayout(itemframe, BoxLayout.LINE_AXIS));
		return itemframe;
	}
	private JPanel Titre() {
		JPanel itemframe = ItemFrame();
		JLabel titre = new JLabel("Modifier un employe.");
		itemframe.add(titre);
		return itemframe;
	}
	
	private JPanel NomPrenom(){
		JPanel itemframe = ItemFrame();
		JLabel nomlabel = new JLabel("*Nom : ");
		JLabel prenomlabel = new JLabel("*Prénom : ");
		nom.setMaximumSize(new Dimension(150, 20));
		prenom.setMaximumSize(new Dimension(150, 20));
		nom.setText(employe.getNom());
		prenom.setText(employe.getPrenom());
		itemframe.add(nomlabel);
		itemframe.add(nom);
		itemframe.add(prenomlabel);
		itemframe.add(prenom);
		return itemframe;
	}
	
	private JPanel MailPwd() {
		JPanel itemframe = ItemFrame();
		JLabel maillabel = new JLabel("*Mail : ");
		JLabel passwordlabel = new JLabel("*Mot de passe : ");
		mail.setMaximumSize(new Dimension(150, 20));
		password.setMaximumSize(new Dimension(150, 20));
		mail.setText(employe.getMail());
		password.setText(employe.getPassword());
		itemframe.add(maillabel);
		itemframe.add(mail);
		itemframe.add(passwordlabel);
		itemframe.add(password);
		return itemframe;
	}
	
	private JPanel LabelDateArrivee() {
		JPanel itemframe = ItemFrame();
		JLabel dateArriveelabel = new JLabel("Date d'arrivée : ");
		itemframe.add(dateArriveelabel);
		return itemframe;
	}
	
	private JPanel DateArrivee() {
		JPanel item_frame = ItemFrame();
		JLabel dateArriveejourlabel = new JLabel("Jour : ");
		JLabel dateArriveemoislabel = new JLabel("Mois: ");
		JLabel dateArriveeanneelabel = new JLabel("Année : ");
		
		dateArriveejour.setMaximumSize(new Dimension(150, 20));
		dateArriveemois.setMaximumSize(new Dimension(150, 20));
		dateArriveeannee.setMaximumSize(new Dimension(150, 20));
		
		item_frame.add(dateArriveejour);
		item_frame.add(dateArriveemois);
		item_frame.add(dateArriveeannee);
		
		item_frame.add(dateArriveejourlabel);
		item_frame.add(dateArriveejour);
		item_frame.add(dateArriveemoislabel);
		item_frame.add(dateArriveemois);
		item_frame.add(dateArriveeanneelabel);
		item_frame.add(dateArriveeannee);
		return item_frame;
	}
	
	private JPanel LabelDateDepart() {
		JPanel itemframe = ItemFrame();
		JLabel dateDepartlabel = new JLabel("Date de départ: ");
		itemframe.add(dateDepartlabel);
		return itemframe;
	}
	
	private JPanel DateDepart() {
		JPanel itemframe = ItemFrame();
		JLabel dateDepartjourlabel = new JLabel("Jour : ");
		JLabel dateDepartmoislabel = new JLabel("Mois: ");
		JLabel dateDepartanneelabel = new JLabel("Année : ");
		dateDepartjour.setMaximumSize(new Dimension(150, 20));
		dateDepartmois.setMaximumSize(new Dimension(150, 20));
		dateDepartannee.setMaximumSize(new Dimension(150, 20));
		
		itemframe.add(dateDepartjour);
		itemframe.add(dateDepartmois);
		itemframe.add(dateDepartannee);
		
		itemframe.add(dateDepartjourlabel);
		itemframe.add(dateDepartjour);
		itemframe.add(dateDepartmoislabel);
		itemframe.add(dateDepartmois);
		itemframe.add(dateDepartanneelabel);
		itemframe.add(dateDepartannee);
		return itemframe;
	}
	
	private JPanel Valider() {
		JPanel itemframe = ItemFrame();
		JButton modifier = new JButton("modifier");
	    modifier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateEmploye();
			}
		});
		itemframe.add(modifier);
		return itemframe;
	}
	
	private JPanel Supprimer() {
		JPanel itemframe = ItemFrame();
		JButton supprimer = new JButton("Supprimer");
		supprimer.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				SupprimerEmploye();	
			}
		});
		itemframe.add(supprimer);
		return itemframe;
	}
	
	private JPanel SetAdministrateur() {
		JPanel itemframe = ItemFrame();
		JButton buttonsetadmin = new JButton("Définir comme Administrateur");
		buttonsetadmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ligue.setAdministrateur(employe);
				ligueedit.UpdateAdmin();
			}
		});
		itemframe.add(buttonsetadmin);
		return itemframe;
	}

	
	private void UpdateEmploye() {
		JOptionPane messageerreur = new JOptionPane();
		if(nom.getText() != null && prenom.getText() != null && mail.getText() != null && password.getPassword() != null) {
		employe.setNom(nom.getText());
		employe.setPrenom(prenom.getText());
		employe.setMail(mail.getText());
		employe.setPassword(String.valueOf(password.getPassword()));
		try {
			LocalDate date = LocalDate.of(Integer.parseInt(dateArriveeannee.getText()), Integer.parseInt(dateArriveemois.getText()), Integer.parseInt(dateArriveejour.getText()));
			employe.setDateArrivee(date);
		}catch (DateException e) {
			messageerreur.showMessageDialog(rootframe, e, "Erreur" ,JOptionPane.ERROR_MESSAGE);
			return;
		}catch(NumberFormatException e) {
			messageerreur.showMessageDialog(rootframe, "La date d'arrivée n'existe pas", "Erreur" ,JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			LocalDate date = LocalDate.of(Integer.parseInt(dateDepartannee.getText()), Integer.parseInt(dateDepartmois.getText()), Integer.parseInt(dateDepartjour.getText()));
			employe.setDateDepart(date);
		} catch (DateException e) {
			messageerreur.showMessageDialog(rootframe, e, "Erreur" ,JOptionPane.ERROR_MESSAGE);
			return;
		} catch (NumberFormatException e) {
			messageerreur.showMessageDialog(rootframe, "La date de départ n'existe pas", "Erreur" ,JOptionPane.ERROR_MESSAGE);
			return;
		}
		rootframe.dispose();
		ligueedit.GenerateListeEmployee();
		}else {
			messageerreur.showMessageDialog(rootframe, "Des champs obligatoire sont vide", "Erreur" ,JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	private void SupprimerEmploye() {
		employe.remove();
		rootframe.dispose();
		ligueedit.GenerateListeEmployee();
	}
	

}
