package jdbc;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import commandLine.EmployeConsole;
import commandLineMenus.Action;
import commandLineMenus.examples.employees.core.Employee;
import personnel.*;

public class JDBC implements Passerelle 
{
	Connection connection;

	public JDBC()
	{
		try
		{
			Class.forName(Credentials.getDriverClassName());
			connection = DriverManager.getConnection(Credentials.getUrl(), Credentials.getUser(), Credentials.getPassword());
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Pilote JDBC non installé.");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	@Override
	public void select_root(Employe root) throws SauvegardeImpossible {
		try 
		{
			String requete_select = "select * from employes where admin = 2 ";
			Statement instruction = connection.createStatement();
			ResultSet root_sql = instruction.executeQuery(requete_select);
			if(!root_sql.next()) {
				PreparedStatement requete_insert  = connection.prepareStatement("insert into employes (nom, prenom, mail, password, admin) value (?, ?, ?, ?, ?)");
				requete_insert.setString(1, root.getNom());
				requete_insert.setString(2, root.getPrenom());
				requete_insert.setString(3, root.getMail());
				requete_insert.setString(4, root.getPassword());
				requete_insert.setInt(5, 2);
				requete_insert.executeUpdate();
			}else {
				root.setid(root_sql.getInt(1));
				root.setNom(root_sql.getString(2));
				root.setPrenom(root_sql.getString(3));
				root.setMail(root_sql.getString(4));
				root.setPassword(root_sql.getString(5));
			}
		
			
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	@Override
	public GestionPersonnel getGestionPersonnel() 
	{
		GestionPersonnel gestionPersonnel = new GestionPersonnel();
		Ligue ligue = null;
		LocalDate depart = null;
		LocalDate arrivee = null;
		Employe employe = null;
		try 
		{
			String requete = "select * from ligue";
			Statement instruction = connection.createStatement();
			ResultSet ligues = instruction.executeQuery(requete);
			while (ligues.next())
				if(ligues != null) {
			 	ligue = gestionPersonnel.addLigue(ligues.getInt(1), ligues.getString(2));
				PreparedStatement instruction_employe;
				instruction_employe = connection.prepareStatement("select * from employes where id_ligue = ?");
				instruction_employe.setInt(1, ligue.getid());
				ResultSet employe_sql = instruction_employe.executeQuery();
				while(employe_sql.next()) {
					if(employe_sql.getDate(6) != null) {
						depart = (employe_sql.getDate(6)).toLocalDate();
					}
					if(employe_sql.getDate(7) != null) {
						arrivee = (employe_sql.getDate(7)).toLocalDate();
					}
					
					employe = ligue.addEmploye(employe_sql.getInt(1), employe_sql.getString(2), employe_sql.getString(3), employe_sql.getString(4), employe_sql.getString(5), depart, arrivee);
					
					if(employe_sql.getInt(8) == 1) {
						ligue.setAdministrateur(employe);
					}
				}
				}
				//ligue.addEmploye(id, nom, prenom, mail, password)
		}
		catch (SQLException e)
		{
			System.out.println(e);
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gestionPersonnel;
	}

	@Override
	public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel) throws SauvegardeImpossible 
	{
		close();
	}
	
	public void close() throws SauvegardeImpossible
	{
		try
		{
			if (connection != null)
				connection.close();
		}
		catch (SQLException e)
		{
			throw new SauvegardeImpossible(e);
		}
	}
	
	@Override
	public int insert(Ligue ligue) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("insert into ligue (nom_ligue) values(?)", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, ligue.getNom());		
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
		
	}
	
	@Override
	public int insert(Employe employe) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("insert into employes (nom, prenom, mail, password, admin, id_ligue) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, employe.getNom());
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getMail());
			instruction.setString(4, employe.getPassword());
			if (employe.estAdmin(employe.getLigue())) {
				instruction.setInt(5, 1);
			}else {
				instruction.setInt(5, 0);
			}
			instruction.setInt(6, (employe.getLigue()).getid());
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
		
	}
	
	@Override
	public void update(Employe employe) throws SauvegardeImpossible 
	{		
			PreparedStatement instruction;
			
		try 
		{
			instruction = connection.prepareStatement("update employes set nom = ?, prenom = ?, mail = ?, password = ?, datedepart = ?, datearrivee = ? where id_employe = ?", Statement.RETURN_GENERATED_KEYS);
			
			instruction.setString(1, employe.getNom());
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getMail());
			instruction.setString(4, employe.getPassword());
			
			if(employe.getDateDepart() == null) {
				instruction.setString(5, null);
			}else {
				instruction.setDate(5, Date.valueOf(employe.getDateDepart()));
			}
			
			if(employe.getDateArrivee() == null) {
				instruction.setString(6, null);
			}else {
				System.out.println(employe.getDateArrivee());
				instruction.setDate(6, Date.valueOf(employe.getDateArrivee()));
			}
				
			instruction.setInt(7, employe.getid());
			
			instruction.executeUpdate();
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
		
	}
	
	public void set_admin(Employe employe) throws SauvegardeImpossible  {
		PreparedStatement instruction1;
		PreparedStatement instruction2;
		try {
			if(employe.getLigue() != null) {
			instruction1 = connection.prepareStatement("update employes set admin = 0 where admin = 1 and id_ligue = ?", Statement.RETURN_GENERATED_KEYS);
			instruction1.setInt(1, employe.getLigue().getid());
			instruction1.executeUpdate();
			
			instruction2 = connection.prepareStatement("update employes set admin = 1 where id_employe = ?", Statement.RETURN_GENERATED_KEYS);
			instruction2.setInt(1, employe.getid());
			instruction2.executeUpdate();
			
			}
		} catch (SQLException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	
	
	public void remove(Employe employe) throws SauvegardeImpossible  {
		PreparedStatement instruction1;
		try {
			instruction1 = connection.prepareStatement("delete from employes where id_employe = ?", Statement.RETURN_GENERATED_KEYS);
			instruction1.setInt(1, employe.getid());
			instruction1.executeUpdate();
			
		} catch (SQLException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	
	public void remove(Ligue ligue) throws SauvegardeImpossible  {
		PreparedStatement instruction1;
		PreparedStatement instruction2;
		try {
			instruction2 = connection.prepareStatement("delete from employes where id_ligue = ?", Statement.RETURN_GENERATED_KEYS);
			instruction2.setInt(1, ligue.getid());
			instruction2.executeUpdate();
			
			instruction1 = connection.prepareStatement("delete from ligue where id_ligue = ?", Statement.RETURN_GENERATED_KEYS);
			instruction1.setInt(1, ligue.getid());
			instruction1.executeUpdate();
			

			
			
		} catch (SQLException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	
	


}
