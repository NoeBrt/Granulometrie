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
     * Date and Time will be instanciate with the current date & time with java.time.LocalDate.now() and java.time.LocalTime.now()
     * set grain Scale with default value (setScale(min,max))
     *     
     */
    public GranuloData(Image image) {
        // TODO implement here
    }
    /**
     * Measure list, each Measure represent grain specifities  
     */
    private List<Measure> Measures;
    
    /**
     * Measure list result after the Scale, used to conserve the value of the initial Measure List
     */
    private List<Measure> MeasuresAfterScale;

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
     * Clusters contain a List of a Measure List with index, allows to classify the measures according with some conditions set in setCluster
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
     * set scale of the Measures List max grain size< measures <min grain size,  it remove measure element from the list who are <min and >max, it actualise Clusters too
     */
    public void setScale(float min, float max) {
        // TODO implement here
    }

    /**
     * @param 
     * Create a HashMap<Integer, Measure> Cluster and set Scale of each Cluster (index & List<Measure>) according to Grains's size 
     * (if float=2.0, then list<Mesuse> with index=0 will contain Grains Measure size beetween 0 and <2.0. index=1 Grains Measure size beetween 2.0 & <4.0 ...ect)
     */
    public void setClusters(float etalon) {
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
	 * @param comment the Image 
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
	 * @return the time
	 */
	public String getTime() {
		return Time;
	}
	/**
	 * @return the cluster
	 */
	public HashMap<Integer, List<Measure>> getClusters() {
		return Clusters;
	}
	
	/**
	 * @return the measures after scale 
	 */
	public List<Measure> getMeasuresAfterScale() {
		return MeasuresAfterScale;
	}

}