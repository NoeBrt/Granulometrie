package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class SingleConnection {
	/**
     * 
     */
	private static Connection con;
	/**
	 * SingleConnection makes a connection with the Database 
	 * using a login and a password
     * @param url : Database URL
     * @param login : Login for the Database
     * @param pwd : Password for the database
     * This method will try to connect to the database named 
     * localhost using these parameters, and return an 
     * exception SQLException if the connection failed
     */
	private SingleConnection(String url, String login, String pwd) throws ClassNotFoundException {
		try {

			Class.forName("com.mysql.cj.jdbc.MysqlDataSource");
			MysqlDataSource mysqlDS = new MysqlDataSource();
			mysqlDS.setUser(login);
			mysqlDS.setPassword(pwd);
			mysqlDS.setServerName("localhost");
			mysqlDS.setPort(3306);
			mysqlDS.setDatabaseName(url.substring(23));
			con = mysqlDS.getConnection();
			// TODO Auto-generated catch block
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
	/**
	 * Connection call the private method SingleConnection
     * @param url : Database URL
     * @param login : Login for the Database
     * @param pwd : Password for the database
     * This method will call SingleConnection with these
     * parameters as methode's parameters, and return the 
     * value of con
     */
	public static Connection getInstance(String url, String login, String pwd) throws ClassNotFoundException {
		if (con == null) {
			new SingleConnection(url, login, pwd) ;
		}
		return con;
		
	}

}
