package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class SingleConnection {
	private static Connection con;

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

	public static Connection getInstance(String url, String login, String pwd) throws ClassNotFoundException {
		if (con == null) {
			new SingleConnection(url, login, pwd) ;
		}
		return con;
		
	}

}
