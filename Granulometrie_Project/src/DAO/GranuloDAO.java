package DAO;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import javax.imageio.*;

import java.awt.Graphics2D;
import java.awt.image.*;

import Controller.CtrlViewResult;
import Model.ImageDB;
import Model.ParameterDB;
import app.Measure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.*;

/**
 * @author Noe,Hreasvik,Joey
 *
 */
public class GranuloDAO {

	/**
	 * The constructor will connect to the database by using a url
	 * <a href="#{@link}">{@link "jdbc:mysql://localhost/Granulometrie"}</a>, a
	 * login (root) and a password. It throws a ClassNotFoundException exception if
	 * the JDBC driver attempts to load a class but the JAR file is not present.
	 * 
	 * @param url
	 * @param login
	 * @param password
	 * @throws ClassNotFoundException
	 * @see SingleConnection.java
	 */
	public GranuloDAO(String url, String login, String password) throws ClassNotFoundException, SQLException {
		this.setUrl(url);
		this.setLogin(login);
		this.password = password;
		this.connection = SingleConnection.getInstance(url, login, password);
	}

	/**
	 * This String will contain the url to our database
	 */
	private String url;

	/**
	 * This String will contain the login so that we can connect to our database
	 */
	private String login;

	/**
	 * This String will contain the password so that we can connect to our database
	 */
	private String password;

	/**
	 * This interface will connect to our database by using the method
	 * getinstance(). It represents a database connection to a relational database.
	 * 
	 * @see SingleConnection.java
	 */
	private Connection connection;

