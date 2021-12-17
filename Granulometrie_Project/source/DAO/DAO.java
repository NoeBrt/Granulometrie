package DAO;

import java.sql.Connection;

import Model.GranuloData;
import javafx.scene.image.Image;

/**
 * 
 */
public class DAO {

	/**
	 * The constructor will connect to the database by using a url <a href="#{@link}">{@link "jdbc:mysql://localhost/Granulometrie"}</a>, 
	 * a login (root) and a password. It throws a ClassNotFoundException exception if the JDBC driver attempts to load a class 
	 * but the JAR file is not present.   
	 * @param url
	 * @param login
	 * @param password
	 * @throws ClassNotFoundException
	 * @see SingleConnection.java 
	 */
	public DAO() throws ClassNotFoundException {
		this.url = "jdbc:mysql://localhost/Granulometrie";
		this.login = "root";
		this.password = "";
		connection = SingleConnection.getInstance(url, login, password);
	}

	/**
	 *This String will contain the url to our database
	 */
	private String url;

	/**
	 *This String will contain the login so that we can connect to our database
	 */
	private String login;

	/**
	 *This String will contain the password so that we can connect to our database
	 */
	private String password;

	/**
	 *This interface will  connect to our database by using the method getinstance().
	 *It represents a database connection to a relational database.
	 *@see SingleConnection.java 
	 */
	private Connection connection;

	/**
	 * This method is for inserting the data about the grains in our database.
	 * It takes a parameter of type GranuloData which is a class.
	 * We can now use methods from GranuloData to get data about the grains and 
	 * implement it in our INSERT sql query.  
	 * @param data
	 * @see GranuloData.java
	 */
	public void insertData(GranuloData data) {
		// TODO implement here
	}
    
	/**
	 * This method is for fetching the table Images from our database.
	 * This table was made for storing the image of a grain.
	 * The method will send a specific SELECT sql query that will select the data.
	 */
	public void getTableImages() {
		// TODO implement here
	}

	/**
	 * This method is for fetching the table Grains from our database.
	 * This table was made for storing the data of a grain.
	 * The method will send a specific SELECT sql query that will select the data
	 */
	public void getTableGrains() {
		// TODO implement here
	}

	/**
	 * This method is for fetching the table Parametrage from our database.
	 * This table was made for storing the settings that was used to calculate the graphs of a grain. 
	 * The method will send a specific SELECT  sql query that will select the data
	 */
	public void getTableParametrage() {
		// TODO implement here
	}

	/**
	 * This method is for fetching the table famille from our database
	 * The method will send a specific SELECT sql query that will select the data
	 */
	public void getTableFamille() {
		// TODO implement here
	}

	/**
	 *This method is for inserting the data about the grains in our database.
	 *It takes a parameter of type Image which is a class.
	 *We can now use methods from Image to get the grain's image and 
	 *implement it in our SELECT sql query.  
	 * @param image
	 * @see Image.java
	 */
	
	public void getData(Image image) {
		// TODO implement here
	}

	/**
	 * This method was made to get the data of a selected image.
	 * It will send a specific SELECT sql query that will select the data by using the parameter index 
	 * which is the ID of an image. With The primary key of the image we can use the foreign keys 
	 * to get data about the image by using a JOIN in sql. 
	 * @param index
	 */
	public void getData(int index) {
		// TODO implement here
	}

	/**
	 * This method was made to delete the data of a selected image.
	 * It will send a specific DELETE sql query that will select the data by using the parameter index 
	 * which is the ID of an image. With The primary key of the image we can use the foreign keys 
	 * to delete the data about the image by using a JOIN in sql. 
	 * @param index
	 */
	public void deleteData(int index) {
		// TODO implement here
	}

	/**
	 * This method was made to insert data into the Parametrage table.  
	 * It will send a specific INSERT sql query that will insert the data by using the parameters index and image. 
	 * We can now use methods from the Classes GranuloData and Image to get data about the grains and 
	 * implement it in our INSERT sql query.
	 * @param data
	 * @param image
	 */
	public void insertParametrage(GranuloData data, Image image) {
		// TODO implement here
	}

	/**
	 * This method is for deleting data from the Parametrage table
	 * it will send a specific sql query that will delete the settings 
	 * used to calculate the graphs of a grain by using the image and the ID of a setting that is stored in Parametrage. 
	 * @param Image
	 * @param idParametrege
	 */
	public void DeleteParametrage(Image images, int idParametrege) {
		// TODO implement here
	}

	/**
	 * This method is for updating data in the Parametrage table.
	 * It will send a specific UPDATE sql query that will update the settings used to calculate the graphs of a grain
	 * by using the image and the ID of a setting that is stored in Parametrage, if the user changed the settings.
	 * @param Image
	 * @param idParametrage
	 */
	public void UpdateParametrage(Image Image, int idParametrage) {
		// TODO implement here
	}

}