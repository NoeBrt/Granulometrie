package Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import CSV.WriteCsv;
import Model.GranuloData;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

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

	/**
	 * the first bubble chart displays grains by their size
	 */
	@FXML
	private LineChart<Double, Double> graphNbGrainSize;

	/**
	 * the second bubble chart displays grains by area
	 */
	@FXML
	private LineChart<Double, Double> graphNbGrainArea;

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
	private Button ApplySize ;
	

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
	 */
	@FXML
	public void initialize() {
		// TODO implement here
		InitalizeGraphSizeAndSurface();
		//graphToImage();
	}

	private void InitalizeGraphSizeAndSurface() {
		XYChart.Series<Double, Double> chartNbGrainSize = new XYChart.Series<>();
		XYChart.Series<Double, Double> chartNbGrainArea = new XYChart.Series<>();
		chartNbGrainSize.setName("Particles by size");
		chartNbGrainArea.setName("Particles by surface");
//	chartNbGrainArea.getData().add(new XYChart.Data<Double, Double>((double)2.1,(double)3.2));
				

		this.graphNbGrainSize.getData().add(chartNbGrainSize);
		this.graphNbGrainArea.getData().add(chartNbGrainArea);

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
		if (sizeMin.getText()!=null&&sizeMax.getText()!=null) {
		GranuloModel.setScale(Integer.parseInt(sizeMin.getText()), Integer.parseInt(sizeMax.getText()));}
		if (sizeMin.getText()==null&&sizeMax.getText()!=null) {
		GranuloModel.setScale(0, Integer.parseInt(sizeMax.getText()));}
		if (sizeMin.getText()!=null&&sizeMax.getText()==null) {
			GranuloModel.setScale(Integer.parseInt(sizeMin.getText()),0);}			
	}

	
	/*
	 * setComment this method set Comment in model
	 * 
	 *
	public void setComment(ActionEvent event) {
		GranuloModel.setComment(comment.getText());
		
	}*/

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
	public Image graphToImage() {
		// TODO implement here
		WritableImage wim = new WritableImage((int) graphNbGrainSize.getWidth(),(int) graphNbGrainSize.getHeight());
		 graphNbGrainSize.getScene().snapshot(null);
		File fileA = new File("C://Graphs/chart.png");
		 try {
		      ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
		 }
		 catch (Exception s) {
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
		GranuloModel.setClusters(Integer.parseInt(clusterWidth.getText()));
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
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV file (.csv)", ".csv");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(null);
		String path = file.getPath();
		WriteCsv write = new WriteCsv(this.GranuloModel, this.GranuloModel.getHeader(),path);
		write.StartWriting();

	}



	/**
	 * UpdateGraphe this method updates both charts
	 * 
	 * @param this method has no parameters
	 * @return void this methode has no return type
	 *
	 * @FXML public void updateGraph() { // TODO implement here }
	 */

}