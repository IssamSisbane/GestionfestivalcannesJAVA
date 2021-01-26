/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.Salle;
import Modele.SalleManager;
import Vue.VueAfficheSalle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Issam
 */
public class ControllerSalle {
    
    private static VueAfficheSalle vueAfficheSalle;
    private static SalleManager sm;
    
    // Constructeur
    public ControllerSalle(){
        
        // On crée les vues utiles
        vueAfficheSalle = new VueAfficheSalle();
        
        // On appelle la fonction d'affichage
        afficherVueSalle();
    }
    
    /**
     * Appelle la vue qui affiche toutes les salles
     */
    public static void afficherVueSalle(){
        
        // On affiche la vue
        vueAfficheSalle.setVisible(true);
        
        // On remplit la Jtable
        remplirTableSalles();
        
        // On recupère l'action de presser la touche entré sur le champs de recherche afin d'appliquer un filtre
        vueAfficheSalle.rechercheBarre.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent e) {
           int key = e.getKeyCode();
           if (key == KeyEvent.VK_ENTER) {
              remplirTableSalles();
              }
           }
         });
    }
    
    /**
     * Remplit la Jtable avec les salles recuperé en BDD
     */
    public static void remplirTableSalles(){
        
        // On crée le manager de salles
        sm = new SalleManager();
        
        // On recupère le texte dans le champ de recherche
        String filtre = vueAfficheSalle.rechercheBarre.getText();
        
        // On recupère toutes les salles correpondantes au filtre si il existe
        ArrayList<Salle> salles = sm.rechercheSalles(filtre);
        
        // On remplit la Jtable avec toutes les salles
        DefaultTableModel model = (DefaultTableModel) vueAfficheSalle.salleTable.getModel();
        model.setRowCount(0);
        
        if (!salles.isEmpty()){
            Object[] row = new Object[4];
            for(int i=0; i<salles.size();i++)
            {
                row[0] = salles.get(i).getId();
                row[1] = salles.get(i).getNom();
                row[2] = salles.get(i).getCapacite();

                model.addRow(row);
            }
        }
        else{
            // On affiche une erreur
            JOptionPane.showMessageDialog(vueAfficheSalle, "Aucun film trouvé","Erreur",JOptionPane.PLAIN_MESSAGE);
        }
        
    }
}
