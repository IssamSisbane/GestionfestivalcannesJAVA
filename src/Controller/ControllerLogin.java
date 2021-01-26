/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modele.Utilisateur;
import Modele.UtilisateurManager;
import Vue.VueLogin;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Issam
 */
public class ControllerLogin {
    
    static UtilisateurManager um;
    static VueLogin vue;
    
    // Constructeur
    public ControllerLogin()
    {
        // On instancie la vue dont on a besoin
        vue = new VueLogin();
        
        // On appelle la fonction qui va gere l'affichage de la vue
        afficher();

    }
    
    /**
     * Affiche la vue et gere les boutons et leur actions
     */
    public static void afficher()
    {
        // On affiche la vue de connexion
        vue.setVisible(true);
        
        
        // Action lorsque le bouton est appuyé
        vue.loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                verifierUtilisateur();
                
            }});
    }
    
    /**
     * Verifie si l'utilisateur existe dans la BDD et si le mot de passe est correct
     */
    public static void verifierUtilisateur()
    {
        try{
                // On cree le manager d'uitlisateur
                um = new UtilisateurManager();
                
                // On recupère le pseudo et le mot de passe entrés
                String pseudo = vue.pseudoText.getText();
                String MotDePasse = new  String(vue.passwordText.getPassword());
                
                // On crée un objet utilisateur avec les informations entrées
                Utilisateur utilisateur = new Utilisateur(pseudo, MotDePasse);
                
                // On verifie si l'utilisateur existe et si le mot de passe est correct
                if(um.verifierUtilisateur(utilisateur))
                {
                    // On appelle le controller de la fenetre principale de l'application
                    new ControllerAccueil();
                    
                    // On ferme la fenetre de login
                    vue.dispose();
                }
                else
                {
                    // On affiche une erreur
                    JOptionPane.showMessageDialog(vue, "Le nom d'utilisateur ou le mot de passe n'est pas correct","Erreur",JOptionPane.PLAIN_MESSAGE);
                }
                    
            }
            catch(HeadlessException z)
            {
                System.out.print(z);
            }
    }
}
