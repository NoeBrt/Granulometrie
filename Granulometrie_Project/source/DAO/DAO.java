package DAO;

import java.sql.Connection;
import java.util.*;

import Model.GranuloData;
import javafx.scene.image.Image;

/**
 * 
 */
public class DAO {

    /**
     * Default constructor
     */
    public DAO() {
    }

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private String login;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Connection connection;

    /**
     * @param url 
     * @param login 
     * @param password
     */
    private DAO(String  url, String  login, String password) {
        // TODO implement here
    }

    /**
     * @param data
     */
    public void insertData(GranuloData data) {
        // TODO implement here
    }

    /**
     * 
     */
    public void getDB() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getData() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getTableImages() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getTableGrains() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getTableParametrage() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getTableFamille() {
        // TODO implement here
    }

    /**
     * @param image
     */
    public void getData(Image image) {
        // TODO implement here
    }

    /**
     * @param index
     */
    public void getData(int index) {
        // TODO implement here
    }

    /**
     * @param index
     */
    public void deleteData(int index) {
        // TODO implement here
    }

    /**
     * @param data 
     * @param image
     */
    public void insertParametrage(GranuloData data, Image image) {
        // TODO implement here
    }

    /**
     * @param Image 
     * @param idParametrege
     */
    public void DeleteParametrage(Image images, int idParametrege) {
        // TODO implement here
    }

    /**
     * @param Image 
     * @param idParametrage
     */
    public void UpdateParametrage(Image Image, int idParametrage) {
        // TODO implement here
    }

}