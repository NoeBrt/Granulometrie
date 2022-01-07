package DAO;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.*;
import Controller.CtrlViewResult;
import Model.GranuloData;
import app.Measure;
import javafx.embed.swing.*;
import javafx.scene.image.*;

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
		this.connection = SingleConnection.getInstance(url, login, password);
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
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @see GranuloData.java
	 */
	public void insertData(GranuloData data) throws SQLException, IOException, ClassNotFoundException {
		Statement stmt = null;
		PreparedStatement ps = null;
		int idImage = 0;
		int idGrain = 0;
		boolean equal = false;
		
	// Set Arrays
		ArrayList<Long> common_char = new ArrayList<Long>();
	
	// Driver Manager
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection(url,login,password);
		
	// Strings
		String insert_img = "INSERT INTO image VALUES (?,?,?,?,?,?,?)";
		String getIdImg = "SELECT idImage FROM image WHERE image=?";
		
	// DL Images
		File import_img = new File(data.getImage().getUrl());
		FileInputStream fis = new FileInputStream(import_img);
		File temp_img = new File("src/tmp.jpg");
		String getLen = "SELECT image FROM image WHERE LENGTH(image)="+import_img.length();
		ps = this.connection.prepareStatement(getLen);
		ResultSet rs = ps.executeQuery();
		while (rs.next() && !equal) {
			if (temp_img.exists()) {
				temp_img.delete();
			}
			FileOutputStream fos = new FileOutputStream(temp_img);
			byte[] buffer = new byte[1];
			InputStream is = rs.getBinaryStream(1);
			while (is.read(buffer) > 0) {
				fos.write(buffer);
			}
			fos.close();
			temp_img.delete();
			BufferedInputStream fis1 = new BufferedInputStream(new FileInputStream(import_img));
			BufferedInputStream fis2 = new BufferedInputStream(new FileInputStream(temp_img));
			int ch = 0;
			for (long pos = 1 ; (ch = fis1.read()) != -1 ; pos++) {
				if (ch == fis2.read()) {
					//System.out.println(pos);
					common_char.add(pos);
				}
			}
			if (common_char.size() != import_img.length()) {
				equal = false;
			}
			else {
				equal = true;
			}
			fis1.close();
			fis2.close();
		}
		if (!equal) {
			this.connection.setAutoCommit(false);
			ps = this.connection.prepareStatement(insert_img);
			ps.setNull(1, 0);
			ps.setBinaryStream(2, fis, (int) import_img.length());
			ps.setFloat(3, (float) data.getImage().getHeight());
			ps.setFloat(4, (float) data.getImage().getWidth());
			ps.setInt(5, 1); // TODO Replace the value '1' by the value of `Grossissement`
			ps.setString(6, data.getMeasures().get_image_name());
			ps.setNull(7, 0);
			ps.executeUpdate();
			this.connection.commit();
			this.connection.setAutoCommit(true);
			fis.close();
		}
		
		this.connection.setAutoCommit(false);
		ps = this.connection.prepareStatement(getIdImg);
		ps.setBinaryStream(1, fis, (int) import_img.length());
		rs = ps.executeQuery();
		this.connection.commit();
		this.connection.setAutoCommit(true);
		while (rs.next()) {
			idImage = rs.getInt("idImage");
		}

		String getIdGrain = "SELECT idGrain FROM grain WHERE idImage="+idImage;
		stmt = this.connection.createStatement();
		rs = stmt.executeQuery(getIdGrain);
		while(rs.next()) {
			idGrain = rs.getInt("idGrain");
		}
		stmt.close();
		rs.close();
		if (idGrain == 0) {
			String insert_grain = "INSERT INTO grain VALUES";
			stmt = this.connection.createStatement();
			for (Measure m :data.getMeasuresAfterScale()) {
				float centreX = (float) m.getCentre_x();
				float centreY = (float) m.getCentre_y();
				float longueur = m.getWidth();
				float largeur = m.getHeight();
				float circularite = (float) m.getRoundness();
				float surface = (float) m.getAire();
				String data_to_insert1 = centreX+","+centreY+","+longueur+",";
				String data_to_insert2 = largeur+","+circularite+","+surface+","+idImage;
				stmt.addBatch(insert_grain+"(NULL,"+data_to_insert1+data_to_insert2+")");
			}
			stmt.executeBatch();
			this.connection.commit();
			this.connection.setAutoCommit(true);
		}
		this.connection.close();
	}
    
	/**
	 * This method is for fetching the table Images from our database.
	 * This table was made for storing the image of a grain.
	 * The method will send a specific SELECT sql query that will select the data.
	 */
	public LinkedList<TableResults> getTableImages() throws SQLException, IOException, ClassNotFoundException {
		LinkedList<TableResults> listResultat = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection(url,login,password);

		Statement stmt = this.connection.createStatement();
		String sql = "SELECT * FROM image";
		ResultSet rs = stmt.executeQuery(sql);



		while (rs.next())
		{
			int idImage = rs.getInt("idImage");

			Blob Image = rs.getBlob("Image");
			InputStream in = Image.getBinaryStream();  
			BufferedImage bufferedImage = ImageIO.read(in);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null );

			float longueur = rs.getFloat("longueur");
			float largeur = rs.getFloat("largeur");
			int Grossisement = rs.getInt("Grossisement");
			String Commentaire = rs.getString("Commentaire");
			int idFamille = rs.getInt("idFamille");

			listResultat.add(new TableResults(idImage,Grossisement,idFamille,longueur,largeur,image,Commentaire));
		}
		stmt.close();
		return listResultat;
	}

	/**
	 * This method is for fetching the table Grains from our database.
	 * This table was made for storing the data of a grain.
	 * The method will send a specific SELECT sql query that will select the data
	 * @return 
	 * @throws ClassNotFoundException 
	 */
	public LinkedList<TableResults> getTableGrains() throws SQLException, ClassNotFoundException {
		LinkedList<TableResults> listResultat = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection(url,login,password);

		Statement stmt = this.connection.createStatement();
		String sql = "SELECT * FROM grain";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next())
		{
			int idGrain = rs.getInt("idGrain");
			float centreX = rs.getFloat("centreX");
			float centreY = rs.getFloat("centreY");
			float longueur = rs.getFloat("longueur");
			float largeur = rs.getFloat("largeur");
			float cicularite = rs.getFloat("circuarite");
			float surface = rs.getFloat("Surface");
			int idImage = rs.getInt("idImage");

			listResultat.add(new TableResults(idGrain,idImage,longueur,largeur,centreX,centreY,cicularite,surface));
		}


		stmt.close();
		return listResultat;

	}

	/**
	 * This method is for fetching the table Parametrage from our database.
	 * This table was made for storing the settings that was used to calculate the graphs of a grain. 
	 * The method will send a specific SELECT  sql query that will select the data
	 * @return 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 *
	public LinkedList<TableResults> getTableParametrage() throws SQLException, IOException, ClassNotFoundException {
		LinkedList<TableResults> listResultat = new LinkedList<TableResults>();

		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection(url,login,password);

		Statement stmt = this.connection.createStatement();
		String sql = "SELECT * FROM parametrage";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next())
		{
			int idParametrage = rs.getInt("idParametrage");
			float tailleMin = rs.getFloat("TailleMin");
			float tailleMax = rs.getFloat("TailleMax");
			int nbCategories = rs.getInt("nbCategories");

			Blob courbe1 = rs.getBlob("Courbe1");
			InputStream in = courbe1.getBinaryStream();  
			BufferedImage bufferedCourbe1 = ImageIO.read(in);
			Image Courbe1 = SwingFXUtils.toFXImage(bufferedCourbe1, null );

			Blob courbe2 = rs.getBlob("Courbe2");
			InputStream in2 = courbe2.getBinaryStream();  
			BufferedImage bufferedCourbe2 = ImageIO.read(in2);
			Image Courbe2  = SwingFXUtils.toFXImage(bufferedCourbe2, null );

			String DateCalcul = rs.getString("DateCalcul");
			Time HeureCalcul = rs.getTime("HeureCalcul");
			int idImage = rs.getInt("idImage");

			listResultat.add(new TableResults(idParametrage,nbCategories,idImage,tailleMin,tailleMax,Courbe1,Courbe2,DateCalcul,HeureCalcul));

		}

		stmt.close();
		return listResultat;
	}
*/
	/**
	 * This method was made to insert data into the Parametrage table.  
	 * It will send a specific INSERT sql query that will insert the data by using the parameters index and image. 
	 * We can now use methods from the Classes GranuloData and Image to get data about the grains and 
	 * implement it in our INSERT sql query.
	 * @param data
	 * @param image
	 */
	public void insertParametrage(GranuloData data) {
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection(url,login,password);
		
		int idImage = 0;
		File import_img = new File(data.getImage().getUrl());
		FileInputStream fis = new FileInputStream(import_img);
		String getIdImg = "SELECT idImage FROM image WHERE image=?";
		this.connection.setAutoCommit(false);
		PreparedStatement ps = this.connection.prepareStatement(getIdImg);
		ps.setBinaryStream(1, fis, (int) import_img.length());
		ResultSet rs = ps.executeQuery();
		this.connection.commit();
		this.connection.setAutoCommit(true);
		while (rs.next()) {
			idImage = rs.getInt("idImage");
		}
		
		String insert_param = "INSERT INTO parametrage VALUES (?,?,?,?,?,?,?,?,?)";
		this.connection.setAutoCommit(false);
		ps = this.connection.prepareStatement(insert_param);
		
		File courbe1 = new File (CtrlViewResult.graphSizeToImage().getUrl());
		File courbe2 = CtrlViewResult.graphSurfaceToImage();
		FileInputStream fis1 = new FileInputStream(courbe1);
		FileInputStream fis2 = new FileInputStream(courbe2);
		
		String date = java.time.LocalDate.now().toString();
		
		int hour = java.time.LocalTime.now().getHour();
		int minute = java.time.LocalTime.now().getMinute();
		int second = java.time.LocalTime.now().getSecond();
		String time = hour+":"+minute+":"+second;
		
		ps.setNull(1,0);
		ps.setFloat(2, data.getTailleGrainnMin());
		ps.setFloat(3, data.getTailleGrainMax());
		ps.setInt(4, data.getClusters().size());
		ps.setBinaryStream(5, fis1, (int) courbe1.length());
		ps.setBinaryStream(6, fis2, (int) courbe2.length());
		ps.setString(7, date);
		ps.setString(8, time);
		ps.setInt(9,  idImage);
		ps.executeUpdate();
		this.connection.commit();
		this.connection.setAutoCommit(true);
		this.connection.close();
	}

	/**
	 * This method is for deleting data from the Parametrage table
	 * it will send a specific sql query that will delete the settings 
	 * used to calculate the graphs of a grain by using the image and the ID of a setting that is stored in Parametrage. 
	 * @param Image
	 * @param idParametrege
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public void deleteParametrage(GranuloData data) throws FileNotFoundException, ClassNotFoundException, SQLException {
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		File import_img = new File(data.getImage().getUrl());
		FileInputStream fis = new FileInputStream(import_img);
		
		String getIdImg = "SELECT idImage FROM image WHERE image=?";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection(url,login,password);
		
		this.connection.setAutoCommit(false);
		ps = this.connection.prepareStatement(getIdImg);
		ps.setBinaryStream(1, fis, (int) import_img.length());
		rs = ps.executeQuery();
		rs.next();
		int idImage = rs.getInt("idImage");
		rs.close();
		
		String delParam = "DELETE FROM parametrage WHERE idImage="+idImage;
		
		stmt = this.connection.createStatement();
		stmt.executeQuery(delParam);
		
		this.connection.close();
	}

	/**
	 * This method is for updating data in the Parametrage table.
	 * It will send a specific UPDATE sql query that will update the settings used to calculate the graphs of a grain
	 * by using the image and the ID of a setting that is stored in Parametrage, if the user changed the settings.
	 * @param Image
	 * @param idParametrage
	 */
	// TODO HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
	public void updateParametrage(Image Image, int idParametrage) {
		
	}

}