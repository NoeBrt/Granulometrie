
package application;


import Controller.CtrlView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class GranuloApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader GranuloVue1 = new FXMLLoader(CtrlView.class.getResource("/Controller/GranuloVue1.fxml"));			 
			BorderPane root = GranuloVue1.load();
			root.setMinSize(800, 600);
			Scene scene = new Scene(root,800,600);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.setTitle("Granulometrie V1");
			primaryStage.getIcons().add(new Image("/IconApp/icon.jpg"));
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> Platform.exit());

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}