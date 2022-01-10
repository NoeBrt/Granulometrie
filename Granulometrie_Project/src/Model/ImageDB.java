package Model;

import javafx.scene.image.Image;

public class ImageDB {
private int idImage;
private Image image;
private float longueur;
private float largeur;
private int grossisement;
private String commentaire;



public ImageDB(int idImage, Image image, float longueur, float largeur, int grossisement, String commentaire) {
	super();
	this.idImage = idImage;
	this.image = image;
	this.longueur = longueur;
	this.largeur = largeur;
	this.grossisement = grossisement;
	this.commentaire = commentaire;
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
 * @return the longueur
 */
public float getLongueur() {
	return longueur;
}
/**
 * @param longueur the longueur to set
 */
public void setLongueur(float longueur) {
	this.longueur = longueur;
}
/**
 * @return the largeur
 */
public float getLargeur() {
	return largeur;
}
/**
 * @param largeur the largeur to set
 */
public void setLargeur(float largeur) {
	this.largeur = largeur;
}
/**
 * @return the grossisement
 */
public int getGrossisement() {
	return grossisement;
}
/**
 * @param grossisement the grossisement to set
 */
public void setGrossisement(int grossisement) {
	this.grossisement = grossisement;
}
/**
 * @return the commentaire
 */
public String getCommentaire() {
	return commentaire;
}
/**
 * @param commentaire the commentaire to set
 */
public void setCommentaire(String commentaire) {
	this.commentaire = commentaire;
}


}
