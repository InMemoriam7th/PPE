package testsUnitaires;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import exception.DateException;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;

import java.time.LocalDate;

public class testEmploye {
	
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
		
	 @Test
	 public void testDateArrivee() throws SauvegardeImpossible, DateException
	 {
		 Ligue ligue = gestionPersonnel.addLigue("Flechettes");
		 Employe employe = ligue.addEmploye("Bouchard", "Gerard", "g.bouchard@gmail.com", "azerty"); 
		 LocalDate date_now = LocalDate.now();
		 employe.setDateArrivee(date_now);
		 assertEquals("Test de la fonction DateArrivee", date_now , employe.getDateArrivee());
		 employe.setDateArrivee(date_now.plusDays(10));
		 assertEquals("Test de la fonction DateArrivee", date_now.plusDays(10) , employe.getDateArrivee());
		 try {
		 employe.setDateArrivee(date_now.minusDays(10));
		 }catch (DateException e) {
			 assertThat(e.getMessage(), is("La date d'arrivée est impossible : " + date_now.minusDays(10)));
		}

		 employe.setDateDepart(date_now.plusDays(20));
		 
		 try {
		 employe.setDateArrivee(date_now);
		 }catch (DateException e) {
			 assertThat(e.getMessage(), is("La date d' arrivee ne peut pas être avant la date de départ"));
		}
		 
		 try {
		 employe.setDateArrivee(null);
		 }catch (DateException e) {
			 assertThat(e.getMessage(), is("La date d'arrivée est null"));
		}
		 
		 
	 }
	 @Test
	 public void testDateDepart() throws SauvegardeImpossible, DateException
	 {
		 Ligue ligue = gestionPersonnel.addLigue("Flechettes");
		 Employe employe = ligue.addEmploye("Bouchard", "Gerard", "g.bouchard@gmail.com", "azerty"); 
		 LocalDate date_now = LocalDate.now();
		 employe.setDateDepart(date_now);
		 assertEquals("Test de la fonction DateDepart", date_now , employe.getDateDepart());
		 employe.setDateDepart(date_now.plusDays(10));
		 assertEquals("Test de la fonction DateDepart", date_now.plusDays(10) , employe.getDateDepart());
		 
		 try {
		 employe.setDateDepart(date_now.minusDays(10));
		 }catch (DateException e) {
			 assertThat(e.getMessage(), is("La date de départ est impossible : " + date_now.minusDays(10)));
		}
		 
		 employe.setDateArrivee(date_now.plusDays(10));
		 try {
			 employe.setDateDepart(date_now);
		 }catch (DateException e) {
			 assertThat(e.getMessage(), is("La date de départ ne peut pas être avant la date d'arrivée"));
		}
		 
		 try {
		 employe.setDateDepart(null);
		 }catch (DateException e) {
			 assertThat(e.getMessage(), is("La date depart est null"));
		}
	 }
	 @Test
	 public void testNom() throws SauvegardeImpossible
	 {
		 Ligue ligue = gestionPersonnel.addLigue("Jeux Vidéos");
		 Employe employe = ligue.addEmploye("Cance", "Alexandre", "c.alexandre@gmail.com", "qwerty");
		 employe.setNom("Cance");
		 assertEquals("Test de la fonction set et get nom", "Cance", employe.getNom()); 
	 }
	 @Test
	 public void testPrenom() throws SauvegardeImpossible
	 {
		 Ligue ligue = gestionPersonnel.addLigue("Jeux Vidéos");
		 Employe employe = ligue.addEmploye("Raharison", "Kevin", "R.Kevin@gmail.com", "qwerty");
		 employe.setPrenom("Kevin");
		 assertEquals("test de la fonction set et get prenom", "Kevin", employe.getPrenom());
	 }
	 
}
