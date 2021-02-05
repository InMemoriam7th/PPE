package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;

import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import personnel.Employe;

public class EmployeConsole 
{
	private Option afficher(final Employe employe)
	{
		return new Option("Afficher l'employ�", "l", () -> {System.out.println(employe);});
	}

	ListOption<Employe> editerEmploye()
	{
		return (employe) -> editerEmploye(employe);		
	}

	Option editerEmploye(Employe employe)
	{
			Menu menu = new Menu("G�rer le compte " + employe.getNom(), "c");
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
		return new Option("Changer le pr�nom", "p", () -> {employe.setPrenom(getString("Nouveau pr�nom : "));});
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
		return new Option("Changer la date de d�part", "c", () -> {employe.setDateDepart(getString("Date de d�part : "));});
	}
	
	private Option dateArrivee(final Employe employe)
	{
		return new Option("Changer la date d'arriv�e", "v", () -> {employe.setDateArrivee(getString("Date d'arriv�e : "));});
	}
	
	private Option supprimerEmploye(final Employe employe)
	{
		return new Option("Supprimer l'employ�", "s", () -> {employe.remove();});
	}
}
