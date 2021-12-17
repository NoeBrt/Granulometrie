package Controller;


import Model.GranuloData;
import javafx.fxml.FXML;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * @author Alex
 * 
 * this class displays two charts with the image particles and allows the user to 
 * export an image in jpg and data in csv also user can insert data into database
 * the constructor
 * @param
 * constructor has no parameters
 */
public class CtrlViewResult {
    /**
     * the first bubble chart displays grains by their size
     */
	@FXML
    private BubbleChart graphNbGrainSize;

    /**
     * the second bubble chart displays grains by area
     */
	@FXML
    private BubbleChart graphNbGrainArea;

    /**
     * user can leave a comment in comment text field
     */
	@FXML
    private TextField comment;

    /**
     * save data base button insert data into database and update the database
     */
	@FXML
	private Button saveDataBaseButton;

    /**
     * TextField sizeMax
     * this text field allows the user to define the maximum size
     */
	@FXML
    private TextField sizeMax;

    /**
     * 
     * TextField sizeMin
     * this text field allows the user to define the mainimum size
     */
	@FXML
    private TextField sizeMin;

    /**
     * clusterWdith
     * this text field allows the user to define the particles width
     */
	@FXML
    private TextField clusterWidth;

    /**
     * exportButton
     *  this button triggers two actions 
     * 1_ export an image in jpg
     * 2_ export data in csv
     */
	@FXML
    private Button exportButton;


    /**
     * the constructor of CtrlResultView
     * @param model
     * the constructor holds one parameter the data model
     */
	@FXML
    public void CtrlResultView(GranuloData model) {
        // TODO implement here
    }
	
    /**
     * setScaleMinMax
     * this method allows the user to define the min, max height of particles
     * @param
     * this method has no parameter
     * @return void
     * this methode has no return type
     */
	
	@FXML
    public void setScaleMinMax() {
        // TODO implement here
    }
	
    /**
     * saveDataBase
     * this methode save data in database
     * @param 
     * this method has no parameter
     * @return void
     * this method has no return type 
     */
	@FXML
    public void saveDataBase() {
        // TODO implement here
    }
	
    /**
     * GrapheToImage 
     * this method convert the chart to image in order to save it in local directory
     * @param Graph 
     * this method holds one parameter the chart that is going to be converted
     * @return void
     * this method has no return type
     */
	@FXML
    public Image GraphToImage(BubbleChart<?, ?> Graph) {
        // TODO implement here
        return null;
    }
	
    /**
     *	setCluster
     *this method defines the particles width
     *@param 
     *this method has no parameters
     *@return void
     *this method has no return type 
     */
	@FXML
    public void setCluster() {
        // TODO implement here
    }

    /**
     * ExportJpg
     * this method save the converted chart image in local directory in jpg format
     * @param 
     * this method has no parameters
     * @return void
     * this method has no return type
     */
	@FXML
    public void ExportJpg() {
        // TODO implement here
    }

    /**
     * 
     * ExportJpg
     * this method save chart data in local directory in csv format
     * @param 
     * this method has no parameters
     * @return void
     * this method has no return type
     */
	@FXML
    public void ExportCsv() {
        // TODO implement here
    }

    /**
     * UpdateGraphe
     * this method updates both charts
     * @param
     * this method has no parameters
     * @return void
     * this methode has no return type
     */
	@FXML
    public void UpdateGraph() {
        // TODO implement here
    }
	
	
    /**
     *	initialize
     *Initialize is an implemented method of Initializable interface that allows
     *the user to define actions to buttons without using fxml file 
     */
	@FXML
    public void initialize() {
        // TODO implement here

    }
}