package personnel;

import java.io.Serializable;
import java.time.LocalDate;

import exception.DateException;

/**
 * Employé d'une ligue hébergée par la M2L. Certains peuvent 
 * être administrateurs des employés de leur ligue.
 * Un seul employé, rattaché à aucune ligue, est le root.
 * Il est impossible d'instancier directement un employé, 
 * il faut passer la méthode {@link Ligue#addEmploye addEmploye}.
 */

public class Employe implements Serializable, Comparable<Employe>
{
	private static final long serialVersionUID = 4795721718037994734L;
	private String nom, prenom, password, mail;
	private int id;
	private Ligue ligue;
	private GestionPersonnel gestionPersonnel;
	private LocalDate date_depart, date_arrivee;
	
	Employe(GestionPersonnel gestionPersonnel, Ligue ligue, String nom, String prenom, String mail, String password)
	{
		this.gestionPersonnel = gestionPersonnel;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.mail = mail;
		this.ligue = ligue;
		this.date_arrivee = null;
		this.date_depart = null;
	}
	
	Employe(GestionPersonnel gestionPersonnel, Ligue ligue, String nom, String prenom, String mail, String password, LocalDate date_depart, LocalDate date_arrivee)
	{
		this.gestionPersonnel = gestionPersonnel;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.mail = mail;
		this.ligue = ligue;
		this.date_depart = date_depart;
		this.date_arrivee = date_arrivee;
		
	}
	
	
	public void setid(int id)
	{
		this.id = id;
	}
	
	public int getid()
	{
		return id;
	}
	
	
	/**
	 * Retourne vrai ssi l'employé est administrateur de la ligue 
	 * passée en paramètre.
	 * @return vrai ssi l'employé est administrateur de la ligue 
	 * passée en paramètre.
	 * @param ligue la ligue pour laquelle on souhaite vérifier si this 
	 * est l'admininstrateur.
	 */
	
	public boolean estAdmin(Ligue ligue)
	{
		return ligue.getAdministrateur() == this;
	}
	
	/**
	 * Retourne vrai ssi l'employé est le root.
	 * @return vrai ssi l'employé est le root.
	 */
	
	public boolean estRoot()
	{
		return GestionPersonnel.getGestionPersonnel().getRoot() == this;
	}
	
	/**
	 * Retourne le nom de l'employé.
	 * @return le nom de l'employé. 
	 */
	
	public String getNom()
	{
		return nom;
	}

	/**
	 * Change le nom de l'employé.
	 * @param nom le nouveau nom.
	 */
	
	public void setNom(String nom)
	{
		this.nom = nom;
		this.update();
	}

	/**
	 * Retourne le prénom de l'employé.
	 * @return le prénom de l'employé.
	 */
	
	public String getPrenom()
	{
		return prenom;
	}
	
	/**
	 * Change le prénom de l'employé.
	 * @param prenom le nouveau prénom de l'employé. 
	 */

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
		this.update();
	}

	/**
	 * Retourne le mail de l'employé.
	 * @return le mail de l'employé.
	 */
	
	public String getMail()
	{
		return mail;
	}
	
	/**
	 * Change le mail de l'employé.
	 * @param mail le nouveau mail de l'employé.
	 */

	public void setMail(String mail)
	{
		this.mail = mail;
		this.update();
	}

	
	public LocalDate getDateArrivee(){
		
		return date_arrivee;
		
	}
	
	public void setDateArrivee(LocalDate date_arrivee) throws DateException{
		if(date_arrivee!=null) {
		LocalDate date_now = LocalDate.now();
		if(date_arrivee.isAfter(date_now) || date_arrivee.isEqual(date_now)) {
			if(this.date_depart == null) {
			this.date_arrivee = date_arrivee;
			this.update();
			}else {
				if(date_depart.isBefore(date_arrivee) || date_arrivee.isEqual(this.date_depart)) {
					this.date_arrivee = date_arrivee;
					this.update();
				}else {
					throw new DateException("La date d' arrivee ne peut pas �tre avant la date de d�part");
				}
			}
		}else{
			throw new DateException("La date d'arriv�e est impossible : " + date_arrivee);
		}
		}else {
			throw new DateException("La date d'arriv�e est null");
		}
		
	}
	
	
	public LocalDate getDateDepart(){
		
		return date_depart;
		
	}

	
	public void setDateDepart(LocalDate date_depart) throws DateException{
		if(date_depart!=null) {
		LocalDate date_now = LocalDate.now();
		if(date_depart.isAfter(date_now) || date_depart.isEqual(date_now)){
			if(this.date_arrivee == null) {
				this.date_depart = date_depart;
				this.update();
			}
			else {
				if(date_arrivee.isBefore(date_depart) || date_depart.isEqual(this.date_arrivee)) {
					this.date_depart = date_depart;
					this.update();
				}else {
					throw new DateException("La date de d�part ne peut pas �tre avant la date d'arriv�e");
				}
			}
		}else {
			throw new DateException("La date de d�part est impossible : " + date_depart);	
		}
		}else {
			throw new DateException("La date depart est null");
		}
	}

	/**
	 * Retourne vrai ssi le password passé en paramètre est bien celui
	 * de l'employé.
	 * @return vrai ssi le password passé en paramètre est bien celui
	 * de l'employé.
	 * @param password le password auquel comparer celui de l'employé.
	 */
	
	public boolean checkPassword(String password)
	{
		return this.password.equals(password);
	}

	/**
	 * Change le password de l'employé.
	 * @param password le nouveau password de l'employé. 
	 */
	
	public void setPassword(String password)
	{
		this.password = password;
		this.update();
	}

	public String getPassword()
	{
		return password;
	}
	
	/**
	 * Retourne la ligue à laquelle l'employé est affecté.
	 * @return la ligue à laquelle l'employé est affecté.
	 */
	
	public Ligue getLigue()
	{
		return ligue;
	}

	/**
	 * Supprime l'employé. Si celui-ci est un administrateur, le root
	 * récupère les droits d'administration sur sa ligue.
	 */
	
	public void remove()
	{
		Employe root = GestionPersonnel.getGestionPersonnel().getRoot();
		if (this != root)
		{
			if (estAdmin(getLigue()))
				getLigue().setAdministrateur(root);
			ligue.remove(this);
		}
		else
			throw new ImpossibleDeSupprimerRoot();
	}
	
	
	private void update() {
		try {
			gestionPersonnel.update(this);
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public int compareTo(Employe autre)
	{
		int cmp = getNom().compareTo(autre.getNom());
		if (cmp != 0)
			return cmp;
		return getPrenom().compareTo(autre.getPrenom());
	}
	
	@Override
	public String toString()
	{
		String res = "Nom : " + nom + ", Prenom : " + prenom + ", Mail : " + mail;
		if(this.date_arrivee != null)
			res += ", Date d'arriv�e : " + date_arrivee;
		
		if(this.date_depart != null)
			res += ", Date de d�part : " + date_depart;
		
		res += ", (";
		if (estRoot())
			res += "super-utilisateur";
		else
			res += ligue.toString();
		return res + ")";
	}
}
