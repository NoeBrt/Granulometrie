package test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import Model.GranuloData;
import app.CCLabeler;
import junit.framework.TestCase;

class granuloDataTest extends TestCase {

	/**
	 * This method will test The method process
	 * We will test it by comparing  with the data the method has returned with the correct data.
	 * @param image
	 */
	@Test
	public void TestContructor() {
		String url  = "/Granulometrie_Project/ImageParticule/113_x63_zoom08_1.jpg";
		GranuloData granulodata = new GranuloData(url);
		CCLabeler counter = new CCLabeler();
		counter.process(url);
		assertEquals(counter.getMeasures(), granulodata.getMeasures());
		
	}
	/**
	 * This method will test The method setScale.
	 * This method will test if the scale has been set.
	 * @param min
	 * @param max
	 */
	
	@Test
	public void TestsetScale(float min, float max) {
        // TODO implement here
    }
	
	/**
	 * This method will test the method setClusters 
	 * This method will test if the cluster has been set.
	 * @param etalon
	 */

	@Test
    public void TestsetClusters(float etalon) {
        // TODO implement here
    }

}
