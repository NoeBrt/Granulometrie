package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import application.GranuloApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Noe,Quentin,Alex
 */
public class CtrlView {
	/**
	 * import button import an image
	 */
	@FXML
	private Button importButton;
	/**
	 * import button import an image
	 */
	@FXML
	private Button seeDatabaseButton;

	/**
	 * launch processing button start processing
	 */
	@FXML
	private Button launchProcessButton;

	/**
	 * the image that will be loaded
	 */
	@FXML
	private static Image image;

	/**
	 * the image that will be loaded
	 */
	@FXML
	private static String imagePath;

	/**
	 * the view which display the image
	 */
	@FXML
	private ImageView imgView;

	private static boolean isImported;

	/**
	 * launchProcess method runs a new interface GrapheController to display
	 * particles on 2 charts
	 * 
	 * @param this method has no parameters
	 * @return void this method has no return type
	 * @throws IOException
	 */
	@FXML
	void importImage() throws IOException {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("JPG Files", "*.jpg"));
		// fc.getExtensionFilters().add(new ExtensionFilter("PNG Files", "*.png"));
		File file = fc.showOpenDialog(null);

		if (file != null) {
			image = new Image(file.toURI().toString());
			imagePath = file.getCanonicalPath();
			imgView.setImage(image);
			isImported = true;
		}

		else {
			System.out.println("invalide file");
		}

	}

	/**
	 * handleDragOver method allows the interface detecting an image if the image is
	 * Draged over the interface the interface accepts any mode of image sending
	 * such as copy paste for example
	 * 
	 * @param DragEvent event this method has only one parameter if the image is in
	 *                  interaction with the interface then the image is detected
	 * 
	 * @return void this method has no return type
	 */

	@FXML
	public void handleDragOver(DragEvent event) {
		if (event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
	}

	/**
	 * handleDrop loads an image and displays it in view through a drag and drop
	 * action if any type of files is dragged except an image we throws
	 * IlligalArgumentException
	 * 
	 * @param DragEvent event this method has one parameter loading an image in view
	 * @return void this method has no return type
	 * @throws IOException
	 */
	@FXML
	public void handleDrop(DragEvent event) throws IOException {
		List<File> files = event.getDragboard().getFiles();
		Image ImageTest = new Image(new FileInputStream(files.get(0)));
		if (!ImageTest.isError()) {
			CtrlView.image = new Image(new FileInputStream(files.get(0)));
			imagePath = files.get(0).getCanonicalPath();
			imgView.setImage(image);
			isImported = true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Image Error");
			alert.setHeaderText("0 Image imported");
			alert.setContentText("File not Valid");
			alert.showAndWait();

		}

	}

	/**
	 * @return the imagePath
	 */
	public static String getImagePath() {
		return imagePath;
	}

	/**
	 * @throws change the scene for CtrlViewDB_Image whose display data from
	 *                Database
	 */
	public void seeDBSaveResults() throws IOException {
		if (CtrlInterfaceConnect.getDao() == null) {
			try {
				CtrlInterfaceConnect.showInterfaceConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (CtrlInterfaceConnect.getDao() != null) {
			Stage stage = GranuloApp.primaryStage;
			FXMLLoader GranuloDB = new FXMLLoader(CtrlView.class.getResource("GranuloDB_Image.fxml"));
			Parent root = GranuloDB.load();
			stage.setResizable(false);
			stage.setScene(new Scene(root));
		}

	}

	/**
	 * launchProcess method runs a new interface GrapheController to display
	 * particles on 2 charts one by size and the other by surface
	 * 
	 * @param this method has one parameter ActionEvent
	 * @return void this method has no return type
	 * @throws IOException
	 */

	@FXML
	public void launchProces() throws IOException {
		if (isImported == true) {
			try {
				FXMLLoader GranuloVue1 = new FXMLLoader(CtrlView.class.getResource("GranuloResultChart.fxml"));
				Parent root = GranuloVue1.load();
				Stage stage = new Stage();
				stage.getIcons().add(new Image("/IconApp/icon.jpg"));
				stage.setTitle("result");
				stage.setMinHeight(583.0);
				stage.setMinWidth(826.0);
				stage.setScene(new Scene(root));
				stage.setResizable(false);
				root.prefWidth(stage.widthProperty().doubleValue());
				root.prefHeight(stage.heightProperty().doubleValue());
				stage.show();
				


			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error alert");
				alert.setHeaderText("ERROR");
				alert.setContentText("CANT'T LOAD IMAGE");
				alert.showAndWait();
				System.out.println("Cannot load new window");
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error alert");
			alert.setHeaderText("0 Image imported");
			alert.setContentText("You must import an Image to launch the process");
			alert.showAndWait();
		}
	}
//	}

	/**
	 * @return the image
	 */
	public static Image getImage() {
		return image;
	}

}