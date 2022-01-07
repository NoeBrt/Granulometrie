package GranuloTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;

import org.junit.Test;
import org.junit.experimental.max.MaxCore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import Model.GranuloData;
import app.CCLabeler;
public class granuloDataTest {

	/**
	 * This method will test The method process We will test it by comparing with
	 * the data the method has returned with the correct data.
	 * 
	 * @param image
	 */
	@Test
	public void TestContructor() {
		
			GranuloData granulodata = new GranuloData("C:\\Users\\noebr\\OneDrive - Université de Tours\\Rapport de projet\\WorkspaceGranu\\granulometrie\\Granulometrie_Project\\src\\GranuloTest\\113_x63_zoom08_1.jpg");
			System.out.println(granulodata.getImagePlus().getHeight()); 

		
	/*	CCLabeler counter = new CCLabeler();
		counter.process(url);*/
		//assertEquals(counter.getMeasures(), granulodata.getMeasures());

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
		// TODO implement here
	}

	/**
	 * This method will test the method setClusters This method will test if the
	 * cluster has been set.
	 * 
	 * @param etalon
	 */

	@Test
	public void TestsetClusters() {
		// TODO implement here
	}

}
