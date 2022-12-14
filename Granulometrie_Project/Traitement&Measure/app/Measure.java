package app;

/**
 * Storage for one measure.
 * 
 * @author thierrybrouard
 *
 */
public class Measure {

	private double aire;		// aire de la composante
	private double centre_x;	// coordonnée x du centre
	private double centre_y;	// coordonnée y du centre
	private int xstart;	        // bounding box coord x min
	private int width;	        // bounding box largeur
	private int ystart;	        // bounding box coord y min
	private int height;	        // bounding box hauteur

	public Measure(double aire, double centrex, double centrey, int xstart, int ystart, int width, int height) {
		this.setAire(aire);
		this.setCentre_x(centrex);
		this.setCentre_y(centrey);
		this.setXstart(xstart);
		this.setYstart(ystart);
		this.setWidth(width);
		this.setHeight(height);
	}

	public int getXstart() {
		return xstart;
	}

	public void setXstart(int xstart) {
		this.xstart = xstart;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getYstart() {
		return ystart;
	}

	public void setYstart(int ystart) {
		this.ystart = ystart;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getAire() {
		return aire;
	}

	private void setAire(double aire) {
		this.aire = aire;
	}
	
	public double getCentre_x() {
		return centre_x;
	}
	
	private void setCentre_x(double centre_x) {
		this.centre_x = centre_x;
	}
	
	public double getCentre_y() {
		return centre_y;
	}
	
	private void setCentre_y(double centre_y) {
		this.centre_y = centre_y;
	}

	public double getSize() {
		return (double)((this.width+this.height)/2);
	}
	
	public double getRoundness() {
		return (this.aire/(((double) this.height/2)*((double) this.height/2)*Math.PI))*100;
	}
	public double getRelativeSurface(double ImageWidht,double ImageHeight){
		return (this.aire/(ImageWidht*ImageHeight)*100000);
		
	}
	
	@Override
	public String toString() {
		return "Measure{" +
				"aire=" + aire +
				", centre_x=" + centre_x +
				", centre_y=" + centre_y +
				", xstart=" + xstart +
				", width=" + width +
				", ystart=" + ystart +
				", height=" + height +
				"}\n";
	}
}
