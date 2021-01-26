/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Issam
 */
public class Planning {
    
    private int id;
    private int annee;
    private String description;
    //private ArrayList<Projection> projections;
    
    public Planning(int id, int annee, String description)
    {
        this.id = id;
        this.annee = annee;
        this.description = description;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the annee
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * @param annee the annee to set
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the projections
     */
    /*
    public ArrayList<Projection> getProjections() {
        return projections;
    }

    /**
     * @param projections the projections to set
     */
    /*
    public void setProjections(ArrayList<Projection> projections) {
        this.projections = projections;
    }*/
}
