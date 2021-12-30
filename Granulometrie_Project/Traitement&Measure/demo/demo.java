package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import app.CCLabeler;
import app.ImagesToProcessList;
import app.Measure;
import app.MeasuresList;

public class demo {

	public static void main(String[] args) {

		// cree une liste d'images à traiter
		ImagesToProcessList ipl = new ImagesToProcessList();
//		ipl.addImagesFromFolder("./images/document/"); // 10 images
		// ipl.addImageName("./images/document/00000.jpg");
		ipl.addImageName("./ImageParticule/particule/113_x63_zoom08_1.jpg");
		// traite chaque image de la liste
		CCLabeler counter = new CCLabeler();
		// traite l'image et compte les particules
		String imagename_to_process = "./ImageParticule/particule/113_x63_zoom08_1.jpg";
		System.out.println(imagename_to_process);
		counter.process(imagename_to_process);

		// recupère les mesures de l'image traitée
		MeasuresList measure_list = counter.getMeasures();
		System.out.println(measure_list);
		/*
		ArrayList<Measure> MeasuresTemp1 = new ArrayList<>(measure_list.getMeasures());

		ArrayList<Measure> MeasuresTemp = new ArrayList<>(measure_list.getMeasures());
		// System.out.println(MeasuresTemp);

		int etalon = 2;
		int etalon1 = 0;
		int etalon2 = 2;
		HashMap<Integer, List<Measure>> Clusters = new HashMap<>();
		Measure m = null;
	//	m = MeasuresTemp.getFirst();

		/*
		 * 
		 * for (Measure grain : MeasuresTemp) { if (grain.getSize()>m.getSize())
		 * m=grain; }
		 * 
		 * System.out.println(m.getSize()); int size=0; int etalon3=2;
		 * 
		 * while (m.getSize()+etalon>=etalon3) { Clusters.put(etalon3, new
		 * ArrayList<>()); etalon3+=2;
		 * 
		 * } System.out.println(Clusters.keySet());
		 * 
		 * 
		 * for (Integer k:Clusters.keySet()) { for (Measure grain : MeasuresTemp) { if
		 * (grain.getSize() > (k-etalon)&&grain.getSize()<k) {
		 * Clusters.get(k).add(grain); } } MeasuresTemp.removeAll(Clusters.get(k)); }
		 * for (Iterator<Integer> iterator = Clusters.keySet().iterator();
		 * iterator.hasNext(); ) { Integer value = iterator.next(); if
		 * (Clusters.get(value).isEmpty()) { iterator.remove(); } }
		 * 
		 * int size1=0; for ( Integer a: Clusters.keySet() ) {
		 * size1+=Clusters.get(a).size(); } System.out.println(Clusters);
		 * System.out.println(Clusters.size());
		 * 
		 * //System.out.println(MeasuresTemp);
		 * 
		 */
		/*
		int size = 0;
		etalon1 = 0;
		etalon2 = 2;
		do {
			for (Measure grain : MeasuresTemp) {
				if (!Clusters.containsKey(etalon2)) {
					Clusters.put(etalon2, new ArrayList<>());
				}
				if (grain.getSize() > (etalon2 - etalon) && grain.getSize() <= etalon2) {
					Clusters.get(etalon2).add(grain);
				}
			}
			MeasuresTemp.removeAll(Clusters.get(etalon2));
			etalon2 += etalon;
			System.out.println(Clusters.toString());
		} while (!MeasuresTemp.isEmpty());
		

		
		  System.out.println(Clusters); int size1=0; for ( Integer a: Clusters.keySet()
		  ) { size1+=Clusters.get(a).size();
		 }
		  System.out.println(MeasuresTemp1.size());
		  System.out.println(size1); 
		 
	*/
}}
