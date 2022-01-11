package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ParameterDB {
	private int idParameter;
	private float sizeMin;
	private float sizeMax;
	private int nbCategorySize;
	private int nbCategorySurface;
	private ImageView chart1;
	private ImageView chart2;
	private String dateCalcul;
	private String heureCalcul;
	private String comment;
	private int idImage;

	public ParameterDB(int idParameter, float sizeMin, float sizeMax, int nbCategorySize, int nbCategorySurface,
			Image chart1, Image chart2, String dateCalcul, String heureCalcul, String comment, int idImage) {
		super();
		this.idParameter = idParameter;
		this.sizeMin = sizeMin;
		this.sizeMax = sizeMax;
		this.nbCategorySize = nbCategorySize;
		this.nbCategorySurface = nbCategorySurface;
		this.chart1 = new ImageView(chart1);
		this.chart1.setFitHeight(40);
		this.chart1.setFitWidth(40);;
		this.chart2 = new ImageView(chart2);
		this.chart2.setFitHeight(40);
		this.chart2.setFitWidth(40);;
		this.dateCalcul = dateCalcul;
		this.heureCalcul = heureCalcul;
		this.comment = comment;
		this.idImage = idImage;
	}

	/**
	 * @return the idParameter
	 */
	public int getIdParameter() {
		return idParameter;
	}

	/**
	 * @param idParameter the idParameter to set
	 */
	public void setIdParameter(int idParameter) {
		this.idParameter = idParameter;
	}

	/**
	 * @return the sizeMin
	 */
	public float getSizeMin() {
		return sizeMin;
	}

	/**
	 * @param sizeMin the sizeMin to set
	 */
	public void setSizeMin(float sizeMin) {
		this.sizeMin = sizeMin;
	}

	/**
	 * @return the sizeMax
	 */
	public float getSizeMax() {
		return sizeMax;
	}

	/**
	 * @param sizeMax the sizeMax to set
	 */
	public void setSizeMax(float sizeMax) {
		this.sizeMax = sizeMax;
	}

	/**
	 * @return the nbCategorySize
	 */
	public int getNbCategorySize() {
		return nbCategorySize;
	}

	/**
	 * @param nbCategorySize the nbCategorySize to set
	 */
	public void setNbCategorySize(int nbCategorySize) {
		this.nbCategorySize = nbCategorySize;
	}

	/**
	 * @return the nbCategorySurface
	 */
	public int getNbCategorySurface() {
		return nbCategorySurface;
	}

	/**
	 * @param nbCategorySurface the nbCategorySurface to set
	 */
	public void setNbCategorySurface(int nbCategorySurface) {
		this.nbCategorySurface = nbCategorySurface;
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
	 * @return the idImage
	 */
	public int getIdImage() {
		return idImage;
	}

	/**
	 * @param idImage the idImage to set
	 */
	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	/**
	 * @return the chart1
	 */
	public ImageView getChart1() {
		return chart1;
	}

	/**
	 * @param chart1 the chart1 to set
	 */
	public void setChart1(ImageView chart1) {
		this.chart1 = chart1;
	}

	/**
	 * @return the chart2
	 */
	public ImageView getChart2() {
		return chart2;
	}

	/**
	 * @param chart2 the chart2 to set
	 */
	public void setChart2(ImageView chart2) {
		this.chart2 = chart2;
	}

	/**
	 * @return the dateCalcul
	 */
	public String getDateCalcul() {
		return dateCalcul;
	}

	/**
	 * @param dateCalcul the dateCalcul to set
	 */
	public void setDateCalcul(String dateCalcul) {
		this.dateCalcul = dateCalcul;
	}


	/**
	 * @return the heureCalcul
	 */
	public String getHeureCalcul() {
		return heureCalcul;
	}

	/**
	 * @param heureCalcul the heureCalcul to set
	 */
	public void setHeureCalcul(String heureCalcul) {
		this.heureCalcul = heureCalcul;
	}

	/**
	 * @param heurecalcul the heurecalcul to set
	 */
	public void setHeurecalcul(String heurecalcul) {
		this.heureCalcul = heurecalcul;
	}

	@Override
	public String toString() {
		return "ParameterDB [idParameter=" + idParameter + ", sizeMin=" + sizeMin + ", sizeMax=" + sizeMax
				+ ", nbCategorySize=" + nbCategorySize + ", nbCategorySurface=" + nbCategorySurface + ", chart1="
				+ chart1 + ", chart2=" + chart2 + ", dateCalcul=" + dateCalcul + ", heurecalcul=" + heureCalcul
				+ ", comment=" + comment + ", idImage=" + idImage + "]";
	}

	

}
