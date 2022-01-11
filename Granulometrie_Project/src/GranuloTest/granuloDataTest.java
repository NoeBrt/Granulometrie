package GranuloTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import Model.GranuloData;
import app.CCLabeler;
import app.Measure;
import app.MeasuresList;

public class granuloDataTest {

	private GranuloData granulodata;

	/**
	 * This method will test The method process We will test it by comparing with
	 * the data the method has returned with the correct data.
	 * 
	 * @param image
	 */
	@Test
	public void TestContructor() {

		String url = "src/GranuloTest/113_x63_zoom08_1.jpg";
		GranuloData granulodata = new GranuloData(url);
		CCLabeler counter = new CCLabeler();
		MeasuresList list1 = counter.getMeasures();
		MeasuresList list2 = granulodata.getMeasures();
		counter.process(url);
		assertEquals(counter.getMeasures().get_nb_measures(), granulodata.getMeasures().get_nb_measures());

	}

	/**
	 * This method will test The method setScale. This method will test if the scale
	 * has been set.
	 * 
	 * @param min
	 * @param max
	 */

	@Test
	public void TestsetScale() {

		String url = "src/GranuloTest/113_x63_zoom08_1.jpg";
		GranuloData granulodata = new GranuloData(url);
		int scaleMin = 3, scaleMax = 10;
		granulodata.setScale(0, 0);
		assertTrue(granulodata.getMeasuresAfterScale().isEmpty());
		granulodata.setScale(scaleMin, scaleMax);
		int count = 0;
		boolean bothArePresent = false;
		for (Measure grain : granulodata.getMeasures()) {

			if (count == 2) {
				bothArePresent = true;
				break;
			}

			if ((double) grain.getSize() < scaleMin || (double) grain.getSize() > scaleMax) {
				count++;
			}
		}

		if (bothArePresent) {
			for (Measure grain : granulodata.getMeasuresAfterScale()) {
				if ((double) grain.getSize() < scaleMin || (double) grain.getSize() > scaleMax) {
					fail();
				}
			}
		}

		else {
			System.out.println("One of the values is not present in the list");
		}

	}

	/**
	 * This method will test the method setClusters This method will test if the
	 * cluster has been set.
	 * 
	 * @param etalon
	 */

	@Test
	public void TestsetClusters() {
		String url = "src/GranuloTest/113_x63_zoom08_1.jpg";
		GranuloData granulodata = new GranuloData(url);
		granulodata.setClusters(2.0);
		assertFalse(granulodata.getClusters().isEmpty());

	}

	public GranuloData getGranulodata() {
		return granulodata;
	}

	public void setGranulodata(GranuloData granulodata) {
		this.granulodata = granulodata;
	}

}
