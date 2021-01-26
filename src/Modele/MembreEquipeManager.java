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
 * Permet de gerer les interactions entre la base de données et l'application pour les objets MembreEquipe
 * @author Issam
 */
public class MembreEquipeManager {
    
    // la connexion à la BDD
    private static Connection c;
    
    // Constructeur
    public MembreEquipeManager(){
    }
    
    /**
     * Recherche et retourne tous les membresEquipe dans la base de données
     * @return membresEquipe
     */
    public static ArrayList<MembreEquipe> rechercheMembresEquipe()
        { 
            
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On crée une nouvel ArrayList
                ArrayList<MembreEquipe> membresEquipe = new ArrayList();
               
                // On crée un statement
                Statement statement = c.createStatement();
                
                // On écrit la requete
                String requete = "select * from membre_equipe";

                //recuperation du resultat de la requete
                ResultSet result = statement.executeQuery(requete);

                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    //creation d'un objet MembreEquipe
                    MembreEquipe me = new MembreEquipe(result.getInt(1), result.getString(2), result.getString(3),
                            result.getDate(4),result.getInt(5),result.getInt(6)); 
                    
                    // Ajout à l'ArrayList
                    membresEquipe.add(me);
                }
                // On ferme la connexion
                c.close();
                
                return membresEquipe;
            }
            catch(SQLException ex)
                {
                System.out.println(ex.getMessage());
                return null;
                }
        }
    
    /**
     * Recherche et retourne les membres de l'equipe du film entré en paramètre
     * @param id_film
     * @return membresEquipe
     */
    public ArrayList<MembreEquipe> rechercheMembreEquipeSpecifique(int id_film)
        { 
            
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On crée une nouvel ArrayList
                ArrayList<MembreEquipe> membresEquipe = new ArrayList();
                
                // On crée un statement
                Statement statement = c.createStatement();
                
                // On écrit la requete
                String requete = "SELECT * from membre_equipe JOIN equipe on equipe.id = membre_equipe.id_equipe JOIN film on film.id = equipe.id_film where film.id = "+id_film;

                //recuperation du resultat d'une requete
                ResultSet result = statement.executeQuery(requete);

                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    //creation d'un objet MembreEquipe
                    MembreEquipe me = new MembreEquipe(result.getInt(1), result.getString(2), result.getString(3),
                            result.getDate(4),result.getInt(5),result.getInt(6)); 
                    
                    // Ajout à l'ArrayList
                    membresEquipe.add(me);
                }
                // On ferme la connexion
                c.close();
                
                return membresEquipe;
            }
            catch(SQLException ex)
            {
               System.out.println(ex.getMessage());
               return null;
            }
        }
    
    /**
     * Recherche et retourne les membres de l'equipe qui assiste à la projection entré en parametre
     * @param id_projection
     * @return membresEquipe
     */
    public ArrayList<MembreEquipe> rechercheMembreEquipeByProjection(int id_projection)
        { 
            
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On crée une nouvel ArrayList
                ArrayList<MembreEquipe> membresEquipe = new ArrayList();
                
                // On crée un statement
                Statement statement = c.createStatement();
                
                // On écrit la requete
                String requete = "SELECT * FROM membre_equipe JOIN projection_vip ON membre_equipe.id = projection_vip.id_vip "
                        + "where projection_vip.type_vip = 'MembreEquipe' and projection_vip.id_projection = "+id_projection;

                //recuperation du resultat d'une requete
                ResultSet result = statement.executeQuery(requete);

                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    //creation d'un objet MembreEquipe
                    MembreEquipe me = new MembreEquipe(result.getInt(1), result.getString(2), result.getString(3),
                            result.getDate(4),result.getInt(5),result.getInt(6)); 
                    
                    // Ajout à l'ArrayList
                    membresEquipe.add(me);
                }
                // On ferme la connexion
                c.close();
                
                return membresEquipe;
            }
            catch(SQLException ex)
            {
               System.out.println(ex.getMessage());
               return null;
            }
        }
}
