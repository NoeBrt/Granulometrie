package Controller;

import java.sql.SQLException;

import DAO.GranuloDAO;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CtrlInterfaceConnect {
	@FXML
	private TextField userName;

	@FXML
	private PasswordField password;

	@FXML
	private Button connect;

	@FXML
	private static GranuloDAO dao;

	/**
	 * attribute dao the connect value of the 2 textfield if these informations are
	 * valid, else it show a alert frame
	 * 
	 * @throws ClassNotFoundException
	 */
	@FXML
	public void getConnection() throws ClassNotFoundException {
		GranuloDAO dao;
		Stage stage = (Stage) userName.getScene().getWindow();
		try {
			dao = new GranuloDAO("jdbc:mysql://localhost/Granulometrie", userName.getText(), password.getText());
			CtrlInterfaceConnect.dao = dao;
		} catch (SQLException e) {
			Alert alert = alertAccesRefuse(e);
			alert.showAndWait();
		}
		stage.close();

	}

	private Alert alertAccesRefuse(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("SQL DataBaseError");
		alert.setHeaderText("Unable to connect to the database");
		alert.setContentText("error code : " + e.getMessage());
		return alert;
	}

	/**
	 * @return the dao
	 */
	public static GranuloDAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public static void setDao(GranuloDAO dao) {
		CtrlInterfaceConnect.dao = dao;
	}

}
