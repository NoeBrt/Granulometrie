package Controller;


import Model.GranuloData;
import javafx.fxml.FXML;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * 
 */
public class CtrlViewResult {
    /**
     * 
     */
	@FXML
    private BubbleChart graphNbGrainSize;

    /**
     * 
     */
	@FXML
    private BubbleChart graphNbGrainArea;

    /**
     * 
     */
	@FXML
    private TextField comment;

    /**
     * 
     */
	@FXML
	private Button saveDataBaseButton;

    /**
     * 
     */
	@FXML
    private TextField sizeMax;

    /**
     * 
     */
	@FXML
    private TextField sizeMin;

    /**
     * 
     */
	@FXML
    private TextField clusterWidth;

    /**
     * 
     */
	@FXML
    private Button exportButton;


    /**
     * @param model
     */
	@FXML
    public void CtrlResultView(GranuloData model) {
        // TODO implement here
    }
    /**
     * 
     */
	@FXML
    public void setScaleMinMax() {
        // TODO implement here
    }
    /**
     * 
     */
	@FXML
    public void saveDataBase() {
        // TODO implement here
    }
    /**
     * @param Graph 
     * @return
     */
	@FXML
    public Image GraphToImage(BubbleChart<?, ?> Graph) {
        // TODO implement here
        return null;
    }
    /**
     * 
     */
	@FXML
    public void setCluster() {
        // TODO implement here
    }

    /**
     * 
     */
	@FXML
    public void ExportJpg() {
        // TODO implement here
    }

    /**
     * 
     */
	@FXML
    public void ExportCsv() {
        // TODO implement here
    }

    /**
     * 
     */
	@FXML
    public void UpdateGraph() {
        // TODO implement here
    }
    /**
     * 
     */
	@FXML
    public void initialize() {
        // TODO implement here

    }
}