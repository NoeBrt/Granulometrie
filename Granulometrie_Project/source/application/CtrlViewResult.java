package application;

import java.util.*;

import Model.GranuloData;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * 
 */
public class CtrlViewResult {

    /**
     * Default constructor
     */
    public CtrlViewResult() {
    }

    /**
     * 
     */
    private BubbleChart graphNbGrainSize;

    /**
     * 
     */
    private BubbleChart graphNbGrainArea;

    /**
     * 
     */
    private TextField comment;

    /**
     * 
     */
    private Button saveDataBaseButton;

    /**
     * 
     */
    private TextField sizeMax;

    /**
     * 
     */
    private TextField sizeMin;

    /**
     * 
     */
    private TextField clusterWidth;

    /**
     * 
     */
    private Button exportButton;


    /**
     * @param model
     */
    public void CtrlResultView(GranuloData model) {
        // TODO implement here
    }
    /**
     * 
     */
    public void setScaleMinMax() {
        // TODO implement here
    }
    /**
     * 
     */
    public void saveDataBase() {
        // TODO implement here
    }
    /**
     * @param Graph 
     * @return
     */
    public Image GraphToImage(BubbleChart<?, ?> Graph) {
        // TODO implement here
        return null;
    }
    /**
     * 
     */
    public void setCluster() {
        // TODO implement here
    }

    /**
     * 
     */
    public void ExportJpg() {
        // TODO implement here
    }

    /**
     * 
     */
    public void ExportCsv() {
        // TODO implement here
    }

    /**
     * 
     */
    public void UpdateGraph() {
        // TODO implement here
    }

}