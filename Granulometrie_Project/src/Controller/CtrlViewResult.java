package Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import CSV.WriteCsv;
import GranuloTest.granuloDataTest;
import Model.GranuloData;
import app.Measure;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Alex
 * 
 *         this class displays two charts with the image particles and allows
 *         the user to export an image in jpg and data in csv also user can
 *         insert data into database the constructor
 * @param constructor has no parameters
 */
public class CtrlViewResult {

	/**
	 * the constructor of CtrlResultView
	 * 
	 * @param model the constructor holds one parameter the data model
	 * 
	 *              public CtrlViewResult(GranuloData model) {
	 *              this.GranuloModel=model; }
	 */

	public CtrlViewResult() {
		// this.GranuloModel=model;
		this.GranuloModel = new GranuloData(CtrlView.getImagePath());
	}

	private GranuloData GranuloModel;

	@FXML
	private static LineChart<String, Integer> graphNbGrainSize;
	// @FXML
	// private CategoryAxis xAxis;
	@FXML
	private static NumberAxis Number;
	/**
	 * the second bubble chart displays grains by area
	 */
	@FXML
	private LineChart<String, Integer> graphNbGrainArea;

	/**
	 * user can leave a comment in comment text field
	 */
	@FXML
	private TextArea comment;

	/**
	 * user can leave a comment in comment text field
	 */
	@FXML
	private TextArea ImageComment;

	/**
	 * import button import an image
	 */
	@FXML
	private Button ApplySize;

	/**
	 * save data base button insert data into database and update the database
	 */
	@FXML
	private Button saveDataBaseButton;

	/**
	 * TextField sizeMax this text field allows the user to define the maximum size
	 */
	@FXML
	private TextField sizeMax;

	/**
	 * 
	 * TextField sizeMin this text field allows the user to define the mainimum size
	 */
	@FXML
	private TextField sizeMin;

	/**
	 * clusterWdith this text field allows the user to define the particles width
	 */
	@FXML
	private TextField clusterWidth;

	/**
	 * exportButton this button export an image in jpg
	 *
	 */
	@FXML
	private Button exportCSVButton;

	/**
	 * exportButton this button export data in csv
	 *
	 */
	@FXML
	private Button exportJPGButton;

	/**
	 * initialize Initialize is an implemented method of Initializable interface
	 * that allows the user to define actions to buttons without using fxml file
	 * 
	 * @FXML public void initialize() { // TODO implement here
	 *       InitalizeGraphSizeAndSurface(); //graphToImage(); }
	 */

	@FXML
	public void initialize() {
		InitalizeGraphSize();
		InitalizeGraphArea();
	}

	@FXML
	private void InitalizeGraphSize() {
		graphNbGrainSize.getData().clear();
		graphNbGrainSize.layout();

		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		for (Map.Entry<Double, List<Measure>> entry : GranuloModel.getClusters().entrySet()) {
			String x = entry.getKey() - GranuloModel.getEtalon() + "-" + entry.getKey().toString();
			List<Measure> y = entry.getValue();
			series.getData().add(new XYChart.Data<>(x, y.size()));

		}
		series.setName("Numer of Grain by Size");
		graphNbGrainSize.getData().clear();
		graphNbGrainSize.layout();

		graphNbGrainSize.getData().add(series);
	}

	@FXML
	private void InitalizeGraphArea() {
		graphNbGrainArea.getData().clear();
		graphNbGrainArea.layout();
		System.out.println(GranuloModel.GetSurfaceClusters(GranuloModel.getEtalon()));
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		for (Map.Entry<Double, List<Measure>> entry : GranuloModel.GetSurfaceClusters(GranuloModel.getEtalon())
				.entrySet()) {
			String x = entry.getKey() - GranuloModel.getEtalon() + "-" + entry.getKey().toString();
			List<Measure> y = entry.getValue();
			series.getData().add(new XYChart.Data<>(x, y.size()));

		}
		series.setName("Numer of Grain by Size");
		graphNbGrainArea.getData().clear();
		graphNbGrainArea.layout();

		graphNbGrainArea.getData().add(series);
	}

	/**
	 * setScaleMinMax this method allows the user to define the min, max height of
	 * particles
	 * 
	 * @param this method has no parameter
	 * @return void this methode has no return type
	 */

