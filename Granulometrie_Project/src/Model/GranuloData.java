package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
	 * process(String url) and image with this.Image Date and Time will be
	 * instanciate with the current date & time with java.time.LocalDate.now() and
	 * java.time.LocalTime.now() set grain Scale with default value
	 * (setScale(min,max))
	 * 
	 * @param url
	 */
	public GranuloData(String url) {
		this.Measures = process(url);
		this.setImageUrl(url);
		this.MeasuresAfterScale = new LinkedList<>(Measures.getMeasuresList());
		this.imagePlus = new ImagePlus(url);
		this.comment = "";
		this.Date = LocalDate.now().toString();
		this.Time = LocalTime.now().toString().substring(0, 8);
		setScale(0, 15);
		this.Clusters = new LinkedHashMap<>();
		this.ClustersSurface = new LinkedHashMap<>();
		setClusters(2.0);
		setSurfaceClusters(10.0);
	}

	/**
	 * the methods store an image in ImageToProcessList object. the methods create
	 * an CCLabeler object. and process the image with the path (url) Finally it
	 * return CCLabeler.getMeasure
	 * 
	 * @param url
	 * @return the measures list after the image Traitement (CCLabeler.getMeasure)
	 * 
	 *
	 * 
	 **/
	public MeasuresList process(String url) {
		CCLabeler counter = new CCLabeler();
		counter.process(url);
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
	private int SizeGrainMin;

	/**
	 * maximum size of a grain for traitement (for result scaling )
	 */
	private int SizeGrainMax;

	/**
	 * User's image to process
	 */
	private ImagePlus imagePlus;

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

	/*
	 * Image url
	 */
	private String imageUrl;
	/*
	 * etalon of cluster
	 */
	private Double etalon;

	/*
	 * etalon of surface cluster
	 */
	private Double etalonSurface;

	/**
	 * Clusters contain a List of a Measure List with index (, allows to classify
	 * the measures according with some conditions set in setCluster
	 */
	private LinkedHashMap<Double, List<Measure>> Clusters;
	/**
	 * Clusters contain a List of a Measure List with index (, allows to classify
	 * the measures according with some conditions set in setCluster
	 */
	private LinkedHashMap<Double, List<Measure>> ClustersSurface;

	/**
	 * set scale of the Measures List max grain size< measures <min grain size, it
	 * remove measure element from the list who are <min and >max, it actualise
	 * Clusters too (size is the average of the grain principal axes)
	 * 
	 * @param min
	 * @param max
	 */
	public void setScale(int min, int max) {
		this.MeasuresAfterScale.clear();
		this.MeasuresAfterScale = new LinkedList<Measure>(this.Measures.getMeasuresList());
		if (min >= 0 && max >= 0) {
			for (Measure grain : this.Measures) {
				if ((double) grain.getSize() < min || (double) grain.getSize() > max) {
					this.MeasuresAfterScale.remove(grain);
				}
			}
		}
		this.SizeGrainMin = min;
		this.SizeGrainMax = max;
	}
	/**
	 * set scale of the Measures inferior to max
	 * 
	 * @param min
	 * @param max
	 */
	public void setScaleMax(int max) {
		this.MeasuresAfterScale.clear();
		this.MeasuresAfterScale = new LinkedList<Measure>(this.Measures.getMeasuresList());
		if (max >= 0) {
			for (Measure grain : this.Measures) {
				if ((double) grain.getSize() > max) {
					this.MeasuresAfterScale.remove(grain);
				}
			}
		}
		this.SizeGrainMax = max;
	}
	/**
	 * set scale of the Measures  superior to min
	 * 
	 * @param min
	 * @param max
	 */
	public void setScaleMin(int min) {
		this.MeasuresAfterScale.clear();
		this.MeasuresAfterScale = new LinkedList<Measure>(this.Measures.getMeasuresList());
		if (min >= 0) {
			for (Measure grain : this.Measures) {
				if ((double) grain.getSize() < min) {
					this.MeasuresAfterScale.remove(grain);
				}
			}
		}
		this.SizeGrainMin = min;

	}

	/**
	 * Create a LinkedHashMap<Double, Measure> Cluster and set Scale of each
	 * Cluster (index & List<Measure>) according to Grains's size (if float=2.0,
	 * then list<Mesuse> with index=2 will contain Grains Measure size beetween 0
	 * and <=2.0. index=4 Grains Measure size beetween 2.0 & <=4.0 ...ect)
	 * 
	 * @param void
	 * 
	 */
	public void setClusters(Double etalon) {
		this.Clusters.clear();
		if (etalon > 0) {
			this.etalon = etalon;
			LinkedList<Measure> MeasuresTemp = new LinkedList<>(this.MeasuresAfterScale);
			Double keyEtalon = etalon;
			do {
				for (Measure grain : MeasuresTemp) {
					if (!this.Clusters.containsKey(keyEtalon)) {
						this.Clusters.put(keyEtalon, new ArrayList<>());
					}
					if ((double) grain.getSize() > (keyEtalon - etalon) && (double) grain.getSize() <= keyEtalon) {
						this.Clusters.get(keyEtalon).add(grain);
					}
				}
				MeasuresTemp.removeAll(Clusters.get(keyEtalon));
				keyEtalon += etalon;
			} while (!MeasuresTemp.isEmpty());
		}
	}

	/**
	 * Create a LinkedHashMap<Double, Measure> Cluster and set Scale of each
	 * Cluster (index & List<Measure>) according to Grains's surface (if float=2.0,
	 * then list<Mesuse> with index=2 will contain Grains Measure size beetween 0
	 * and <=2.0. index=4 Grains Measure size beetween 2.0 & <=4.0 ...ect)
	 * 
	 * @param void
	 * 
	 */
	public void setSurfaceClusters(Double etalon) {
		this.ClustersSurface.clear();
		if (etalon > 0) {
			this.etalonSurface = etalon;
			LinkedList<Measure> MeasuresTemp = new LinkedList<>(this.MeasuresAfterScale);
			Double keyEtalon = etalon;
			do {
				for (Measure grain : MeasuresTemp) {
					if (!this.ClustersSurface.containsKey(keyEtalon)) {
						this.ClustersSurface.put(keyEtalon, new ArrayList<>());
					}
					if (grain.getAire() > (keyEtalon - etalon) && grain.getAire() <= keyEtalon) {
						this.ClustersSurface.get(keyEtalon).add(grain);
					}
				}
				MeasuresTemp.removeAll(ClustersSurface.get(keyEtalon));
				keyEtalon += etalon;
			} while (!MeasuresTemp.isEmpty());
		}
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
	public int getSizeGrainMin() {

		return SizeGrainMin;
	}

	/**
	 * @return the tailleGrainMax
	 */
	public int getSizeGrainMax() {
		return SizeGrainMax;
	}

	/**
	 * @param tailleGrainMax the tailleGrainMax to set
	 */
	public void setTailleGrainMax(int SizeGrainMax) {
		this.SizeGrainMax = SizeGrainMax;
	}

	/**
	 * @return the image in ImagePlus
	 */
	public ImagePlus getImagePlus() {
		return imagePlus;
	}

	/**
	 * @return the image in Image javaFx
	 */
	public Image getImage() {
		return new Image(imageUrl);
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(ImagePlus image) {
		this.imagePlus = image;
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
	public LinkedHashMap<Double, List<Measure>> getClusters() {
		setClusters(this.etalon);
		return Clusters;
	}

	/**
	 * @return the clustersSurface
	 */
	public LinkedHashMap<Double, List<Measure>> getClustersSurface() {
		setSurfaceClusters(this.etalonSurface);
		return ClustersSurface;
	}

	/**
	 * @return the measures after scale
	 */
	public List<Measure> getMeasuresAfterScale() {
		return MeasuresAfterScale;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the etalon
	 */
	public Double getEtalon() {
		return etalon;
	}

	public Double getEtalonSurface() {
		return etalonSurface;
	}

	/**
	 * @param measures the measures to set
	 */
	public void setMeasures(MeasuresList measures) {
		Measures = measures;
	}

	/**
	 * @param measuresAfterScale the measuresAfterScale to set
	 */
	public void setMeasuresAfterScale(LinkedList<Measure> measuresAfterScale) {
		MeasuresAfterScale = measuresAfterScale;
	}

	/**
	 * @param sizeGrainMin the sizeGrainMin to set
	 */
	public void setSizeGrainMin(int sizeGrainMin) {
		SizeGrainMin = sizeGrainMin;
	}

	/**
	 * @param sizeGrainMax the sizeGrainMax to set
	 */
	public void setSizeGrainMax(int sizeGrainMax) {
		SizeGrainMax = sizeGrainMax;
	}

	/**
	 * @param imagePlus the imagePlus to set
	 */
	public void setImagePlus(ImagePlus imagePlus) {
		this.imagePlus = imagePlus;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		Date = date;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		Time = time;
	}

	/**
	 * @param etalon the etalon to set
	 */
	public void setEtalon(Double etalon) {
		this.etalon = etalon;
	}

	/**
	 * @param etalonSurface the etalonSurface to set
	 */
	public void setEtalonSurface(Double etalonSurface) {
		this.etalonSurface = etalonSurface;
	}

	/**
	 * @param clusters the clusters to set
	 */
	public void setClusters(LinkedHashMap<Double, List<Measure>> clusters) {
		Clusters = clusters;
	}

	/**
	 * @param clustersSurface the clustersSurface to set
	 */
	public void setClustersSurface(LinkedHashMap<Double, List<Measure>> clustersSurface) {
		ClustersSurface = clustersSurface;
	}

}