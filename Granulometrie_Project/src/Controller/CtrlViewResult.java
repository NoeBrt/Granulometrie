package Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.text.AsyncBoxView.ChildState;

import CSV.WriteCsv;
import DAO.DAO;
import Model.GranuloData;
import app.Measure;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
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
		this.GranuloModel = new GranuloData(CtrlView.getImagePath());
	}

	private GranuloData GranuloModel;

	@FXML
	private LineChart<String, Integer> graphNbGrainSize;

	@FXML
	private NumberAxis Number;
	/**
	 * the second bubble chart displays grains by area
	 */
	@FXML
	private LineChart<String, Integer> graphNbGrainSurface;

	/**
	 * user can leave a comment in comment text field
	 */
	@FXML
	private TextArea comment;

	/**
	 * user can leave a comment in comment text field
	 */
	@FXML
	private TextArea imageComment;

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
	 * clusterWdith this text field allows the user to define the particles width
	 */
	@FXML
	private TextField surfaceClusterWidth;

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
	 * exportButton this button export data in csv
	 *
	 */
	@FXML
	private Button exportChart1JPGButton;
	/**
	 * exportButton this button export data in csv
	 *
	 */
	@FXML
	private Button exportChart2JPGButton;

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
		InitalizeGraphSurface();
	}

	@FXML
	private void InitalizeGraphSize() {

		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		for (Map.Entry<Double, List<Measure>> entry : GranuloModel.getClusters().entrySet()) {
			String x = entry.getKey() - GranuloModel.getEtalon() + "-" + entry.getKey().toString();
			List<Measure> y = entry.getValue();
			series.getData().add(new XYChart.Data<>(x, y.size()));

		}
		// series.setName("Numer of Grain by Size");
		graphNbGrainSize.setLegendVisible(false);
		graphNbGrainSize.setMinSize(426, 324);
		graphNbGrainSize.setMaxSize(426, 324);
		graphNbGrainSize.getData().clear();
		// graphNbGrainSize.layout();
		graphNbGrainSize.getData().add(series);
	}

	@FXML
	private void InitalizeGraphSurface() {

		System.out.println(GranuloModel.getClustersSurface().keySet());
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		for (Map.Entry<Double, List<Measure>> entry : GranuloModel.getClustersSurface().entrySet()) {
			String x = entry.getKey() - GranuloModel.getEtalonSurface() + "-" + entry.getKey().toString();
			List<Measure> y = entry.getValue();
			series.getData().add(new XYChart.Data<>(x, y.size()));

		}
		// series.setName("Numer of Grain by Surface");
		graphNbGrainSurface.setLegendVisible(false);
		graphNbGrainSurface.setMinSize(426, 405);
		graphNbGrainSurface.setMaxSize(426, 405);
		graphNbGrainSurface.getData().clear();
		// graphNbGrainArea.layout();
		graphNbGrainSurface.getData().add(series);
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
			InitalizeGraphSurface();

		}
		if (sizeMin.getText() == "" && sizeMax.getText() != "") {
			GranuloModel.setScaleMin(Integer.parseInt(sizeMin.getText()));
			InitalizeGraphSize();
			InitalizeGraphSurface();

		}
		if (sizeMin.getText() != "" && sizeMax.getText() == "") {
			GranuloModel.setScaleMax(Integer.parseInt(sizeMax.getText()));
			graphNbGrainSize.notify();
			InitalizeGraphSize();
			InitalizeGraphSurface();

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
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	@FXML
	public void saveDataBase(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
		// TODO implement here
		DAO d = new DAO();
		d.insertTableImage(this);
	}

	/**
	 * graphSizeToImage this method convert the chart to image in order to save it
	 * in local directory
	 * 
	 * @param Graph this method holds one parameter the chart that is going to be
	 *              converted
	 * @return void this method has no return type
	 * @throws FileNotFoundException 
	 */
	public void graphSizeToImageExport() throws FileNotFoundException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialFileName("GraphNumerBySize");
		SaveInFile(getGraphSizeImage(), fileChooser);

	}

	public BufferedImage getGraphSizeImage() throws FileNotFoundException {
		Parent root = graphNbGrainSurface;
		WritableImage image = root.snapshot(new SnapshotParameters(), null);
		return SwingFXUtils.fromFXImage(image, null);
	}
