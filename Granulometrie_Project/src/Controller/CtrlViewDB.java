package Controller;

import java.io.IOException;

import application.GranuloApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class CtrlViewDB {
	public Button importButton;

	public CtrlViewDB() {
		// TODO Auto-generated constructor stub
	}

	public void backToCtrlView() throws IOException {
		Stage stage = GranuloApp.primaryStage;
		FXMLLoader CtrlView = new FXMLLoader(CtrlView.class.getResource("GranuloVue1.fxml"));
		Parent root = CtrlView.load();
		stage.setScene(new Scene(root));
	}

}
