package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import commandLine.EmployeConsole;
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
			System.out.println(root_sql);
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
			
			System.out.println(root_sql);
			/*new Employe(ligues.getInt(1), ligues.getString(2));*/
			
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
		
		try 
		{
			String requete = "select * from ligue";
			Statement instruction = connection.createStatement();
			ResultSet ligues = instruction.executeQuery(requete);
			while (ligues.next())
				gestionPersonnel.addLigue(ligues.getInt(1), ligues.getString(2));
		}
		catch (SQLException e)
		{
			System.out.println(e);
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
			instruction = connection.prepareStatement("insert into employes (nom, prenom, mail, admin, id_ligue) values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, employe.getNom());
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getMail());
			if (employe.estAdmin(employe.getLigue())) {
				instruction.setInt(4, 1);
			}else {
				instruction.setInt(4, 0);
			}
			instruction.setInt(5, (employe.getLigue()).getid());
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
}
