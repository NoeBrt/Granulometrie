package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.GranuloDAO;
import Model.ImageDB;
import Model.ParameterDB;
import application.GranuloApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * @author Noe controller of GranuloDB_param.fxml, display a table view of
 *         Parameter row from a database
 */
public class CtrlViewDB_Param implements Initializable {
	@FXML
	public Button returnToImageView;
	private GranuloDAO granuloDAO;
	public ObservableList<ParameterDB> dataParameter = FXCollections.observableArrayList();
	private static ImageDB ImageDbCLiked;

	@FXML
	private TableView<ParameterDB> tableViewParameter;
	@FXML
	TableColumn<ParameterDB, Integer> idParameter;
	@FXML
	TableColumn<ParameterDB, Float> sizeMin;
	@FXML
	TableColumn<ParameterDB, Float> sizeMax;
	@FXML
	TableColumn<ParameterDB, Integer> nbCategorySize;
	@FXML
	TableColumn<ParameterDB, Integer> nbCategorySurface;
	@FXML
	TableColumn<ParameterDB, ImageView> chart1;
	@FXML
	TableColumn<ParameterDB, ImageView> chart2;
	@FXML
	TableColumn<ParameterDB, String> dateCalcul;
	@FXML
	TableColumn<ParameterDB, String> heureCalcul;
	@FXML
	TableColumn<ParameterDB, String> comment;
	@FXML
	TableColumn<ParameterDB, Integer> idImage;

	/**
	 * go back to the GranuloDB_Image.fxml view & controller
	 * 
	 * @throws IOException
	 */
	@FXML
	public void backToCtrlViewDBImage() throws IOException {
		Stage stage = GranuloApp.primaryStage;
		FXMLLoader CtrlViewDB_param = new FXMLLoader(CtrlView.class.getResource("GranuloDB_Image.fxml"));
		Parent root = CtrlViewDB_param.load();
		stage.setScene(new Scene(root));
	}

	/**
	 * connect to the DataBase & set TableView with viewParameterTable(), if the
	 * program is unable to connect to the Database, the method display a error
	 * frame
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Alert alert1 = new Alert(AlertType.ERROR);
		alert1.setTitle("SQL DataBaseError");
		alert1.setHeaderText("Unable to connect to the database");
		this.granuloDAO = CtrlInterfaceConnect.getDao();
		try {
			viewParameterTable();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * get all the Parameter row from the DataBase and set the TableVew
	 * 
	 * @throws ClassNotFoundException
	 */
	public void viewParameterTable() throws ClassNotFoundException {
		try {
			this.dataParameter = this.granuloDAO.getParameterTable(CtrlViewDB_Param.ImageDbCLiked.getIdImage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e);
			sqlAlert(e);
		}
		idParameter.setCellValueFactory(new PropertyValueFactory<ParameterDB, Integer>("idParameter"));
		sizeMin.setCellValueFactory(new PropertyValueFactory<ParameterDB, Float>("sizeMin"));
		sizeMax.setCellValueFactory(new PropertyValueFactory<ParameterDB, Float>("sizeMax"));
		nbCategorySize.setCellValueFactory(new PropertyValueFactory<ParameterDB, Integer>("nbCategorySize"));
		nbCategorySurface.setCellValueFactory(new PropertyValueFactory<ParameterDB, Integer>("nbCategorySurface"));
		chart1.setCellValueFactory(new PropertyValueFactory<ParameterDB, ImageView>("chart1"));
		chart2.setCellValueFactory(new PropertyValueFactory<ParameterDB, ImageView>("chart2"));
		dateCalcul.setCellValueFactory(new PropertyValueFactory<ParameterDB, String>("dateCalcul"));
		heureCalcul.setCellValueFactory(new PropertyValueFactory<ParameterDB, String>("heureCalcul"));
		comment.setCellValueFactory(new PropertyValueFactory<ParameterDB, String>("comment"));
		tableViewParameter.setItems(dataParameter);
	}

	/**
	 * display a Alert Frame
	 * 
	 * @param SqlException e
	 */
	private void sqlAlert(SQLException e) {
		Alert alert1 = new Alert(AlertType.ERROR);
		alert1.setTitle("SQL DataBaseError");
		alert1.setHeaderText("Unable to connect to the database");
		alert1.setContentText("error code : " + e.getErrorCode());
		alert1.showAndWait();
	}

	/**
	 * @return the imageDbCLiked
	 */
	public static ImageDB getImageDbCLiked() {
		return ImageDbCLiked;
	}

	/**
	 * @param imageDbCLiked the imageDbCLiked to set
	 */
	public static void setImageDbCLiked(ImageDB imageDbCLiked) {
		ImageDbCLiked = imageDbCLiked;
	}

}
