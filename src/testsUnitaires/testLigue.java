package testsUnitaires;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import exception.DateException;
import personnel.*;

class testLigue 
{
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	
	@Test
	void createLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());
		ligue.remove();
	}
	
	@Test
	 public void testNom() throws SauvegardeImpossible
	 {
		 Ligue ligue = gestionPersonnel.addLigue("Flechettes"); 
		 ligue.setNom("billard");
		 assertEquals("Test de la fonction get et set nom ligue", "billard" , ligue.getNom());
		 ligue.remove();
	 }
	
	 @Test
	 public void testAdministrateurligue() throws SauvegardeImpossible
	 {
		 Ligue ligue = gestionPersonnel.addLigue("Flechettes");
		 Employe employe = ligue.addEmploye("Bouchard", "Gerard", "g.bouchard@gmail.com", "azerty", null, null); 
		 ligue.setAdministrateur(employe);
		 assertEquals("Test de la fonction setAdministrateur et getAdministrateur", employe , ligue.getAdministrateur());
		 ligue.remove();
	 }


	@Test
	void addEmploye() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", null, null); 
		assertEquals(employe, ligue.getEmployes().first());
		ligue.remove();
	}
	
	@Test
	 public void testremove() throws SauvegardeImpossible
	 {
		 Ligue ligue1 = gestionPersonnel.addLigue("Billard"); 
		 Ligue ligue2 = gestionPersonnel.addLigue("Flechettes");
		 ligue1.remove();
		 boolean result = false;
		 for(Ligue ligue : gestionPersonnel.getLigues()) {
			 if(ligue == ligue1) {
				 result = true;
			 }
		 }
		 assertFalse("Test de la fonction remove", result);
		 ligue2.remove();
	 }
	
	@Test
	 public void testcompareTo() throws SauvegardeImpossible
	 {
		 Ligue ligue1 = gestionPersonnel.addLigue("Billard"); 
		 Ligue ligue2 = gestionPersonnel.addLigue("Flechettes");
		 assertEquals("Test de la fonction compareTo", 0 , ligue1.compareTo(ligue1));
		 assertEquals("Test de la fonction compareTo", -4 , ligue1.compareTo(ligue2));
		 ligue1.remove();
		 ligue2.remove();
	 }
	
	@Test
	 public void testtoString() throws SauvegardeImpossible
	 {
		 Ligue ligue = gestionPersonnel.addLigue("Billard"); 
		 assertEquals("Test de la fonction compareTo", "Billard" , ligue.toString());
		 ligue.remove();
	 }

}
