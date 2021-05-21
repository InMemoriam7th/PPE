package interface_graphique;

	import javax.swing.*;
	import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import personnel.Employe;
import personnel.GestionPersonnel;

	public class EditRoot {
		private GestionPersonnel gestionPersonnel;
		private Employe employe;
		private JFrame rootframe = new JFrame();
		private JPanel mainframe = new JPanel();
		
		private JTextField nom  = new JTextField();
		
		private JTextField prenom = new JTextField();
		
		private JTextField mail = new JTextField();
		
		private JPasswordField password  = new JPasswordField();

		
		
		
		public EditRoot(GestionPersonnel gestionPersonnel, Employe employe) {
			this.gestionPersonnel = gestionPersonnel;
			this.employe = employe;
			RootFrame();
			MainFrame();
		}
		
		private void RootFrame() {
			rootframe.setSize(300,300);
			rootframe.setVisible(true);
			rootframe.getContentPane().add(mainframe);
			rootframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		private void MainFrame() {
			mainframe.setLayout(new BoxLayout(mainframe, BoxLayout.PAGE_AXIS));
			mainframe.add(Titre());
			mainframe.add(NomPrenom());
			mainframe.add(MailPwd());
			mainframe.add(ValiderRetour());
		}
		
		private JPanel ItemFrame() {
			JPanel itemframe = new JPanel();
			itemframe.setLayout(new BoxLayout(itemframe, BoxLayout.LINE_AXIS));
			return itemframe;
		}
		private JPanel Titre() {
			JPanel itemframe = ItemFrame();
			JLabel titre = new JLabel("Modifier Le compte Root.");
			itemframe.add(titre);
			return itemframe;
		}
		
		private JPanel NomPrenom(){
			JPanel itemframe = ItemFrame();
			JLabel nomlabel = new JLabel("Nom : ");
			JLabel prenomlabel = new JLabel("Prénom : ");
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
			JLabel maillabel = new JLabel("Mail : ");
			JLabel passwordlabel = new JLabel("Mot de passe : ");
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
		private JPanel ValiderRetour() {
			JPanel itemframe = ItemFrame();
			JButton valider = new JButton("valider");
			valider.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!nom.getText().isEmpty() && !String.valueOf(password.getPassword()).isEmpty()) {
						employe.setNom(nom.getText());
						employe.setPrenom(prenom.getText());
						employe.setMail(mail.getText());
						employe.setPassword(String.valueOf(password.getPassword()));
						rootframe.dispose();
					}
				}
			});
			itemframe.add(valider);
			return itemframe;
		}

	


	}


