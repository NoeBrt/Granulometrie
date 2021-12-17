package app;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Liste d'images à traiter. 
 * On peut ajouter les noms des images un par un, ou bien donner un nom de dossier, avec une extension,
 * pour ajouter toutes les images du dossier, possédant la bonne extension.
 * @author thierrybrouard
 *
 */
public class ImagesToProcessList implements Iterable {

	private LinkedList<String> image_list;
	/**
	 * constructor
	 * initialize image_list as a LinkedList<String>()
	 * */
	public ImagesToProcessList() {
		super();
		this.image_list = new LinkedList<String>();
	}

	/** 
	 * add an image in the list from his name
	 * @param image_name name of the file to add
	 */
	public void addImageName(String image_name) {
		this.image_list.add(image_name);
	}

	/**
	 * add every image from a folder in the list
	 * @param image_path path of the folder
	 */
	public void addImagesFromFolder(String image_path) {
		File dir = new File(image_path);
		File[] liste = dir.listFiles();
		for (File item : liste) {
			if (item.isFile()) {
				addImageName(item.getAbsolutePath());
			}
		}
	}

	@Override
	/**
	 * return an Iterator to browse a list
	 */
	public Iterator iterator() {
		return this.image_list.iterator();
	}

	
}
