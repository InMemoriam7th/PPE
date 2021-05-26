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
	private DisplayLigue displayLigue;
	private JFrame rootframe = new JFrame();
	private JPanel mainframe = new JPanel();
	private JTextField ligue = new JTextField();
	private JButton valider = new JButton("valider");
	
	public AjoutLigue(GestionPersonnel gestionPersonnel, DisplayLigue displayLigue) {
		this.displayLigue = displayLigue;
		this.gestionPersonnel = gestionPersonnel;
		RootFrame();
		MainFrame();
	}

	private void RootFrame() {
		rootframe.setSize(300,150);
		rootframe.setVisible(true);
		rootframe.getContentPane().add(mainframe);
		rootframe.setLocationRelativeTo(null);
		rootframe.setTitle("Ajouter une ligue");
	}
	
	private void MainFrame() {
		mainframe.setLayout(new BoxLayout(mainframe, BoxLayout.PAGE_AXIS));
		mainframe.add(titre());
		mainframe.add(ligue());
		mainframe.add(ValiderRetour());
	}
	private JPanel ItemFrame() {
		JPanel itemframe = new JPanel();
		itemframe.setLayout(new BoxLayout(itemframe, BoxLayout.LINE_AXIS));
		return itemframe;
	}
	private JPanel titre() {
		JPanel itemframe = ItemFrame();
		JLabel titre = new JLabel("Ajouter une ligue.");
		itemframe.add(titre);
		return itemframe;
	}
	private JPanel ligue() {
		JPanel itemframe = ItemFrame();
		JLabel liguelabel = new JLabel("Entrer le nom de la ligue : ");
		ligue.setMaximumSize(new Dimension(150, 20));
		ligue.addKeyListener(getKeyListener());
		itemframe.add(liguelabel);
		itemframe.add(ligue);
		return itemframe;
	}
	private JPanel ValiderRetour() {
		JPanel itemframe = ItemFrame();
		valider.setEnabled(false);
		valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AddLigue();
				
			}
		});
		itemframe.add(valider);
		return itemframe;
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
	
	private void AddLigue() {
		try {
			gestionPersonnel.addLigue(ligue.getText());
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rootframe.dispose();
		displayLigue.GenerateListe();
	}

}
