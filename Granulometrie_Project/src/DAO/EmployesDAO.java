package crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.result.IntegerValueFactory;

import DAO.SingleConnection;

public class EmployesDAO {
	private String url;
	private String login;
	private String pwd;
	private Connection cn;

	public EmployesDAO(String url, String login, String pwd) throws ClassNotFoundException {
		this.url = url;
		this.login = login;
		this.pwd = pwd;
		cn = SingleConnection.getInstance(url, login, pwd);
	}

	public Employes findByID(int id) throws SQLException {
		Statement stmt = this.cn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM employes WHERE id = " +"'"+ id+"'");
		if (rs.next()==false) {
			return null;
		}
		return new Employes(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"), rs.getString("city"),
				rs.getInt("salary"));
	}

	public List<Employes> findAll(String city) throws SQLException {
		Statement stmt = this.cn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM employes WHERE city = " +"'"+ city+"'");
		List<Employes> listEmploye = new ArrayList<Employes>();
		while (rs.next()) {
			listEmploye.add(new Employes(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"),
					rs.getString("city"), rs.getInt("salary")));
		}
		return listEmploye;
	}

	public boolean insert(Employes e) throws SQLException {
		Statement stmt = this.cn.createStatement();
		if (findByID(e.getId())==null) {
		stmt.executeUpdate("INSERT INTO employes VALUES"+ "('"+e.getId()+"','"+e.getLastname()+"','"+e.getFirstname()+"','"+e.getCity()+"',"+e.getSalary()+")");}
			return (findByID(e.getId()).equals(e));	
	}
	public boolean update(Employes e) throws SQLException {
		Statement stmt = this.cn.createStatement();
		if (findByID(e.getId())==null) {
		stmt.executeUpdate("UPDATE employes set lastname = '"+e.getLastname()+"', firstname='"+e.getFirstname()+"', city='"+e.getCity()+"', salary='"+e.getSalary()+"' WHERE id="+e.getId());}
			return (findByID(e.getId()).equals(e));	
	}
}
