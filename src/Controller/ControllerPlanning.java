/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Modele.Planning;
import Modele.PlanningManager;
import Vue.VueAffichePlanning;
import Vue.VueCreerPlanning;
import Vue.VueGestionPlanning;
import Vue.VueProjectionAffiche;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Issam
 */
public class ControllerPlanning {
    
    private static VueProjectionAffiche vueAfficheProjection;
    private static VueGestionPlanning vueGestionPlanning;
    private static VueCreerPlanning vueCreerPlanning;
    private static VueAffichePlanning vueAffichePlanning;
    private static PlanningManager pm;
    
    // Constructeur
    public ControllerPlanning()
    {
        // On instancie les vues dont on a besoin
        vueAfficheProjection = new VueProjectionAffiche();
        vueGestionPlanning = new VueGestionPlanning();
        vueCreerPlanning = new VueCreerPlanning();
        
        // On appelle la fonction qui va gere l'affichage de la vue
        AfficheMenuGestionPlanning();
    }
    
    /**
     * Affiche la vue de gestion de planning
     */
    public static void AfficheMenuGestionPlanning()
    {
        // Remplit la JTable planning
        remplirTablePlannings();
        
        // On affiche la fenetre
        vueGestionPlanning.setVisible(true);  
        
        // On recupère l'action de double click sur une ligne et on renvoi au planning associé à la ligne
        vueGestionPlanning.planningTable.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent me) {
             
            // On detecte les doubles clicks de souris
            if (me.getClickCount() == 2) {     
                
                // On recupère la cible de la souris
               JTable target = (JTable)me.getSource(); 
               
               // on recupère la ligne selectionné
               int row = target.getSelectedRow(); 
               
               // On recupère l'id de la ligne cliqué
               int id_planning = (int) vueGestionPlanning.planningTable.getValueAt(row, 0);
               
               System.out.print(id_planning);
               // On affiche le planning selectionné
               new ControllerProjection(id_planning);
               
               /*
               vueAffichePlanning = new VueAffichePlanning(id_planning);
               vueAffichePlanning.setVisible(true);
               */
                }
            }});
        
        // On recupère l'action d'appuyer sur le bouton ajouter
        vueGestionPlanning.ajouterBouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                // On appelle d'affichage de la fenetre d'ajout de planning
                afficherAjoutPlanning();
                
            }});
        
        // On recupère l'action d'appuyer sur le bouton ajouter
        vueGestionPlanning.supprimerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                // on recupère la ligne selectionné
               int row = vueGestionPlanning.planningTable.getSelectedRow(); 
               
               // On recupère l'id de la ligne cliqué
               int id_planning = (int) vueGestionPlanning.planningTable.getValueAt(row, 0);
               
               // On demande à l'utilisateur de confirmer son choix
               int retour = JOptionPane.showConfirmDialog(vueGestionPlanning, "Voulez vous vraiment supprimer ce planning ?", "Avertissement", JOptionPane.YES_NO_OPTION);
               
               // Si le bouton cliqué est oui on suppromer le planning
               if (retour == 0){
                    pm = new PlanningManager();
                    pm.supprimePlanning(id_planning);
                    
                    remplirTablePlannings();
               }
                
            }});
        
    }
    
    /**
     * Rempli la Jtable projections
     */
    public static void remplirTablePlannings()
    {
        // On crée le manager de plannings
        pm = new PlanningManager();
        
        // On recupère tous les plannings en BDD
        ArrayList<Planning> plannings = pm.recherchePlannings();
        
        // On remplit la Jtable avec tous les plannings
        DefaultTableModel model = (DefaultTableModel) vueGestionPlanning.planningTable.getModel();
        model.setRowCount(0);
        
        if(!plannings.isEmpty()){
            Object[] row = new Object[6];
            for(int i=0; i<plannings.size();i++)
            {
                row[0] = plannings.get(i).getId();
                row[1] = plannings.get(i).getAnnee();
                row[2] = plannings.get(i).getDescription();
                model.addRow(row);
            }
        }
        else
        {
            // On affiche une erreur
            JOptionPane.showMessageDialog(vueGestionPlanning, "Aucun film trouvé","Erreur",JOptionPane.PLAIN_MESSAGE);
        }
        
    }
    
    /**
     * Appelle la vue d'ajout de planning
     */
    public static void afficherAjoutPlanning(){
        
        // On affiche la vue
        vueCreerPlanning.setVisible(true);
        
        // On recupère l'action d'appuyer sur le bouton ajouter
        vueCreerPlanning.creerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                // On appelle la fonction d'ajout de planning
                ajoutPlanning();
                
            }
        });
    }
    
    /**
     * Crée le planning avec les valeurs entré et l'ajoute en BDD
     */
    private static void ajoutPlanning(){
        
        // On recupère les informations du futur planning
        int id = Integer.parseInt(vueCreerPlanning.idText.getText());

        int annee = vueCreerPlanning.YearChooser.getYear();

        String description = vueCreerPlanning.descriptionText.getText();

        // On crée le planning
        Planning planning = new Planning(id,annee,description);

        // On ajoute la planning en BDD
        pm.ajouterPlanning(planning);

        // On met a jour la Jtable planning
        remplirTablePlannings();

        // On ferme la fenetre d'ajout
        vueCreerPlanning.dispose();
    }
}
