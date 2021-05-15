package interface_graphique;

import javax.swing.*;

import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.SauvegardeImpossible;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AjoutLigue{ 
	
	private GestionPersonnel gestionPersonnel;
	private Display_Ligue display_Ligue;
	private JFrame root_frame = new JFrame();
	private JPanel main_frame = new JPanel();
	private JTextField ligue = new JTextField();
	private JButton valider = new JButton("valider");
	
	public AjoutLigue(GestionPersonnel gestionPersonnel, Display_Ligue display_Ligue) {
		this.display_Ligue = display_Ligue;
		this.gestionPersonnel = gestionPersonnel;
		Root_frame();
		Main_frame();
	}

	private void Root_frame() {
		root_frame.setSize(300,150);
		root_frame.setVisible(true);
		root_frame.getContentPane().add(main_frame);
		root_frame.setLocationRelativeTo(null);
	}
	
	private void Main_frame() {
		main_frame.setLayout(new BoxLayout(main_frame, BoxLayout.PAGE_AXIS));
		main_frame.add(titre());
		main_frame.add(ligue());
		main_frame.add(validerRetour());
	}
	private JPanel item_frame() {
		JPanel item_frame = new JPanel();
		item_frame.setLayout(new BoxLayout(item_frame, BoxLayout.LINE_AXIS));
		return item_frame;
	}
	private JPanel titre() {
		JPanel item_frame = item_frame();
		JLabel titre = new JLabel("Ajouter une ligue.");
		item_frame.add(titre);
		return item_frame;
	}
	private JPanel ligue() {
		JPanel item_frame = item_frame();
		JLabel ligue_label = new JLabel("Entrer le nom de la ligue : ");
		ligue.setMaximumSize(new Dimension(150, 20));
		ligue.addKeyListener(getKeyListener());
		item_frame.add(ligue_label);
		item_frame.add(ligue);
		return item_frame;
	}
	private JPanel validerRetour() {
		JPanel item_frame = item_frame();
		valider.setEnabled(false);
		valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add_ligue();
				
			}
		});
		item_frame.add(valider);
		return item_frame;
	}

	
	 private KeyListener getKeyListener()
	 {
	  return new KeyAdapter()
	  {
	   @Override
	   public void keyReleased(KeyEvent e)
	   {
		if(ligue.getText().equals("")) {
			valider.setEnabled(false);
		}else {
			valider.setEnabled(true);
		}
	   }
	  };
	 }
	
	private void add_ligue() {
		try {
			gestionPersonnel.addLigue(ligue.getText());
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		root_frame.dispose();
		display_Ligue.generate_liste();
	}

}
