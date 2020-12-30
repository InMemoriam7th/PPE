package jdbc;

public class Credentials 
{
	private static String driver ="mysql",
			driverClassName = "com.mysql.cj.jdbc.Driver",
			host = "151.80.111.190", 
			port ="3306",
			database ="s2_PPE",
			user = "u2_5AedczFXub",
			password = "h+MMMXA@7zUvpSwLg@2B+CNb";
	
	static String getUrl() 
	{
		return "jdbc:" + driver + "://" + host + "/" + database ;
	}
	
	static String getDriverClassName()
	{
		return driverClassName;
	}
	
	static String getUser() 
	{
		return user;
	}

	static String getPassword() 
	{
		return password;
	}
}
