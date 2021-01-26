/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Permet de gerer les interactions entre la base de données et l'application pour les objets Utilisateur
 * @author Issam
 */
public class UtilisateurManager {
    
    // la connexion à la BDD
    private Connection c;
 
    // Constructeur
    public UtilisateurManager(){
    }
    
    /**
     * Verifie si l'utilisateur en paramètre appartient à la BDD et si le mdp match
     * @param utilisateur
     * @return 
     */
    public Boolean verifierUtilisateur(Utilisateur utilisateur)
    {
            try{
                // On recupère la connexion
                c = ConnexionBD.connect();
                
                // On crée un statement
                Statement statement = c.createStatement();
                
                // On écrit la requete
                String requete = "select * from utilisateur where type_utilisateur_id = 3 and pseudo = '"+utilisateur.getPseudo()+"' and motDePasse = '"+utilisateur.getMotDePasse()+"';";
                
                //recuperation du resultat de la requete requete
                ResultSet result = statement.executeQuery(requete);
                boolean connexion = result.next();

                
                // On ferme la connexion
                c.close();
                
                return connexion;
            }
            catch(SQLException ex)
                {
                    System.out.println(ex.getMessage());
                    return null;
                }
    }
    
}
