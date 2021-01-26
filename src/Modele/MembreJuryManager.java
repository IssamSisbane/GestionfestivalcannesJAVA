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
import java.util.ArrayList;

/**
 * Permet de gerer les interactions entre la base de données et l'application pour les objets MembreJury
 * @author Issam
 */
public class MembreJuryManager {
    
    // la connexion à la BDD
    private Connection c;
 
    // Constructeur
    public MembreJuryManager(){

    }
    
    /**
     * recherche et retourne tous les MembreJury trouvés en BDD
     * @return membresJury
     */
    public ArrayList<MembreJury> rechercheMembresJury()
        { 
            
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On crée une nouvel ArrayList
                ArrayList<MembreJury> membresJury = new ArrayList();
                
                // On crée un statement
                Statement statement = c.createStatement();
                
                // On écrit la requete
                String requete = "select * from membre_jury";

                //recuperation du resultat d'une requete
                ResultSet result = statement.executeQuery(requete);

                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    //creation d'un objet MembreJury
                    MembreJury mj = new MembreJury(result.getInt(1), result.getString(2), result.getString(3),
                            result.getDate(4),result.getInt(5),result.getInt(6));   
                    
                    // Ajout à l'ArrayList
                    membresJury.add(mj);
                }
                
                // On ferme la connexion
                c.close();
                return membresJury;
            }
            catch(SQLException ex)
                {
                System.out.println(ex.getMessage()); 
                return null;
                }
           
        }
        
        public ArrayList<MembreJury> rechercheMembresJurySpecifique(int id_film)
        { 
            
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On crée une nouvel ArrayList
                ArrayList<MembreJury> membresJury = new ArrayList();
                
                // On crée un statement
                Statement statement = c.createStatement();
                
                // On écrit la requete
                String requete = "SELECT * FROM `membre_jury` join jury ON jury.id = membre_jury.id_jury "
                        + "JOIN competition on competition.id = jury.id_competition "
                        + "JOIN film on film.idCompetition = competition.id where film.id = "+id_film;

                //recuperation du resultat d'une requete
                ResultSet result = statement.executeQuery(requete);

                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    //creation d'un objet MembreJury
                    MembreJury mj = new MembreJury(result.getInt(1), result.getString(2), result.getString(3),
                            result.getDate(4),result.getInt(5),result.getInt(6));  
                    
                    // Ajout à l'ArrayList
                    membresJury.add(mj);
                }
                // On ferme la connexion
                c.close();
                
                return membresJury;
            }
            catch(SQLException ex)
                {
                System.out.println(ex.getMessage());
                return null;
                }
        }
        
        public ArrayList<MembreJury> rechercheMembresJuryByProjection(int id_projection)
        { 
            
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On crée une nouvel ArrayList
                ArrayList<MembreJury> membresJury = new ArrayList();
                
                // On crée un statement
                Statement statement = c.createStatement();
                
                // On écrit la requete
                String requete = "SELECT * FROM membre_jury JOIN projection_vip ON membre_jury.id = projection_vip.id_vip "
                        + "where projection_vip.type_vip = 'MembreJury' and projection_vip.id_projection = "+id_projection;

                //recuperation du resultat d'une requete
                ResultSet result = statement.executeQuery(requete);

                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    //creation d'un objet MembreJury
                    MembreJury mj = new MembreJury(result.getInt(1), result.getString(2), result.getString(3),
                            result.getDate(4),result.getInt(5),result.getInt(6));  
                    
                    // Ajout à l'ArrayList
                    membresJury.add(mj);
                }
                // On ferme la connexion
                c.close();
                
                return membresJury;
            }
            catch(SQLException ex)
                {
                System.out.println(ex.getMessage());
                return null;
                }
        }
}
