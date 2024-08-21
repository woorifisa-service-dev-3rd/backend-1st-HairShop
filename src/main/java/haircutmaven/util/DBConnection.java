package haircutmaven.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	public static Connection getConnection() throws IOException, SQLException
	{
		Properties properties = new Properties();
		FileInputStream fs= new FileInputStream("src/main/java/haircutmaven/DBsetting");
		properties.load(fs);
		final String db_url = properties.getProperty("db_url");
		final String username = properties.getProperty("username");
		final String password = properties.getProperty("password");
		final String database = properties.getProperty("database");
		return DriverManager.getConnection(db_url+database, username, password);
		
	}
}
