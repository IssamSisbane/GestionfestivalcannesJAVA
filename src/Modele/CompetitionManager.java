/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Issam
 */
public class CompetitionManager {
    
    // la connexion à la BDD
    private Connection c;
    
    public CompetitionManager(){
        
    }
    
    public Competition recupererCompetitionParId(int id_competition){
        try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On crée un statement
                Statement statement = c.createStatement();
               
                
                // On écrit la requete
                String requete = "select * from competition where id = "+id_competition;
                

                //recuperation du resultat d'une requete
                ResultSet result = statement.executeQuery(requete);
                
                //tant qu'il y a une ligne resultat
                if(result.next())
                {
                    //creation d'un objet Film
                    Competition competition = new Competition(result.getInt(1), result.getString(2));           
                    
                    // On ferme la connexion
                    c.close();
                    
                    return competition;
                }
                else{
                    // On ferme la connexion
                    c.close();
                    
                    return null;

                }
  
            }
            catch(Exception ex)
                {
                System.out.println(ex.getMessage());
                return null;
                }
    }
}
