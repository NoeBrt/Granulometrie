package Model;

import javafx.scene.image.Image;

public class ParameterDB {
private int idParameter;
private float sizeMin;
private float sizeMax;
private int nbCategorySize;
private int nbCategorySurface;
private Image Chart1;
private Image Chart2;
private String DateCalcul;
private String Calcul;
private String comment;
private int idImage;
public ParameterDB(int idParameter, float sizeMin, float sizeMax, int nbCategorySize, int nbCategorySurface,
		Image chart1, Image chart2, String dateCalcul, String calcul, String comment, int idImage) {
	super();
	this.idParameter = idParameter;
	this.sizeMin = sizeMin;
	this.sizeMax = sizeMax;
	this.nbCategorySize = nbCategorySize;
	this.nbCategorySurface = nbCategorySurface;
	Chart1 = chart1;
	Chart2 = chart2;
	DateCalcul = dateCalcul;
	Calcul = calcul;
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
 * @return the chart1
 */
public Image getChart1() {
	return Chart1;
}
/**
 * @param chart1 the chart1 to set
 */
public void setChart1(Image chart1) {
	Chart1 = chart1;
}
/**
 * @return the chart2
 */
public Image getChart2() {
	return Chart2;
}
/**
 * @param chart2 the chart2 to set
 */
public void setChart2(Image chart2) {
	Chart2 = chart2;
}
/**
 * @return the dateCalcul
 */
public String getDateCalcul() {
	return DateCalcul;
}
/**
 * @param dateCalcul the dateCalcul to set
 */
public void setDateCalcul(String dateCalcul) {
	DateCalcul = dateCalcul;
}
/**
 * @return the calcul
 */
public String getCalcul() {
	return Calcul;
}
/**
 * @param calcul the calcul to set
 */
public void setCalcul(String calcul) {
	Calcul = calcul;
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



}
