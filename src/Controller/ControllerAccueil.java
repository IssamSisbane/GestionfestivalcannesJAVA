/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Vue.VueAccueil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Issam
 */
public class ControllerAccueil {
    
    private static VueAccueil vueAccueil;
    
    // Constructeur
    public ControllerAccueil(){
        
        // On instancie la vue dont on a besoin
        vueAccueil = new VueAccueil();
        
        // On appelle la fonction qui va gere l'affichage de la vue
        afficherVueAccueil();
    }
    
    /**
     * Affiche la accueil et gere l'appui sur les boutons en renvoyant au bon controller
     */
    public static void afficherVueAccueil(){
        
        // Affiche la vue
        vueAccueil.setVisible(true);
        
        // Renvoi vers le controller film si le boutons films est cliqué
        vueAccueil.filmsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new ControllerFilm();
                
            }});
        
        // Renvoi vers le controller salle si le bouton salles est cliqué
        vueAccueil.sallesButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new ControllerSalle();
                
            }});
        
        // Renvoi vers le controller planning si le bouton plannings est cliqué
        vueAccueil.planningButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new ControllerPlanning();
                
            }});
    }
}
