/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author Issam
 */
public class Projection {
    
    private int id;
    private Timestamp DateDebut;
    private Timestamp DateFin;
    private Salle salle;
    private Film film;
    private boolean seanceLendemain;
    private int id_planning;
    //private ArrayList<ArrayList<Vip>> vips;
    
    public Projection(int id, Timestamp DateDebut, Timestamp DateFin, Salle salle, Film film,boolean seanceLendemain, int id_planning) 
    {
        this.id = id;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.salle = salle;
        this.film = film;
        this.seanceLendemain = seanceLendemain;
        this.id_planning = id_planning;
        //this.vips = vips;
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
     * @return the DateDebut
     */
    public Timestamp getDateDebut() {
        return DateDebut;
    }

    /**
     * @param DateDebut the DateDebut to set
     */
    public void setDateDebut(Timestamp DateDebut) {
        this.DateDebut = DateDebut;
    }

    /**
     * @return the DateFin
     */
    public Timestamp getDateFin() {
        return DateFin;
    }

    /**
     * @param DateFin the DateFin to set
     */
    public void setDateFin(Timestamp DateFin) {
        this.DateFin = DateFin;
    }

    /**
     * @return the salle
     */
    public Salle getSalle() {
        return salle;
    }

    /**
     * @param salle the salle to set
     */
    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    /**
     * @return the film
     */
    public Film getFilm() {
        return film;
    }

    /**
     * @param film the film to set
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    
    /**
     * @return the vips
     */
    
    public ArrayList<ArrayList<Vip>> getVips() {
        return null; //vips;
    }

    /**
     * @param vips the vips to set
     */
    public void setVips(ArrayList<ArrayList<Vip>>vips) {
        //this.vips = vips;
    }

    /**
     * @return the seanceLendemain
     */
    public boolean getSeanceLendemain() {
        return seanceLendemain;
    }

    /**
     * @param seanceLendemain the seanceLendemain to set
     */
    public void setSeanceLendemain(boolean seanceLendemain) {
        this.seanceLendemain = seanceLendemain;
    }

    /**
     * @return the id_planning
     */
    public int getId_planning() {
        return id_planning;
    }

    /**
     * @param id_planning the id_planning to set
     */
    public void setId_planning(int id_planning) {
        this.id_planning = id_planning;
    }
}
