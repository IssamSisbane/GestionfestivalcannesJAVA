/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import java.sql.Date;
/**
 *
 * @author Issam
 */
public class Film {
    
    private int id;
    private String titre;
    private Date annee;
    private Competition competition;
    
    public Film(int id, String titre, Date annee, Competition competition)
    {
        this.id = id;
        this.titre = titre;
        this.annee = annee;
        this.competition = competition;
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
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * @return the annee
     */
    public Date getAnnee() {
        return annee;
    }

    /**
     * @param annee the annee to set
     */
    public void setAnnee(Date annee) {
        this.annee = annee;
    }
    
    public String toString(){
        return this.getTitre();
    }

    /**
     * @return the competition
     */
    public Competition getCompetition() {
        return competition;
    }

    /**
     * @param competition the competition to set
     */
    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
