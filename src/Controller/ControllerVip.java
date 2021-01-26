/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.Invite;
import Modele.InviteManager;
import Modele.MembreEquipe;
import Modele.MembreEquipeManager;
import Modele.MembreJury;
import Modele.MembreJuryManager;
import Modele.Salle;
import Vue.VueAfficherVips;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Issam
 */
public class ControllerVip {
    
    private static int id_projection;
    public static int nombreInvites;
    private static VueAfficherVips vueAfficherVips;
    private static MembreEquipeManager mem;
    private static MembreJuryManager mej;
    private static InviteManager im;
    private static Salle salle;
    
    // Constructeur
    public ControllerVip(int id, Salle Lasalle)
    {
        // on recupère les variables utiles
        id_projection = id;
        salle = Lasalle;
        
        // On crée les vues utiles
        vueAfficherVips = new VueAfficherVips();
        
        // On appelle la fonction d'affichage
        afficherVipProjection();
    }
    
    /**
     * Recupère les vips et appelle la vue qui va afficher les vips
     */
    public static void afficherVipProjection(){
        
        // On affiche la vue
        vueAfficherVips.setVisible(true);
        
        // On affiche les membres de l'equipe du film et on recupère le nombre de vips
        int nombreMembresEquipe = afficherMembresEquipe();
        
        // On affiche les membres du jury de la projection et on recupère le nombre de vips
        int nombreMembresJury = afficherMembresJury();
        
        // On affiche les invites de la proejction et on recupère le nombre de vips
        nombreInvites = afficherInvites();
        
        vueAfficherVips.ajouterInvite.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    ControllerProjection.afficherAjouterInviteProjection(id_projection);     
            }
        });
        
        // On recupère et on affiche le nombre de places occupés actuellement dans la salle
        int places = nombreMembresEquipe + nombreMembresJury + nombreInvites;
        String placesOccupes = String.valueOf(places);
        vueAfficherVips.placesOccupes.setText(placesOccupes);
        
        // On recupère le nombre de places restantes dans la salle
        places = salle.getCapacite() - places;
        String placesRestantes = String.valueOf(places);
        vueAfficherVips.placesRestantes.setText(placesRestantes);
    }
    
    /**
     * Recupère les membre de l'equipe correpondant à la projection et les affiche dans la JTable
     * @return int le nombre de membre de l'equipe de film
     */
    public static int afficherMembresEquipe(){
        
        // On recupère les membre de l'equipe du film projeté
        mem = new MembreEquipeManager();
        ArrayList<MembreEquipe> membresEquipe = mem.rechercheMembreEquipeByProjection(id_projection);

        // On remplit la Jtable avec toutes les membres de l'equipe du film
        DefaultTableModel modelEquipe = (DefaultTableModel) vueAfficherVips.membreEquipeTable.getModel();
        modelEquipe.setRowCount(0);
        
        if (!membresEquipe.isEmpty()){
            
            Object[] rowEquipe = new Object[3];
            for(int i=0; i<membresEquipe.size();i++)
            {
                rowEquipe[0] = membresEquipe.get(i).getId();
                rowEquipe[1] = membresEquipe.get(i).getNom();
                rowEquipe[2] = membresEquipe.get(i).getPrenom();

                modelEquipe.addRow(rowEquipe);
            }
            
        }else{
            System.out.print("pas de données");
        }
        
        // On affiche le nombre de membre de l'equipe de film recuperé
        vueAfficherVips.nombreEquipe.setText(String.valueOf(membresEquipe.size()));
        
        return membresEquipe.size();
    }
    
    /**
     * Recupère les membre du jury correpondant à la projection et les affiche dans la JTable
     * @return int le nombre de membre du jury de la projection
     */
    public static int afficherMembresJury(){
        
        // On recupère les membre du jury correspondant à la projection
        mej = new MembreJuryManager();
        ArrayList<MembreJury> membresJury = mej.rechercheMembresJuryByProjection(id_projection);
        
        // On remplit la Jtable avec toutes les vips
        DefaultTableModel modelJury = (DefaultTableModel) vueAfficherVips.membreJuryTable.getModel();
        modelJury.setRowCount(0);
        
        if (!membresJury.isEmpty()){
            
            Object[] rowJury = new Object[3];
            for(int i=0; i<membresJury.size();i++)
            {
                rowJury[0] = membresJury.get(i).getId();
                rowJury[1] = membresJury.get(i).getNom();
                rowJury[2] = membresJury.get(i).getPrenom();

                modelJury.addRow(rowJury);
            }
            
        }else{
            System.out.print("pas de données");
        }
        
        // On affiche le nombre de membre du jury recuperé
        vueAfficherVips.nombreJury.setText(String.valueOf(membresJury.size()));
        
        return membresJury.size();
    }
    
    /**
     * Recupère les invites correpondant à la projection et les affiche dans la JTable
     * @return int le nombre d'invite qui assite à la projection
     */
    public static int afficherInvites(){
        
        // On recupère le nombre d'invite assistant à la projection
        im = new InviteManager();
        ArrayList<Invite> invites = im.rechercheInvitesByProjection(id_projection);    
        
        
        // On remplit la Jtable avec toutes les vips
        DefaultTableModel modelInvite = (DefaultTableModel) vueAfficherVips.inviteTable.getModel();
        modelInvite.setRowCount(0);
        
        if (!invites.isEmpty()){
            
            Object[] rowInvite = new Object[3];
            for(int i=0; i<invites.size();i++)
            {
                rowInvite[0] = invites.get(i).getId();
                rowInvite[1] = invites.get(i).getNom();
                rowInvite[2] = invites.get(i).getPrenom();

                modelInvite.addRow(rowInvite);
            }
            
        }else{
            System.out.print("pas de données");
        }
        
        // On affiche le nombre d'invités recuperé
        vueAfficherVips.nombreInvite.setText(String.valueOf(invites.size()));
        
        return invites.size();
    }
}
