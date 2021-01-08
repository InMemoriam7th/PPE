package testsUnitaires;

import static org.junit.Assert.*;
import org.junit.Test;

import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;

import java.time.LocalDate;

public class testDate {
	
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
		
	 @Test
	 public void testDateArrivee() throws SauvegardeImpossible
	 {
		 Ligue ligue = gestionPersonnel.addLigue("Flechettes");
		 Employe employe = ligue.addEmploye("Bouchard", "Gerard", "g.bouchard@gmail.com", "azerty"); 
		 LocalDate date_now = LocalDate.now();
		 employe.setDateArrivee(date_now);
		 assertEquals("Test de la fonction DateArrivee", date_now , employe.getDateArrivee());
	 }
}
