
import java.util.*;

/**
 * 
 */
public class CtrlViewResult (from controlers) {

    /**
     * Default constructor
     */
    public CtrlViewResult (from controlers)() {
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
     * 
     */
    private ViewResult (from  views) ctrl;



    /**
     * @param model
     */
    public void CtrlResultView(GranuloData (from models) model) {
        // TODO implement here
    }

    /**
     * @param view
     */
    public void setView(ViewResult (from  views) view) {
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
    public Image GraphToImage(BubbleChart Graph) {
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