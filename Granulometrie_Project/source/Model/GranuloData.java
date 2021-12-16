package Model;


import java.util.HashMap;
import java.util.List;

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

    
    
    
    
    // Getter & Setter 
	/**
	 * @return the measures
	 */
	public List<Measure> getMeasures() {
		return Measures;
	}

	/**
	 * @param measures the measures to set
	 */
	public void setMeasures(List<Measure> measures) {
		Measures = measures;
	}

	/**
	 * @return the grainMin
	 */
	public Float getGrainMin() {
		return grainMin;
	}

	/**
	 * @param grainMin the grainMin to set
	 */
	public void setGrainMin(Float grainMin) {
		this.grainMin = grainMin;
	}

	/**
	 * @return the grainMax
	 */
	public Float getGrainMax() {
		return grainMax;
	}

	/**
	 * @param grainMax the grainMax to set
	 */
	public void setGrainMax(Float grainMax) {
		this.grainMax = grainMax;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return Date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		Date = date;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return Time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		Time = time;
	}

	/**
	 * @return the cluster
	 */
	public HashMap<Integer, Measure> getCluster() {
		return Cluster;
	}

}