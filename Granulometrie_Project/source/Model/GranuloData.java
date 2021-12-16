package Model;

import java.util.*;

import javafx.scene.image.Image;

/**
 * 
 */
public class GranuloData {
	 /**
     * @param image
     */
    public GranuloData(Image image) {
        // TODO implement here
    }
    /**
     * 
     */
    private List<Measure> Measures;

    /**
     * 
     */
    private Float grainMin;

    /**
     * 
     */
    private Float grainMax;

    /**
     * 
     */
    private Image image;

    /**
     * 
     */
    private String comment;

    /**
     * 
     */
    private String Date;

    /**
     * 
     */
    private String Time;

    /**
     * 
     */
    private HashMap<Integer, Measure> Cluster;

   

    /**
     * @param min 
     * @param max
     */
    public void setScale(float min, float max) {
        // TODO implement here
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
    public void setComment() {
        // TODO implement here
    }

}