/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Permet de gerer les interactions entre la base de données et l'application pour les objets Planning
 * @author Issam
 */
public class PlanningManager {
     // la connexion à la BDD
    private static Connection c;
    
    
    public PlanningManager(){
        
    }
    
    /**
         * Recherche et renvoi toutes les plannings présents en BDD
         * @return plannings
         */
        public ArrayList<Planning> recherchePlannings()
        { 
            
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
               
                // On crée une nouvel ArrayList
                ArrayList<Planning> plannings = new ArrayList();
                
                // On crée un statement
                Statement statement = c.createStatement();
                
                // On écrit la requete
                String requete = "select * from planning";

                //recuperation du resultat de la requete
                ResultSet result = statement.executeQuery(requete);
                
                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    //creation d'un objet Planning
                    Planning planning = new Planning(result.getInt(1),result.getInt(2),result.getString(3));
                    
                    // Ajout à l'ArrayList
                    plannings.add(planning);
                }
                
                result.close();
                // On ferme la connexion
                c.close();
                
                return plannings;
            }
            catch(SQLException ex)
                {
                System.out.println(ex.getMessage());
                return null;
                }
        }
        
        public boolean ajouterPlanning(Planning planning)
        {
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On écrit la requete
                String requete = "INSERT INTO `planning`(`id`, `année`, `description`) VALUES (?,?,?)";
                
                // On crée un preparedStatement
                PreparedStatement ps = c.prepareStatement(requete);
                
                // On injecte les données dans le preparedStatement
                ps.setInt(1,planning.getId());
                ps.setInt(2,planning.getAnnee());
                ps.setString(3,planning.getDescription());
                
                // On recupère le resultat
                int resultat = ps.executeUpdate();
                
                // On verifie si l'operation c'est bien passée
                if(resultat == 1){
                    // On ferme la connexion
                    c.close();
                    
                    return true;
                }
                else{
                    // On ferme la connexion
                    c.close();
                     
                    return false;  
                }
                    
            }
            catch(SQLException ex)
            {
               System.out.println(ex.getMessage());
               return false;
            }
        }
        
        /**
         * Supprime le film correspondant à l'id entré en paramètre
         * @param planning_id
         * @return boolean
         */
        public boolean supprimePlanning(int planning_id){
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On crée un premier statement
                Statement st = c.createStatement();
                
                // On crée un deuxieme statement
                Statement st2 = c.createStatement();
                
                // On écrit les requetes
                String requete = "DELETE from planning where id = "+planning_id;
                String requete3 = "DELETE from planning where id = (select id from projection where id_planning = )"+planning_id;
                String requete2 = "DELETE from projection where id_planning = "+planning_id;
                
                //recuperation du resultat de la requete2
                int result2 = st2.executeUpdate(requete2);
                
                //recuperation du resultat de la requete
                int result = st.executeUpdate(requete);
                
                // On verifie si l'operation c'est bien passée
                if(result == 1){
                    // On ferme la connexion
                    c.close();
                    
                    return true;
                }
                else{
                    // On ferme la connexion
                     c.close();
                     
                    return false;  
                }
                
            }catch(SQLException ex)
            {
               System.out.println(ex.getMessage());
               return false;
            }
            
        }
}
