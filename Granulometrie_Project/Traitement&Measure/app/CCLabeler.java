package app;

import ij.IJ;
import ij.ImagePlus;
import ij.measure.ResultsTable;
import ij.plugin.filter.ParticleAnalyzer;
import ij.process.ImageProcessor;

public class CCLabeler {

	private MeasuresList measures_list = null;
	private String image_name = null;

	/**
	 * Binarise the images, black background and white character.
	 *
	 * @param imOriginale Image to� binarise
	 * @return image binaire
	 */
	private ImagePlus binarise(ImagePlus imOriginale) {
		ImagePlus imBinary = imOriginale.duplicate();
		imBinary.getProcessor().setAutoThreshold("Otsu");
		imBinary.getProcessor().autoThreshold();
		return imBinary;
	}

	/**
	 * Find every data in image
	 * 
	 * @param imBinary
	 * @return data
	 */
	private ResultsTable findParticles(ImagePlus imBinary) {
		ImagePlus imgpls = new ImagePlus("./ImageParticule/113_x63_zoom08_1.jpg");
		imBinary.copyAttributes(imgpls);
		int options = ij.plugin.filter.ParticleAnalyzer.SHOW_NONE;
		int measurements = ij.measure.Measurements.AREA + ij.measure.Measurements.CENTROID
				+ ij.measure.Measurements.RECT;

		ResultsTable data = new ResultsTable();
		ParticleAnalyzer pa = new ParticleAnalyzer(options, measurements, data, 0, 100000);
		pa.analyze(imBinary);

		return (data);
	}

	/**
	 * @param data
	 * @return measure_list
	 * 
	 *         it take all data which get find by findParticles and order it in a
	 *         new MeasureList then initialize this.measures_list and return it
	 */
	private MeasuresList getCCInformations(ResultsTable data) {
		// allocation structure
		MeasuresList measures_list = new MeasuresList(this.image_name);
		// recupère les données dans des tableaux
		double[] cenx = data.getColumnAsDoubles(ResultsTable.X_CENTROID);
		double[] ceny = data.getColumnAsDoubles(ResultsTable.Y_CENTROID);
		double[] area = data.getColumnAsDoubles(ResultsTable.AREA);
		double[] xmin = data.getColumnAsDoubles(ResultsTable.ROI_X);
		double[] ymin = data.getColumnAsDoubles(ResultsTable.ROI_Y);
		double[] height = data.getColumnAsDoubles(ResultsTable.ROI_HEIGHT);
		double[] width = data.getColumnAsDoubles(ResultsTable.ROI_WIDTH);

		// parcours des résultats
		for (int idx = 0; idx < cenx.length; idx++) {
			Measure measure = new Measure(area[idx], cenx[idx], ceny[idx], (int) xmin[idx], (int) ymin[idx],
					(int) width[idx], (int) height[idx]);
			measures_list.add(measure);
		}
		this.measures_list = measures_list;
		return measures_list;
	}

	/**
	 * List of measures This list have the image's measures. It be cleaned at every
	 * process, and contain only measures did by the last call of process()
	 * 
	 * @return MeasuresList
	 * @see MeasuresList
	 * @see CCLabeler
	 */
	public MeasuresList getMeasures() {
		return this.measures_list;
	}

	/**
	 * Generate the outPut image
	 */
	private void generate_output(ImagePlus imInput) {

		// copie de l'originale, améliore la dynamique
		ImagePlus imOutput = imInput.duplicate();
		ImageProcessor ip = imOutput.getProcessor();
		ip.gamma(0.41);
		imOutput.setProcessor(ip);

		// dessine les bbox et les centres
		ImageProcessor ipOut = imOutput.getProcessor();
		ipOut.setColor(java.awt.Color.RED);
		int delta = 5; // taille croix
		int width = imOutput.getWidth();
		int height = imOutput.getHeight();

		for (Measure info : this.measures_list) {
			int xcell = (int) info.getCentre_x();
			int ycell = (int) info.getCentre_y();
			// coordonnées croix
			int x1 = Math.max(0, xcell - delta);
			int x2 = Math.min(width, xcell + delta);
			int y3 = Math.max(0, ycell - delta);
			int y4 = Math.min(height, ycell + delta);
			// trace centre
			ipOut.drawLine(x1, ycell, x2, ycell);
			ipOut.drawLine(xcell, y3, xcell, y4);
			// trace bounding box
			ipOut.drawRect(info.getXstart(), info.getYstart(), info.getWidth(), info.getHeight());

		}
		IJ.save(imOutput, "output.jpg");
	}

	/**
	 * At the beginning it set the image_name to the local attribute image_name.
	 * After, it instantiate and ImagePlus image which get the return of openImage
	 * which if in the if package Then we binarise this image and store the result
	 * in an ImagePlus Object again. After this we create and ResultatsTable object
	 * which is the return of findParticles To finish it create an
	 * MeasuresListObject which is the return of the method getCCInformations .
	 * this.measure_list isn't set in the function beacuse it has been set in
	 * getCCInformations
	 * 
	 * @param image_name
	 * 
	 *
	 */
	public void process(String image_name) {

		this.image_name = image_name;
		ImagePlus imOriginale = IJ.openImage(this.image_name);
		ImagePlus imBinary = this.binarise(imOriginale);
		ResultsTable data = findParticles(imBinary);
		MeasuresList measure_list = getCCInformations(data);
	}
}
