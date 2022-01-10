package DAO;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.text.*;
import java.util.*;
import javax.imageio.*;

import java.awt.Graphics2D;
import java.awt.image.*;

import Controller.CtrlView;
import Controller.CtrlViewResult;
import Model.GranuloData;
import app.Measure;
import javafx.embed.swing.*;
import javafx.scene.image.*;

/**
 * 
 */
public class GranulometrieDAO {

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
	public GranulometrieDAO(String url,String login,String password) throws ClassNotFoundException {
		this.url = url;
		this.login = login;
		this.password =password;
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
	
	
	public void insertData(CtrlViewResult ctrlViewResult) throws ClassNotFoundException, SQLException, IOException {
		insertImageTable(ctrlViewResult);
		insertParameter(ctrlViewResult);
		InsertGrains(ctrlViewResult);
	}

	private ByteArrayInputStream FormatImageToBlob(java.awt.Image image) {
		BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
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

	public void insertParameter(CtrlViewResult ctrlViewResult)
			throws SQLException, IOException, ClassNotFoundException {
		if (FindIdImageByImage(ctrlViewResult.getOriginalImage())!=-1) {
		if (FindParameter(ctrlViewResult)==-1) {
		PreparedStatement ps = this.connection.prepareStatement(
				"INSERT INTO Parametrage (TailleMin,TailleMax,nbCategoriesTaille,nbCategoriesSurface,Courbe1,Courbe2,DateCalcul,HeureCalcul,commentaire,idImage) VALUES (?,?,?,?,?,?,?,?,?,?)");
		this.connection.setAutoCommit(true);
		ps.setFloat(1, (float) ctrlViewResult.getGranuloModel().getSizeGrainMin()); 
		ps.setFloat(2, (float) ctrlViewResult.getGranuloModel().getSizeGrainMax());
		ps.setInt(3, ctrlViewResult.getGranuloModel().getClusters().size()); 
		ps.setInt(4, ctrlViewResult.getGranuloModel().getClustersSurface().size()); 
		ps.setBlob(5,FormatImageToBlob(ctrlViewResult.getGraphSizeImage()));
		ps.setBlob(6,FormatImageToBlob(ctrlViewResult.getGraphSizeImage()));
		ps.setDate(7, Date.valueOf(ctrlViewResult.getGranuloModel().getDate()));
		ps.setTime(8, Time.valueOf(ctrlViewResult.getGranuloModel().getTime()));
		ps.setString(9, ctrlViewResult.getComment().getText());
		ps.setInt(10, FindIdImageByImage(ctrlViewResult.getOriginalImage()));
		ps.executeUpdate();
		ps.close();
		}else {
			Statement smt = this.connection.createStatement();
			smt.executeUpdate("UPDATE Parametrage SET DateCalcul='" +Date.valueOf(ctrlViewResult.getGranuloModel().getDate())+"',HeureCalcul='"+Time.valueOf(ctrlViewResult.getGranuloModel().getTime())+"' ,commentaire='"+ctrlViewResult.getComment().getText()+"' WHERE idParametrage=" + FindParameter(ctrlViewResult) + ";");
		}
		}}

		public int FindParameter(CtrlViewResult ctrlViewResult ) throws SQLException, ClassNotFoundException, IOException {
			PreparedStatement ps = this.connection.prepareStatement(
					"SELECT * FROM parametrage WHERE TailleMin=? AND TailleMax=? AND nbCategoriesTaille=? AND nbCategoriesSurface=?  AND idImage=?");
			ps.setFloat(1, (float) ctrlViewResult.getGranuloModel().getSizeGrainMin()); 
			ps.setFloat(2, (float) ctrlViewResult.getGranuloModel().getSizeGrainMax());
			ps.setInt(3, ctrlViewResult.getGranuloModel().getClusters().size()); 
			ps.setInt(4, ctrlViewResult.getGranuloModel().getClustersSurface().size()); 
			ps.setInt(5, FindIdImageByImage(ctrlViewResult.getOriginalImage()));
			ResultSet r=ps.executeQuery();
			try {
				r.next();
				return r.getInt("idParametrage");
			} catch (java.sql.SQLException e) {
				return -1;
			}
		}

		
	

	public int FindIdImageByImage(java.awt.Image image) throws SQLException, IOException, ClassNotFoundException {
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
	
	
	
	public void InsertGrains(CtrlViewResult ctrlViewResult) throws ClassNotFoundException, SQLException, IOException{
		
		if(!isImageHadGrain(FindIdImageByImage(ctrlViewResult.getOriginalImage()))) {	
		this.connection.setAutoCommit(true);
			System.out.println(FindParameter(ctrlViewResult));
			int idImage = FindIdImageByImage(ctrlViewResult.getOriginalImage());
			for (Measure measure :ctrlViewResult.getGranuloModel().getMeasures()) {
				PreparedStatement ps = this.connection.prepareStatement(
						"INSERT INTO grain (centreX,centreY,longueur,largeur,circularite,Surface,idImage) VALUES (?,?,?,?,?,?,?)");
			ps.setFloat(1, (float) measure.getCentre_x()); 
			ps.setFloat(2, (float) measure.getCentre_y());
			ps.setFloat(3, measure.getHeight()); 
			ps.setFloat(4, measure.getWidth()); 
			ps.setFloat(5, (float) measure.getRoundness());
			ps.setFloat(6,  (float) measure.getAire());
			ps.setInt(7, idImage );
			ps.executeUpdate();
			}}
	}
	
	public boolean isImageHadGrain(int idImage) throws SQLException, IOException, ClassNotFoundException {
		PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM grain WHERE idImage=?");
		this.connection.setAutoCommit(true);
		ps.setInt(1,idImage);
		ResultSet r = ps.executeQuery();
		return r.next();
	}

	/**
	 * This method is for fetching the table Parametrage from our database. This
	 * table was made for storing the settings that was used to calculate the graphs
	 * of a grain. The method will send a specific SELECT sql query that will select
	 * the data
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 *
	 *                                public LinkedList<TableResults>
	 *                                getTableParametrage() throws SQLException,
	 *                                IOException, ClassNotFoundException {
	 *                                LinkedList<TableResults> listResultat = new
	 *                                LinkedList<TableResults>();
	 * 
	 *                                Class.forName("com.mysql.cj.jdbc.Driver");
	 *                                this.connection =
	 *                                DriverManager.getConnection(url,login,password);
	 * 
	 *                                Statement stmt =
	 *                                this.connection.createStatement(); String sql
	 *                                = "SELECT * FROM parametrage"; ResultSet rs =
	 *                                stmt.executeQuery(sql);
	 * 
	 *                                while (rs.next()) { int idParametrage =
	 *                                rs.getInt("idParametrage"); float tailleMin =
	 *                                rs.getFloat("TailleMin"); float tailleMax =
	 *                                rs.getFloat("TailleMax"); int nbCategories =
	 *                                rs.getInt("nbCategories");
	 * 
	 *                                Blob courbe1 = rs.getBlob("Courbe1");
	 *                                InputStream in = courbe1.getBinaryStream();
	 *                                BufferedImage bufferedCourbe1 =
	 *                                ImageIO.read(in); Image Courbe1 =
	 *                                SwingFXUtils.toFXImage(bufferedCourbe1, null
	 *                                );
	 * 
	 *                                Blob courbe2 = rs.getBlob("Courbe2");
	 *                                InputStream in2 = courbe2.getBinaryStream();
	 *                                BufferedImage bufferedCourbe2 =
	 *                                ImageIO.read(in2); Image Courbe2 =
	 *                                SwingFXUtils.toFXImage(bufferedCourbe2, null
	 *                                );
	 * 
	 *                                String DateCalcul =
	 *                                rs.getString("DateCalcul"); Time HeureCalcul =
	 *                                rs.getTime("HeureCalcul"); int idImage =
	 *                                rs.getInt("idImage");
	 * 
	 *                                listResultat.add(new
	 *                                TableResults(idParametrage,nbCategories,idImage,tailleMin,tailleMax,Courbe1,Courbe2,DateCalcul,HeureCalcul));
	 * 
	 *                                }
	 * 
	 *                                stmt.close(); return listResultat; }
	 */
	/**
	 * This method was made to insert data into the Parametrage table. It will send
	 * a specific INSERT sql query that will insert the data by using the parameters
	 * index and image. We can now use methods from the Classes GranuloData and
	 * Image to get data about the grains and implement it in our INSERT sql query.
	 * 
	 * @param data
	 * @param image
	 *
	 *              public void insertParametrage(GranuloData data) {
	 *              Class.forName("com.mysql.cj.jdbc.Driver"); this.connection =
	 *              DriverManager.getConnection(url,login,password);
	 * 
	 *              int idImage = 0; File import_img = new
	 *              File(data.getImage().getUrl()); FileInputStream fis = new
	 *              FileInputStream(import_img); String getIdImg = "SELECT idImage
	 *              FROM image WHERE image=?"; this.connection.setAutoCommit(false);
	 *              PreparedStatement ps =
	 *              this.connection.prepareStatement(getIdImg);
	 *              ps.setBinaryStream(1, fis, (int) import_img.length()); ResultSet
	 *              rs = ps.executeQuery(); this.connection.commit();
	 *              this.connection.setAutoCommit(true); while (rs.next()) { idImage
	 *              = rs.getInt("idImage"); }
	 * 
	 *              String insert_param = "INSERT INTO parametrage VALUES
	 *              (?,?,?,?,?,?,?,?,?)"; this.connection.setAutoCommit(false); ps =
	 *              this.connection.prepareStatement(insert_param);
	 * 
	 *              File courbe1 = new File
	 *              (CtrlViewResult.graphSizeToImage().getUrl()); File courbe2 =
	 *              CtrlViewResult.graphSurfaceToImage(); FileInputStream fis1 = new
	 *              FileInputStream(courbe1); FileInputStream fis2 = new
	 *              FileInputStream(courbe2);
	 * 
	 *              String date = java.time.LocalDate.now().toString();
	 * 
	 *              int hour = java.time.LocalTime.now().getHour(); int minute =
	 *              java.time.LocalTime.now().getMinute(); int second =
	 *              java.time.LocalTime.now().getSecond(); String time =
	 *              hour+":"+minute+":"+second;
	 * 
	 *              ps.setNull(1,0); ps.setFloat(2, data.getTailleGrainnMin());
	 *              ps.setFloat(3, data.getTailleGrainMax()); ps.setInt(4,
	 *              data.getClusters().size()); ps.setBinaryStream(5, fis1, (int)
	 *              courbe1.length()); ps.setBinaryStream(6, fis2, (int)
	 *              courbe2.length()); ps.setString(7, date); ps.setString(8, time);
	 *              ps.setInt(9, idImage); ps.executeUpdate();
	 *              this.connection.commit(); this.connection.setAutoCommit(true);
	 *              this.connection.close(); }
	 */
	
	/**
	 * This method is for deleting data from the Parametrage table it will send a
	 * specific sql query that will delete the settings used to calculate the graphs
	 * of a grain by using the image and the ID of a setting that is stored in
	 * Parametrage.
	 * 
	 * @param Image
	 * @param idParametrege
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	/**
	 * This method is for updating data in the Parametrage table. It will send a
	 * specific UPDATE sql query that will update the settings used to calculate the
	 * graphs of a grain by using the image and the ID of a setting that is stored
	 * in Parametrage, if the user changed the settings.
	 * 
	 * @param Image
	 * @param idParametrage
	 *
	 *                      // TODO
	 *                      HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
	 *                      public void updateParametrage(Image Image, int
	 *                      idParametrage) {
	 * 
	 *                      }
	 */

}