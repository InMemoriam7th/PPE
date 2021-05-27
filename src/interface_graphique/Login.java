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


public class Login{
	
	private GestionPersonnel gestionPersonnel;
	private Employe employe;
	private JOptionPane message_erreur = new JOptionPane();
	private JFrame rootframe = new JFrame();
	private JPanel mainframe = new JPanel();
	private JTextField email = new JTextField();
	private JPasswordField password = new JPasswordField();

	public Login(GestionPersonnel gestionPersonnel) {
		this.gestionPersonnel = gestionPersonnel;
		MainFrame();
		RootFrame();
	}
	
	
	private void RootFrame() {
		rootframe.setSize(300,300);
		rootframe.setVisible(true);
		rootframe.setResizable(false);
		rootframe.getContentPane().add(mainframe);
		rootframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rootframe.setLocationRelativeTo(null);
		rootframe.setTitle("Connexion");
	}
	
	private void MainFrame() {
		mainframe.setLayout(new BoxLayout(mainframe, BoxLayout.PAGE_AXIS));
		mainframe.add(Title());
		mainframe.add(Email());
		mainframe.add(password());
		mainframe.add(valider());
	}
	
	private JPanel ItemFrame() {
		JPanel itemframe = new JPanel();
		itemframe.setLayout(new BoxLayout(itemframe, BoxLayout.LINE_AXIS));
		return itemframe;
	}
	
	private JPanel Title() {
		JPanel itemframe = ItemFrame();
		JLabel title = new JLabel("Connexion");
		title.setFont(new Font("Verdana", Font.PLAIN, 20));
		title.setBorder(new EmptyBorder(10, 0, 20, 0));
		itemframe.add(title);
		return itemframe;
	}
	
	private JPanel Email() {
		JPanel itemframe = ItemFrame();
		JLabel emaillabel = new JLabel("Utilisateur ou Email : ");
		email.setMaximumSize(new Dimension(150, 20));
		
		itemframe.add(emaillabel);
		itemframe.add(email);
		return itemframe;
	}
	
	private JPanel password() {
		JPanel item_frame = ItemFrame();
		JLabel password_label = new JLabel("Mot de passe : ");
		password_label.setMaximumSize(new Dimension(117, 20));
		password.setMaximumSize(new Dimension(150, 20));
		password.addKeyListener(getKeyListener());
		item_frame.add(password_label);
		item_frame.add(password);
		return item_frame;
	}
	
	private JPanel valider() {
		JPanel item_frame = ItemFrame();
		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				check_value();
			}
		});
		item_frame.add(valider);
		item_frame.setBorder(new EmptyBorder(15, 0, 0, 0));
		return item_frame;
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
		  employe = gestionPersonnel.checkAccount(email.getText(), String.valueOf(password.getPassword()));
		  if(employe != null) {
			  rootframe.dispose();
			  new Main(gestionPersonnel, employe);
		  }else {
			  message_erreur.showMessageDialog(rootframe, "Email ou mot de passe incorrecte", "Erreur" ,JOptionPane.ERROR_MESSAGE);
		  }
	 }
	
	
	public static void main(String[] args) {
		new Login(GestionPersonnel.getGestionPersonnel());
		
	}
	
	
}
