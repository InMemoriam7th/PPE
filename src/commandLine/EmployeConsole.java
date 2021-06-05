package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import exception.DateException;
import personnel.Employe;

/**
 * Menu d'édition en ligne de commande des employées d'une ligue 
 */

public class EmployeConsole 
{
	private Option afficher(final Employe employe)
	{
		return new Option("Afficher l'employé", "l", () -> {System.out.println(employe);});
	}

	ListOption<Employe> editerEmploye()
	{
		return (employe) -> editerEmploye(employe);		
	}

	Option editerEmploye(Employe employe)
	{
			Menu menu = new Menu("Gérer le compte " + employe.getNom(), "c");
			menu.add(afficher(employe));
			menu.add(changerNom(employe));
			menu.add(changerPrenom(employe));
			menu.add(changerMail(employe));
			menu.add(changerPassword(employe));
			menu.add(dateDepart(employe));
			menu.add(dateArrivee(employe));
			menu.add(supprimerEmploye(employe));
			menu.addBack("q");
			menu.setAutoBack(true);
			return menu;
	}

	private Option changerNom(final Employe employe)
	{
		return new Option("Changer le nom", "n", 
				() -> {employe.setNom(getString("Nouveau nom : "));}
			);
	}
	
	private Option changerPrenom(final Employe employe)
	{
		return new Option("Changer le prénom", "p", () -> {employe.setPrenom(getString("Nouveau prénom : "));});
	}
	
	private Option changerMail(final Employe employe)
	{
		return new Option("Changer le mail", "e", () -> {employe.setMail(getString("Nouveau mail : "));});
	}
	
	private Option changerPassword(final Employe employe)
	{
		return new Option("Changer le password", "x", () -> {employe.setPassword(getString("Nouveau password : "));});
	}
	
	private Option dateDepart(final Employe employe)
	{
		return new Option("Changer la date de départ", "c", () -> {
				int day = Integer.parseInt(getString("Jour : "));
				int month = Integer.parseInt(getString("Mois : "));
				int year = Integer.parseInt(getString("Année : "));
				try {
					LocalDate date = LocalDate.of(year, month, day);
					employe.setDateDepart(date);
				}catch(DateTimeException e){
					System.out.println(e);
				}catch (DateException e) {
					System.out.println(e);
				}	
		});
	}
	
	private Option dateArrivee(final Employe employe)
	{
		return new Option("Changer la date d'arrivée", "w", () -> {
			int day = Integer.parseInt(getString("Jour : "));
			int month = Integer.parseInt(getString("Mois : "));
			int year = Integer.parseInt(getString("Année : "));
			try {
			LocalDate date = LocalDate.of(year, month, day);
			employe.setDateArrivee(date);
			}catch (DateTimeException e){
				System.out.println("Date impossible");
			} catch (DateException e) {
				System.out.println(e);
			}	
	    });
	}
	
	private Option supprimerEmploye(final Employe employe)
	{
		return new Option("Supprimer l'employé", "s", () -> {employe.remove();});
	}
}
