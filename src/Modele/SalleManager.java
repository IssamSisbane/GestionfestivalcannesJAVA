/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection; 
import java.sql.SQLException;

/**
 * Permet de gerer les interactions entre la base de données et l'application pour les objets Salle
 * @author Issam
 */
public class SalleManager {
    
    //la connexion à la BDD
    private static Connection c;
    
        // Constructeur
        public SalleManager()
        { 
        }
        
        /**
         * Recherche et renvoi tous les salles présentes dans la bdd
         * @param filtre
         * @return salles
         */
        public ArrayList<Salle> rechercheSalles(String filtre)
        { 
     
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On crée une nouvel ArrayList
                ArrayList<Salle> salles = new ArrayList();
               
                // On crée un statement
                Statement statement = c.createStatement();
                
                // On écrit la requete
                String requete = "select * from salle WHERE nom LIKE '%"+filtre+"%'";

                //recuperation du resultat de la requete
                ResultSet result = statement.executeQuery(requete);
                
                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    //creation d'un objet Salle
                    Salle g = new Salle(result.getInt(1), result.getString(2), result.getInt(3));           
                    
                    // Ajout à l'ArrayList
                    salles.add(g);
                }
                
                statement.close();
                result.close();
                // On ferme la connexion
                c.close();
                return salles;
            }
            catch(SQLException ex)
                {
                    System.out.println(ex.getMessage());
                    return null;
                }
        }
        
        /**
         * Recherche et renvoi la salle correspondant à l'id en paramètre en BDD
         * @param id
         * @return Salle salle
         */
        public Salle recupereSalleParId(int id)
        { 
            try{
                // On recupère la connexion
                c = ConnexionBD.connect();
                
                // On crée un statement
                Statement statement = c.createStatement();
                
                // On écrit la requete
                String requete = "select * from salle where id = "+id;

                //recuperation du resultat de la requete requete
                ResultSet result = statement.executeQuery(requete);

                //tant qu'il y a une ligne resultat
                if(result.next())
                {
                    //creation d'un objet Salle
                    Salle salle = new Salle(result.getInt(1), result.getString(2), result.getInt(3));   
                    
                    // On ferme la connexion
                    c.close();
                    
                    return salle;     
                }
                else{
                    
                    // On ferme la connexion
                    c.close();
                    
                    // On n'a pas trouvé de Salle avec cette id
                    return null;
                }
            }
            catch(SQLException ex)
                {
                    System.out.println(ex.getMessage());
                    return null;
                }
        }
}