	/**
	 * save all the result in database
	 * 
	 * @param ctrlViewResult
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void insertData(CtrlViewResult ctrlViewResult) throws ClassNotFoundException, SQLException, IOException {
		insertImageTable(ctrlViewResult);
		insertParameter(ctrlViewResult);
		InsertGrains(ctrlViewResult);
	}

	/**
	 * use for insert image in Blob type
	 * 
	 * @param image
	 * @return Image in byte
	 */
	private ByteArrayInputStream FormatImageToBlob(java.awt.Image image) {
		BufferedImage bi = (BufferedImage)image;
		Graphics2D g2d = bi.createGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "png", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				baos.close();
			} catch (Exception e) {
			}
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		return bais;

	}

	/**
	 * format Blob type in Image;
	 * 
	 * @param image
	 * @return Image in byte
	 */
	private Image FormatBlobtoImage(Blob blob) throws SQLException {
		InputStream is = blob.getBinaryStream();
		return new Image(is);

	}

	/**
	 * Insert Image and Image info from CtrlViewResult in Image mysql Table;
	 * 
	 * @param ctrlViewResult
	 */
	public void insertImageTable(CtrlViewResult ctrlViewResult)
			throws SQLException, IOException, ClassNotFoundException {
		if (FindIdImageByImage(ctrlViewResult.getOriginalImage()) == -1) {
			PreparedStatement ps = this.connection.prepareStatement(
					"INSERT INTO image (image,longueur,largeur,Grossisement,Commentaire) VALUES (?,?,?,?,?)");
			this.connection.setAutoCommit(true);
			ps.setBlob(1, FormatImageToBlob(ctrlViewResult.getOriginalImage()));
			ps.setFloat(2, (float) ctrlViewResult.getOriginalImage().getHeight());
			ps.setFloat(3, (float) ctrlViewResult.getOriginalImage().getHeight());
			ps.setInt(4, 1); // TODO Replace the value '1' by the value of `Grossissement`
			ps.setString(5, ctrlViewResult.getImageComment().getText());
			ps.executeUpdate();
			ps.close();
		} else {
			Statement smt = this.connection.createStatement();
			smt.executeUpdate("UPDATE Image SET commentaire='" + ctrlViewResult.getImageComment().getText()
					+ "' WHERE idImage=" + FindIdImageByImage(ctrlViewResult.getOriginalImage()) + ";");

		}

	}

	/**
	 * Insert Parameter of an image traitement from CtrlViewResult in Image mysql
	 * Table;
	 * 
	 * @param ctrlViewResult
	 */
	public void insertParameter(CtrlViewResult ctrlViewResult)
			throws SQLException, IOException, ClassNotFoundException {
		if (FindIdImageByImage(ctrlViewResult.getOriginalImage()) != -1) {
			if (FindParameter(ctrlViewResult) == -1) {
				PreparedStatement ps = this.connection.prepareStatement(
						"INSERT INTO Parametrage (TailleMin,TailleMax,nbCategoriesTaille,nbCategoriesSurface,Courbe1,Courbe2,DateCalcul,HeureCalcul,commentaire,idImage) VALUES (?,?,?,?,?,?,?,?,?,?)");
				this.connection.setAutoCommit(true);
				ps.setFloat(1, (float) ctrlViewResult.getGranuloModel().getSizeGrainMin());
				ps.setFloat(2, (float) ctrlViewResult.getGranuloModel().getSizeGrainMax());
				ps.setInt(3, ctrlViewResult.getGranuloModel().getClusters().size());
				ps.setInt(4, ctrlViewResult.getGranuloModel().getClustersSurface().size());
				ps.setBlob(5, FormatImageToBlob(ctrlViewResult.getGraphSizeImage()));
				ps.setBlob(6, FormatImageToBlob(ctrlViewResult.getGraphSizeImage()));
				ps.setDate(7, Date.valueOf(ctrlViewResult.getGranuloModel().getDate()));
				ps.setTime(8, Time.valueOf(ctrlViewResult.getGranuloModel().getTime()));
				ps.setString(9, ctrlViewResult.getComment().getText());
				ps.setInt(10, FindIdImageByImage(ctrlViewResult.getOriginalImage()));
				ps.executeUpdate();
				ps.close();
			} else {
				Statement smt = this.connection.createStatement();
				smt.executeUpdate(
						"UPDATE Parametrage SET DateCalcul='" + Date.valueOf(ctrlViewResult.getGranuloModel().getDate())
								+ "',HeureCalcul='" + Time.valueOf(ctrlViewResult.getGranuloModel().getTime())
								+ "' ,commentaire='" + ctrlViewResult.getComment().getText() + "' WHERE idParametrage="
								+ FindParameter(ctrlViewResult) + ";");
			}
		}
	}

	private int FindParameter(CtrlViewResult ctrlViewResult) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement ps = this.connection.prepareStatement(
				"SELECT * FROM parametrage WHERE TailleMin=? AND TailleMax=? AND nbCategoriesTaille=? AND nbCategoriesSurface=?  AND idImage=?");
		ps.setFloat(1, (float) ctrlViewResult.getGranuloModel().getSizeGrainMin());
		ps.setFloat(2, (float) ctrlViewResult.getGranuloModel().getSizeGrainMax());
		ps.setInt(3, ctrlViewResult.getGranuloModel().getClusters().size());
		ps.setInt(4, ctrlViewResult.getGranuloModel().getClustersSurface().size());
		ps.setInt(5, FindIdImageByImage(ctrlViewResult.getOriginalImage()));
		ResultSet r = ps.executeQuery();
		try {
			r.next();
			return r.getInt("idParametrage");
		} catch (java.sql.SQLException e) {
			return -1;
		}
	}

	private int FindIdImageByImage(java.awt.Image image) throws SQLException, IOException, ClassNotFoundException {
		PreparedStatement ps = this.connection.prepareStatement("SELECT idImage FROM Image WHERE image=?");
		this.connection.setAutoCommit(true);
		ps.setBlob(1, FormatImageToBlob(image));
		ResultSet r = ps.executeQuery();
		try {
			r.next();
			return r.getInt("idImage");
		} catch (java.sql.SQLException e) {
			return -1;
		}
	}

	/**
	 * Insert Parameter of an image traitement from CtrlViewResult in Image mysql
	 * Table;
	 * 
	 * @param ctrlViewResult
	 */
	public void InsertGrains(CtrlViewResult ctrlViewResult) throws ClassNotFoundException, SQLException, IOException {

		if (!isImageHadGrain(FindIdImageByImage(ctrlViewResult.getOriginalImage()))) {
			this.connection.setAutoCommit(true);
			System.out.println(FindParameter(ctrlViewResult));
			int idImage = FindIdImageByImage(ctrlViewResult.getOriginalImage());
			for (Measure measure : ctrlViewResult.getGranuloModel().getMeasures()) {
				PreparedStatement ps = this.connection.prepareStatement(
						"INSERT INTO grain (centreX,centreY,longueur,largeur,circularite,Surface,idImage) VALUES (?,?,?,?,?,?,?)");
				ps.setFloat(1, (float) measure.getCentre_x());
				ps.setFloat(2, (float) measure.getCentre_y());
				ps.setFloat(3, measure.getHeight());
				ps.setFloat(4, measure.getWidth());
				ps.setFloat(5, (float) measure.getRoundness());
				ps.setFloat(6, (float) measure.getAire());
				ps.setInt(7, idImage);
				ps.executeUpdate();
			}
		}
	}

	private boolean isImageHadGrain(int idImage) throws SQLException, IOException, ClassNotFoundException {
		PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM grain WHERE idImage=?");
		this.connection.setAutoCommit(true);
		ps.setInt(1, idImage);
		ResultSet r = ps.executeQuery();
		return r.next();
	}

	/**
	 * @param idImage
	 * @return a ObservableList<ParameterDB> of all the Image row in the DB
	 * @throws SQLException
	 */
	public ObservableList<ImageDB> getImageTable() throws SQLException {
		Statement smt = this.connection.createStatement();
		ResultSet r = smt.executeQuery("Select * from Image");
		ObservableList<ImageDB> imageDBList = FXCollections.observableArrayList();
		while (r.next()) {
			imageDBList.add(new ImageDB(r.getInt(1), FormatBlobtoImage(r.getBlob(2)), r.getFloat(3), r.getFloat(4),
					r.getInt(5), r.getString(6)));
		}

		return imageDBList;
	}

	/**
	 * @param idImage
	 * @return a ObservableList<ParameterDB> of all the Parameter row in the DB
	 *         associed to this image id
	 * @throws SQLException
	 */
	public ObservableList<ParameterDB> getParameterTable(int idImage) throws SQLException {
		Statement smt = this.connection.createStatement();
		ResultSet r = smt.executeQuery("Select * from parametrage WHERE idImage=" + idImage + "");
		ObservableList<ParameterDB> ParameterTableList = FXCollections.observableArrayList();
		while (r.next()) {
			ParameterTableList.add(new ParameterDB(r.getInt(1), r.getFloat(2), r.getFloat(3), r.getInt(4), r.getInt(5),
					FormatBlobtoImage(r.getBlob(6)), FormatBlobtoImage(r.getBlob(7)), r.getDate(8).toString(),
					r.getTime(9).toString(), r.getString(10), r.getInt(11)));
		}

		return ParameterTableList;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return login of user
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	protected String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

}