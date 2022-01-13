
package application;


import java.sql.SQLException;

import Controller.CtrlInterfaceConnect;
import Controller.CtrlView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class GranuloApp extends Application {
	 public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader GranuloVue1 = new FXMLLoader(CtrlView.class.getResource("/Controller/GranuloVue1.fxml"));			 
			BorderPane root = GranuloVue1.load();
			root.prefWidthProperty().bind(Bindings.divide(primaryStage.widthProperty(), 3.0));
			root.prefHeightProperty().bind(Bindings.divide(primaryStage.heightProperty(), 3.0));
			root.pickOnBoundsProperty();
			Scene scene = new Scene(root,800,600);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.setTitle("Granulometrie V1");
			primaryStage.getIcons().add(new Image("/IconApp/icon.jpg"));
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> {
				try {
					CtrlInterfaceConnect.getDao().getConnection().close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			primaryStage.setOnCloseRequest(e -> Platform.exit());
			GranuloApp.primaryStage=primaryStage;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}