package DAO;

import java.sql.Connection;

import Model.GranuloData;
import javafx.scene.image.Image;

/**
 * 
 */
public class DAO {

	/**
	 * @param url
	 * @param login
	 * @param password
	 * @throws ClassNotFoundException 
	 */
	public DAO() throws ClassNotFoundException {
		this.url = "jdbc:mysql://localhost/Granulometrie";
		this.login = "root";
		this.password = "";
		connection = SingleConnection.getInstance(url, login, password);
	}

	/**
	 * 
	 */
	private String url;

	/**
	 * 
	 */
	private String login;

	/**
	 * 
	 */
	private String password;

	/**
	 * 
	 */
	private Connection connection;

	/**
	 * @param data
	 */
	public void insertData(GranuloData data) {
		// TODO implement here
	}

	/**
	 * This method is for fetching the table Images from our database
	 * it will send a specific sql query that will select the data
	 */
	public void getTableImages() {
		// TODO implement here
	}

	/**
	 * This method is for fetching the table Grains from our database
	 * it will send a specific sql query that will select the data
	 */
	public void getTableGrains() {
		// TODO implement here
	}

	/**
	 * This method is for fetching the table Grains from our database
	 * it will send a specific sql query that will select the data
	 */
	public void getTableParametrage() {
		// TODO implement here
	}

	/**
	 * This method is for fetching the table famille from our database
	 * it will send a specific sql query that will select the data
	 */
	public void getTableFamille() {
		// TODO implement here
	}

	/**
	 * This method is for fetching our database
	 * @param image
	 */
	public void getData(Image image) {
		// TODO implement here
	}

	/**
	 * This method is for fetching our database
	 * @param index
	 */
	public void getData(int index) {
		// TODO implement here
	}

	/**
	 * This method is for fetching our database
	 * @param index
	 */
	public void deleteData(int index) {
		// TODO implement here
	}

	/**
	 * This method is for fetching our database now
	 * @param data
	 * @param image
	 */
	public void insertParametrage(GranuloData data, Image image) {
		// TODO implement here
	}

	/**
	 * This method is for deleting data from the Parametrage table
	 * it will send a specific sql query that will select the data
	 * @param Image
	 * @param idParametrege
	 */
	public void DeleteParametrage(Image images, int idParametrege) {
		// TODO implement here
	}

	/**
	 * This method is for updating data in the Parametrage table
	 * it will send a specific sql query that will select the data
	 * @param Image
	 * @param idParametrage
	 */
	public void UpdateParametrage(Image Image, int idParametrage) {
		// TODO implement here
	}

}