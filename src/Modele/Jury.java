/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author Issam
 */
public class Jury {
    
    private int id;
    private int nombre_membre;
    private int id_competition;
    
    public Jury(int id, int nombre_membre, int id_competition)
    {
        this.id = id;
        this.nombre_membre = nombre_membre;
        this.id_competition = id_competition;
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
     * @return the nombre_membre
     */
    public int getNombre_membre() {
        return nombre_membre;
    }

    /**
     * @param nombre_membre the nombre_membre to set
     */
    public void setNombre_membre(int nombre_membre) {
        this.nombre_membre = nombre_membre;
    }

    /**
     * @return the id_competition
     */
    public int getId_competition() {
        return id_competition;
    }

    /**
     * @param id_competition the id_competition to set
     */
    public void setId_competition(int id_competition) {
        this.id_competition = id_competition;
    }
}