/*
	public byte[] getGraphSizeByte() throws FileNotFoundException {
		return NodeToBaos(graphNbGrainSize);
	}*/

	/**
	 * graphSurfaceToImage this method convert the chart to image in order to save
	 * it in local directory
	 * 
	 * @param Graph this method holds one parameter the chart that is going to be
	 *              converted
	 * @return void this method has no return type
	 * @throws FileNotFoundException 
	 */
	public void graphSurfaceToImageExport() throws FileNotFoundException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialFileName("GraphNumerBySurface");
		SaveInFile(getGraphSurfaceImage(), fileChooser);
	}

	public BufferedImage getGraphSurfaceImage() throws FileNotFoundException {
		Parent root = graphNbGrainSurface;
		WritableImage image = root.snapshot(new SnapshotParameters(), null);
		return SwingFXUtils.fromFXImage(image, null);
	}
/*
	public byte[] getGraphSurfaceByte() throws FileNotFoundException {
		return NodeToBaos(graphNbGrainSurface);
	}*/

/*
	private byte[] NodeToBaos(Parent node) {
		Parent root = node;
		WritableImage image = root.snapshot(new SnapshotParameters(), null);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "jpg", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
*/
	private void SaveInFile(BufferedImage image, FileChooser fileChooser) {
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG file (*.jpg)", "*.jpg");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(null);
		try {
			ImageIO.write(image, "png", file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	/**
	 * setCluster this method defines the particles width
	 * 
	 * @param this method has no parameters
	 * @return void this method has no return type
	 */
	@FXML
	public void setCluster(ActionEvent event) {
		GranuloModel.setClusters(Double.parseDouble(clusterWidth.getText()));
		InitalizeGraphSize();
		// setScaleMinMax(event);
	}

	/**
	 * setSurfaceCluster this method defines the particles width
	 * 
	 * @param this method has no parameters
	 * @return void this method has no return type
	 */
	@FXML
	public void setSurfaceCluster(ActionEvent event) {
		GranuloModel.setSurfaceClusters(Double.parseDouble(surfaceClusterWidth.getText()));
		InitalizeGraphSurface();
		// setScaleMinMax(event);

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
		Image imageToBeSaved = GranuloModel.getImage();
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
	
	public BufferedImage getOriginalImage() {
		return SwingFXUtils.fromFXImage(CtrlView.getImage(),null);
		
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
		WriteCsv write = new WriteCsv(this.GranuloModel,
				new String[] { "air", "centreX", "centreY", "XStart", "YStart", "Width", "Height" }, path);
		write.StartWriting();

	}

	@FXML
	public void ClickChartSize() {
		/*
		 * //FXMLLoader GranuloVue1 = new
		 * FXMLLoader(CtrlView.class.getResource("Graph1Wide.fxml")); Parent root =
		 * graphNbGrainSize; Stage stage = new Stage(); stage.getIcons().add(new
		 * Image("/IconApp/icon.jpg")); stage.setTitle("GrapheSizeWide");
		 * stage.setScene(new Scene(root)); stage.show();
		 * 
		 */

	}

	@FXML
	public void ClickChartArea() {
		/*
		 * FXMLLoader GranuloVue1 = new
		 * FXMLLoader(CtrlView.class.getResource("Graph1Wide.fxml")); LineChart<String,
		 * Integer> h = new LineChart<String, Integer>(graphNbGrainArea.getXAxis(),
		 * graphNbGrainArea.getYAxis());
		 * 
		 * h.getData().addAll(graphNbGrainArea.snapshot(null, null)); Parent root; root
		 * = h; Stage stage = new Stage(); stage.getIcons().add(new
		 * Image("/IconApp/icon.jpg")); stage.setTitle("GrapheAreaWide");
		 * stage.setScene(new Scene(root)); stage.show();
		 * 
		 */
	}

	/**
	 * @return the comment
	 */
	public TextArea getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(TextArea comment) {
		this.comment = comment;
	}

	/**
	 * @return the imageComment
	 */
	public TextArea getImageComment() {
		return imageComment;
	}

	/**
	 * @param imageComment the imageComment to set
	 */
	public void setImageComment(TextArea imageComment) {
		this.imageComment = imageComment;
	}

}