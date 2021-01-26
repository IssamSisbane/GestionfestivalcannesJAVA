/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.Film;
import Modele.FilmManager;
import Vue.VueAfficheFilm;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Issam
 */
public class ControllerFilm {
    
    private static VueAfficheFilm vueAfficheFilm;
    private static FilmManager fm;
    
    // Constructeur
    public ControllerFilm(){
        
        // On instancie la vue dont on a besoin
        vueAfficheFilm = new VueAfficheFilm();
        
        // On appelle la fonction qui va gere l'affichage de la vue
        afficherVueFilm();
        
    }
    
    /**
     * Appelle la fenetre qui affiche la liste des films
     */
    public static void afficherVueFilm(){
        
        // On affiche la vue
        vueAfficheFilm.setVisible(true);
        
        // On rempli la table
        remplirTableFilms();
        
        // On recupère l'action de presser la touche entré sur le champs de recherche afin d'appliquer un filtre
        vueAfficheFilm.rechercherBarre.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent e) {
           int key = e.getKeyCode();
           if (key == KeyEvent.VK_ENTER) {
              remplirTableFilms();
              }
           }
         });
      }

    
    /**
     * Rempli la Jtable films
     */
    public static void remplirTableFilms()
    {
        // On crée le manager de films
        fm = new FilmManager();
        
        // On recupère le texte dans le champ de recherche
        String filtre = vueAfficheFilm.rechercherBarre.getText();
        
        // On recupère toutes les films correpondants au filtre si il existe
        ArrayList<Film> films = fm.recupererFilms(filtre);
        
        DefaultTableModel model = (DefaultTableModel) vueAfficheFilm.filmTable.getModel();
        model.setRowCount(0);
            
        if(!films.isEmpty())
        {
            // On remplit la Jtable avec toutes les films
            
            Object[] row = new Object[4];
            for(int i=0; i<films.size();i++)
            {
                row[0] = films.get(i).getId();
                row[1] = films.get(i).getTitre();
                row[2] = films.get(i).getAnnee();
                row[3] = films.get(i).getCompetition();

                model.addRow(row);
            } 
        }
        else
        {
            // On affiche une erreur
            JOptionPane.showMessageDialog(vueAfficheFilm, "Aucun film trouvé","Erreur",JOptionPane.PLAIN_MESSAGE);
        }
        
    }
    
}
