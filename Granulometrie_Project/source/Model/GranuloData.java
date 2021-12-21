package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import app.CCLabeler;
import app.Measure;
import app.MeasuresList;
import ij.ImagePlus;
import javafx.scene.image.Image;

/**
 * Images and traitement will be done here
 */
public class GranuloData {
	/**
	 * Constructor of GranuloData object, it instanciate Measures with
	 * process(image) and image with this.Image Date and Time will be instanciate
	 * with the current date & time with java.time.LocalDate.now() and
	 * java.time.LocalTime.now() set grain Scale with default value
	 * (setScale(min,max))
	 * 
	 * @param image
	 */
	public GranuloData(Image image) {
		this.Measures = process(image);
		this.MeasuresAfterScale = new LinkedList<>(Measures.getMeasures());
		this.image = image;
		this.comment = "";
		this.Date = LocalDate.now().toString();
		this.Time = LocalTime.now().toString().substring(0, 8);
		setScale(0, 10);
	}

	/**
	 * the methods store an image in ImageToProcessList object. It
	 * addImageName(image.getUrl()) will put this in ImageToProcessList's LinkedList
	 * after that, the methods create an CCLabeler object. Then, for each Image in
	 * the list, it past the images url in the CCLabeler. Finally it return
	 * CCLabeler.getMeasure
	 * 
	 * @param image
	 * @return the measures list after the image Traitement (CCLabeler.getMeasure)
	 * 
	 *
	 * 
	 */
	public MeasuresList process(Image image) {
		CCLabeler counter = new CCLabeler();
		counter.process(image.getUrl());
		return counter.getMeasures();
	}

	/**
	 * Measure list, each Measure represent a grain specifities, import from
	 * app.MeasureList
	 */
	private MeasuresList Measures;

	/**
	 * Measure list result after the Scale, used to conserve the value of the
	 * initial Measure List
	 */
	private LinkedList<Measure> MeasuresAfterScale;

	/**
	 * minimum size of a grain for traitement (for result scaling )
	 */
	private Float tailleGrainnMin;

	/**
	 * maximum size of a grain for traitement (for result scaling )
	 */
	private int tailleGrainMax;

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
	 * Clusters contain a List of a Measure List with index (, allows to classify the
	 * measures according with some conditions set in setCluster
	 */
	private HashMap<Integer, List<Measure>> Clusters;

	/**
	 * set scale of the Measures List max grain size< measures <min grain size, it
	 * remove measure element from the list who are <min and >max, it actualise
	 * Clusters too (size is the average of the grain principal axes)
	 * 
	 * @param min
	 * @param max
	 */
	public void setScale(int min, int max) {
		this.Clusters = new HashMap<>();
		for (Measure grain : this.MeasuresAfterScale) {
			if (grain.getSize() < min || grain.getSize() > max) {
				this.MeasuresAfterScale.remove(grain);
			}
		}

	}

	/**
	 * Create a HashMap<Integer, Measure> Cluster and set Scale of each Cluster
	 * (index & List<Measure>) according to Grains's size (if float=2.0, then
	 * list<Mesuse> with index=0 will contain Grains Measure size beetween 0 and
	 * <2.0. index=1 Grains Measure size beetween 2.0 & <4.0 ...ect)
	 * 
	 * @param void
	 * 
	 */
	public void setClusters(int etalon) {
		LinkedList<Measure> MeasuresTemp = new LinkedList<>(this.MeasuresAfterScale);
		
		while (!MeasuresTemp.isEmpty()) {
		for (Measure grain : MeasuresTemp) {
		if (grain.getSize()>etalon){
			Clusters.put(etalon, new ArrayList<>());
			Clusters.get(etalon).add(grain);
			MeasuresTemp.remove(grain);
		}
		}
		etalon+=etalon;}
		
	}

	// Getter & Setter

	/**
	 * @return the measures
	 */
	public MeasuresList getMeasures() {
		return Measures;
	}

	/**
	 * @return the tailleGrainnMin
	 */
	public Float getTailleGrainnMin() {
		return tailleGrainnMin;
	}
	/**
	 * @return the tailleGrainMax
	 */
	public int getTailleGrainMax() {
		return tailleGrainMax;
	}

	/**
	 * @param tailleGrainMax the tailleGrainMax to set
	 */
	public void setTailleGrainMax(int tailleGrainMax) {
		this.tailleGrainMax = tailleGrainMax;
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