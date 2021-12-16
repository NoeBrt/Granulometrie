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
     * Constructor of GranuloData object,
     * it instanciate Measures with process(image) and image with this.Image
     * Date and Time will be instanciate with the current date & time
     *     
     */
    public GranuloData(Image image) {
        // TODO implement here
    }
    /**
     * Measure list, one grain specifities for each Measure
     */
    private List<Measure> Measures;

    /**
     *minimum size  of a grain for traitement (for result scaling )
     */
    private Float grainMin;

    /**
     *maximum size  of a grain for traitement (for result scaling )
     */
    private Float grainMax;

    /**
     * User's image to process
     */
    private Image image;

    /**
     * user comment of image 
     */
    private String comment;

    /**
     * current Date
     */
    private String Date;

    /**
     * current Time
     */
    private String Time;

    /**
     * Clusters contain a List of a Measure List with index, allows to classify the measures according with some conditions
     */
    private HashMap<Integer, List<Measure>> Clusters;

   
    /**
     * @param image
     * @return the measures list after the image Traitement (CCLabeler.getMeasure)
     * 
     * the methods store an image in ImageToProcessList object. It addImageName(image.getUrl()) will put this in ImageToProcessList's LinkedList
     * after that, the methods create an CCLabeler object. Then, for each Image in the list, it past the images url in the CCLabeler. 
     * Finally it return CCLabeler.getMeasure
     * 
     */
    public List<Measure> process(Image image) {
		return Measures;
    }
    /**
     * @param min 
     * @param max
     */
    public void setScale(float min, float max) {
        // TODO implement here
    }

    /**
     * Create a HashMap<Integer, Measure> Cluster and set Scale of each Cluster (index & List<Measure>) according to Grains's size 
     * (if float=2.0, then list<Mesuse> with index=0 will contain Grains Measure size beetween 0 and <2.0. index=1 Grains Measure size beetween 2.0 & <4.0 ...ect)
     */
    public void setClusters(float a) {
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
	public HashMap<Integer, List<Measure>> getClusters() {
		return Clusters;
	}

}