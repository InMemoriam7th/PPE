package interface_graphique;

	import javax.swing.*;
	import java.awt.*;
	import javax.swing.JTextField;

	public class EditRoot {
		private JFrame root_frame = new JFrame();
		private JPanel main_frame = new JPanel();
		private JLabel titre = new JLabel("Modifier Le compte Root.");
		private JLabel nom_label = new JLabel("Nom : ");
		private JTextField nom  = new JTextField();
		private JLabel prenom_label = new JLabel("Prénom : ");
		private JTextField prenom = new JTextField();
		private JLabel mail_label = new JLabel("Mail : ");
		private JTextField mail = new JTextField();
		private JLabel password_label = new JLabel("Mot de passe : ");
		private JPasswordField password  = new JPasswordField();

		private JButton valider = new JButton("valider");
		private JButton retour = new JButton("retour");
		
		public EditRoot() {
			Root_frame();
			Main_frame();
		}
		
		private void Root_frame() {
			root_frame.setSize(300,300);
			root_frame.setVisible(true);
			root_frame.getContentPane().add(main_frame);
			root_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		private void Main_frame() {
			main_frame.setLayout(new BoxLayout(main_frame, BoxLayout.PAGE_AXIS));
			main_frame.add(titre());
			main_frame.add(nomPrenom());
			main_frame.add(mailPwd());
			main_frame.add(validerRetour());
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
		private JPanel validerRetour() {
			JPanel item_frame = item_frame();
		//	valider.addActionListener(this);
		//  retour.addActionListener(this);
			item_frame.add(valider);
			item_frame.add(retour);
			return item_frame;
		}
		public static void main(String[] args) {
			new EditRoot();

		}

	


	}


