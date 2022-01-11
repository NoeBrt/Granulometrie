package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.GranuloDAO;
import Model.ImageDB;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * @author Noe controller of GranuloDB_param.fxml, display a table view of Image
 *         row from a database
 */
public class CtrlViewDB_Image implements Initializable {
	public Button importButton;
	/**
	 * 
	 */
	private GranuloDAO granuloDAO;
	public ObservableList<ImageDB> dataImage = FXCollections.observableArrayList();
	@FXML
	private TableView<ImageDB> tableViewImage;
	@FXML
	TableColumn<ImageDB, Integer> idImage;
	@FXML
	TableColumn<ImageDB, ImageView> image;
	@FXML
	TableColumn<ImageDB, Float> height;
	@FXML
	TableColumn<ImageDB, Float> widht;
	@FXML
	TableColumn<ImageDB, Integer> magnification;
	@FXML
	TableColumn<ImageDB, String> comment;

	/**
	 * go back to the GranuloVue1.fxml view & controller
	 * 
	 * @throws IOException
	 */
	@FXML
	public void backToCtrlView() throws IOException {
		Stage stage = GranuloApp.primaryStage;
		FXMLLoader CtrlViewf = new FXMLLoader(CtrlView.class.getResource("GranuloVue1.fxml"));
		Parent root = CtrlViewf.load();
		stage.setScene(new Scene(root));
	}

	/**
	 * connect to the DataBase & set TableView with viewImageTable(), if the program
	 * is unable to connect to the Database, the method display a error frame
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (CtrlInterfaceConnect.getDao() != null) {
			getDoubleClikedRowImageItem();
			Alert alert1 = new Alert(AlertType.ERROR);
			alert1.setTitle("SQL DataBaseError");
			alert1.setHeaderText("Unable to connect to the database");
			this.granuloDAO = CtrlInterfaceConnect.getDao();
			try {
				viewImageTable();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				backToCtrlView();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * get all the Image row from the DataBase and set the TableVew
	 * 
	 * @throws ClassNotFoundException
	 */
	public void viewImageTable() throws ClassNotFoundException {
		try {
			this.dataImage = this.granuloDAO.getImageTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			sqlAlert(e);
		}
		idImage.setCellValueFactory(new PropertyValueFactory<ImageDB, Integer>("idImage"));
		image.setCellValueFactory(new PropertyValueFactory<ImageDB, ImageView>("image"));
		widht.setCellValueFactory(new PropertyValueFactory<ImageDB, Float>("longueur"));
		height.setCellValueFactory(new PropertyValueFactory<ImageDB, Float>("largeur"));
		magnification.setCellValueFactory(new PropertyValueFactory<ImageDB, Integer>("grossisement"));
		comment.setCellValueFactory(new PropertyValueFactory<ImageDB, String>("commentaire"));
		tableViewImage.setItems(dataImage);
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
	 * if a row who contain data is double clicked, display the TableView of
	 * parameter Table with CtrlViewDB_Param as controller
	 */
	public void getDoubleClikedRowImageItem() {
		tableViewImage.setRowFactory(tv -> {
			TableRow<ImageDB> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					System.out.println(row.getItem());
					CtrlViewDB_Param.setImageDbCLiked(row.getItem());
					Stage stage = GranuloApp.primaryStage;
					FXMLLoader CtrlView = new FXMLLoader(CtrlView.class.getResource("GranuloDB_Param.fxml"));
					Parent root;
					try {
						root = CtrlView.load();
						stage.setScene(new Scene(root));
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			});
			return row;
		});
	}

}