	@FXML
	public void setScaleMinMax(ActionEvent event) {
		if (sizeMin.getText() != "" && sizeMax.getText() != "") {
			GranuloModel.setScale(Integer.parseInt(sizeMin.getText()), Integer.parseInt(sizeMax.getText()));
			InitalizeGraphSize();
			InitalizeGraphArea();

		}
		if (sizeMin.getText() == "" && sizeMax.getText() != "") {
			GranuloModel.setScaleMin(Integer.parseInt(sizeMin.getText()));
			InitalizeGraphSize();
			InitalizeGraphArea();

		}
		if (sizeMin.getText() != "" && sizeMax.getText() == "") {
			GranuloModel.setScaleMax(Integer.parseInt(sizeMax.getText()));
			InitalizeGraphSize();
			InitalizeGraphArea();

		}

	}

	/*
	 * setComment this method set Comment in model
	 * 
	 *
	 * public void setComment(ActionEvent event) {
	 * GranuloModel.setComment(comment.getText());
	 * 
	 * }
	 */

	/**
	 * saveDataBase this methode save data in database
	 * 
	 * @param this method has no parameter
	 * @return void this method has no return type
	 */
	@FXML
	public void saveDataBase(ActionEvent event) {
		// TODO implement here
	}

	/**
	 * GrapheToImage this method convert the chart to image in order to save it in
	 * local directory
	 * 
	 * @param Graph this method holds one parameter the chart that is going to be
	 *              converted
	 * @return void this method has no return type
	 */
	public Image graphSizeToImage() {
		// TODO implement here
		WritableImage wim = new WritableImage((int) graphNbGrainSize.getWidth(), (int) graphNbGrainSize.getHeight());
		graphNbGrainSize.getScene().snapshot(wim);
		File fileA = new File("C://Graphs/chart.png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
		} catch (Exception s) {
			System.out.println("erreur");
		}

		return null;
	}

	public Image graphSurfaceToImage() {
		// TODO implement here
		WritableImage wim = new WritableImage((int) graphNbGrainSize.getWidth(), (int) graphNbGrainSize.getHeight());
		graphNbGrainSize.getScene().snapshot(wim);
		File fileA = new File("C://Graphs/chart.png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
		} catch (Exception s) {
			System.out.println("erreur");
		}

		return null;
	}

	/**
	 * setCluster this method defines the particles width
	 * 
	 * @param this method has no parameters
	 * @return void this method has no return type
	 */
	@FXML
	public void setCluster(ActionEvent event) {
		// TODO implement here
		GranuloModel.setClusters(Double.parseDouble(clusterWidth.getText()));
		System.out.println(Double.parseDouble(clusterWidth.getText()));
		InitalizeGraphSize();
		InitalizeGraphArea();

	}

	/**
	 * ExportJpg this method save the converted chart image in local directory in
	 * jpg format
	 * 
	 * @param this method has no parameters
	 * @return void this method has no return type
	 */
	@FXML
	public void exportJpg(ActionEvent event) {
		Image imageToBeSaved = new Image(GranuloModel.getImage().getUrl());
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialFileName("ImageGranulo");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG file (*.jpg)", "*.jpg");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(null);
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(imageToBeSaved, null), "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * ExportJpg this method save chart data in local directory in csv format with
	 * WriteCSV with the metod statWriting
	 * 
	 * @param this method has no parameters
	 * @return void this method has no return type
	 */
	@FXML
	public void exportCsv(ActionEvent event) throws MalformedURLException, IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialFileName("DataGranulo");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV file (.csv)", ".csv");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(null);
		String path = file.getPath();
		WriteCsv write = new WriteCsv(this.GranuloModel, this.GranuloModel.getHeader(), path);
		write.StartWriting();

	}
	
	 @FXML
	 public void ClickChartSize() {
			FXMLLoader GranuloVue1 = new FXMLLoader(CtrlView.class.getResource("Graph1Wide.fxml"));
			Parent root;
			try {
				root = GranuloVue1.load();
				Stage stage = new Stage();
				stage.getIcons().add(new Image("/IconApp/icon.jpg"));
				stage.setTitle("GrapheSizeWide");
				stage.setScene(new Scene(root));
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	 }
	 @FXML
	 public void ClickChartArea() {
			FXMLLoader GranuloVue1 = new FXMLLoader(CtrlView.class.getResource("Graph2Wide.fxml"));
			Parent root;
			try {
				root = GranuloVue1.load();
				Stage stage = new Stage();
				stage.getIcons().add(new Image("/IconApp/icon.jpg"));
				stage.setTitle("GrapheAreaWide");
				stage.setScene(new Scene(root));
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	 }

}