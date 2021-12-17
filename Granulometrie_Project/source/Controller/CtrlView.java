package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.DragEvent;
/**
 * 
 */
public class CtrlView{
	  /**
     * import button import an image
     */
	@FXML
    private Button ImportButton;

	 /**
     * launch processing button start processing
     */
	@FXML
    private Button launchProcessButton;

	 /**
     * the image that will be loaded
     */
	@FXML
    private Image image;

	   /**
     * the view which display the image
     */
	@FXML
    private ImageView imgView;
	
	/**
    * this method import an image through a File Chooser if l'image isn't properly
    * imported the program throws FileNotFoundException
    * @param
    * this method has no parameters
    * @return void
    * this method has no return type
    */
	@FXML
    public void importImage() {
        // TODO implement here
    }

	 /**
     * handleDragOver method allows the interface detecting an image if the image is
     * Draged over the interface
     * the interface accepts any mode of image sending
     * such as copy paste for example
     * @param DragEvent event
     * this method has only one parameter if the image is in interaction with 
     * the interface then the image is detected
     * 
     * @return void
     * this method has no return type
     */
	@FXML
    public void handleDragOver(DragEvent event) {
        // TODO implement here
    }

	/**
     * handleDrop loads an image and display it in view through a drag and drop
     * action if any type of files is dragged except an image we throws IlligalArgumentException
     * @param DragEvent event
     * this method has one parameter loading an image in view
     * @return void
     * this method has no return type
     */
	@FXML
    public void handleDrop(DragEvent event) {
        // TODO implement here
    }

    /**
     * launchProcess method runs a new interface GrapheController to display
     * particles on 2 charts
     * @param
     * this method has no parameters
     * @return void
     * this method has no return type
     */
	@FXML
    public void launchProcess() {
        // TODO implement here
    }
    
    /**
     * @return void
     * Initialize is an implemented method allows the user to define actions to buttons without using fxml file
     */
	@FXML
    public void initialize() {
        // TODO implement here

    }

}