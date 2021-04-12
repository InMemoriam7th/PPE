package interface_graphique;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import commandLine.PersonnelConsole;
import personnel.Employe;
import personnel.GestionPersonnel;



public class Login implements ActionListener{
	
	private GestionPersonnel gestionPersonnel;
	JOptionPane message_erreur = new JOptionPane();
	private JFrame root_frame = new JFrame();
	private JPanel main_frame = new JPanel();
	private JLabel title = new JLabel("Connection");
	private JLabel email_label = new JLabel("Email : ");
	private JTextField email = new JTextField();
	private JLabel password_label = new JLabel("Password : ");
	private JPasswordField password = new JPasswordField();
	private JButton valider = new JButton("Valider");
	
	 @Override
	 public void actionPerformed(ActionEvent e)
	 {
	  System.out.println(email.getText());
	  System.out.println(password.getPassword());
	  Employe employe = gestionPersonnel.check_account(email.getText(), String.valueOf(password.getPassword()));
	  if(employe != null) {
		  System.out.println("connecter");
	  }else {
		  message_erreur.showMessageDialog(root_frame, "Email ou mot de passe incorrecte", "Erreur" ,JOptionPane.ERROR_MESSAGE);
	  }
	 }
	
	public Login(GestionPersonnel gestionPersonnel) {
		this.gestionPersonnel = gestionPersonnel;
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
		main_frame.add(title());
		main_frame.add(email());
		main_frame.add(password());
		main_frame.add(valider());
	}
	
	private JPanel item_frame() {
		JPanel item_frame = new JPanel();
		item_frame.setLayout(new BoxLayout(item_frame, BoxLayout.LINE_AXIS));
		return item_frame;
	}
	
	private JPanel title() {
		JPanel item_frame = item_frame();
		item_frame.add(title);
		return item_frame;
	}
	
	private JPanel email() {
		JPanel item_frame = item_frame();
		email.setMaximumSize(new Dimension(150, 20));
		item_frame.add(email_label);
		item_frame.add(email);
		return item_frame;
	}
	
	private JPanel password() {
		JPanel item_frame = item_frame();
		password.setMaximumSize(new Dimension(150, 20));
		item_frame.add(password_label);
		item_frame.add(password);
		return item_frame;
	}
	
	private JPanel valider() {
		JPanel item_frame = item_frame();
		valider.addActionListener(this);
		item_frame.add(valider);
		return item_frame;
	}
	
	public static void main(String[] args) {
		new Login(GestionPersonnel.getGestionPersonnel());
		
	}
	
	
}
