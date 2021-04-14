package interface_graphique;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

import interface_graphique.*;
import personnel.Employe;
import personnel.GestionPersonnel;


public class Login implements ActionListener{
	
	private GestionPersonnel gestionPersonnel;
	private Employe employe;
	private JOptionPane message_erreur = new JOptionPane();
	private JFrame root_frame = new JFrame();
	private JPanel main_frame = new JPanel();
	private JLabel title = new JLabel("Connection");
	private JLabel email_label = new JLabel("Email : ");
	private JTextField email = new JTextField();
	private JLabel password_label = new JLabel("Password : ");
	private JPasswordField password = new JPasswordField();
	private JButton valider = new JButton("Valider");
	

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
		root_frame.setLocationRelativeTo(null);
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
		title.setFont(new Font("Verdana", Font.PLAIN, 20));
		title.setBorder(new EmptyBorder(10, 0, 20, 0));
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
		password.addKeyListener(getKeyListener());
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
	
	 @Override
	 public void actionPerformed(ActionEvent e)
	 {
		 check_value();
	 }
	 
	 private KeyListener getKeyListener()
	 {
	  return new KeyAdapter()
	  {
	   @Override
	   public void keyReleased(KeyEvent e)
	   {
		if(e.getKeyCode() == 10) {
			check_value();
		}

	   }
	  };
	 }
	 
	 private void check_value() {
		  employe = gestionPersonnel.check_account(email.getText(), String.valueOf(password.getPassword()));
		  if(employe != null) {
			  root_frame.dispose();
			  new Main(gestionPersonnel, employe);
		  }else {
			  message_erreur.showMessageDialog(root_frame, "Email ou mot de passe incorrecte", "Erreur" ,JOptionPane.ERROR_MESSAGE);
		  }
	 }
	
	
	public static void main(String[] args) {
		new Login(GestionPersonnel.getGestionPersonnel());
		
	}
	
	
}
