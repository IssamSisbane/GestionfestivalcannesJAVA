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
import javax.sql.DataSource;

/**
 * Permet de gerer les interactions entre la base de données et l'application pour les objets Films
 * @author Issam
 */
public class FilmManager {
    
    // la connexion à la BDD
    private Connection c;
     
        public FilmManager()
        {
            
        }
        
        /**
         * Recherche et retourne tous les films de la BDD
         * @return ArrayList<Film> films
         */
        public ArrayList<Film> recupererFilms(String filtre)
        { 
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On crée une nouvel ArrayList
                ArrayList<Film> films = new ArrayList<Film>();
                
                // On crée un statement
                Statement statement = c.createStatement();
                Statement statement2 = c.createStatement();
               
                
                // On écrit la requete
                String requete = "SELECT * from film WHERE titre LIKE '%"+filtre+"%'";
                

                //recuperation du resultat d'une requete
                ResultSet result = statement.executeQuery(requete);
                
                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    // On recupère la competition associé au film
                    CompetitionManager cm = new CompetitionManager();
                    Competition competition = cm.recupererCompetitionParId(result.getInt(4));
                    
                    //creation d'un objet Film
                    Film g = new Film(result.getInt(1), result.getString(2), result.getDate(3),competition);           
                    
                    // Ajout à l'ArrayList
                    films.add(g);
                }
                
                result.close();
                statement.close();
                
                // On ferme la connexion
                c.close();
                
                return films;
            }
            catch(Exception ex)
                {
                    System.out.println(ex.getMessage());
                    return null;
                }
        }
        
        /**
         * Recherche et retourne le film correspondant à l'id en paramètre
         * @param film_id
         * @return 
         */
        public Film recupereFilmParId(int film_id)
        { 
            
            try{
                
                // la connexion à la BDD
                c = ConnexionBD.connect();
               
                // On crée un statement
                Statement statement = c.createStatement();
                
                // On écrit la requete
                String requete = "select * from film where id = "+film_id;

                //recuperation du resultat d'une requete
                ResultSet result = statement.executeQuery(requete);

                //tant qu'il y a une ligne resultat
                if(result.next())
                {
                    // On recupère la competition associé au film
                    CompetitionManager cm = new CompetitionManager();
                    Competition competition = cm.recupererCompetitionParId(result.getInt(4));
                    
                    //creation d'un objet VIP
                    Film film = new Film(result.getInt(1), result.getString(2), result.getDate(3),competition);
                    
                    // On ferme la connexion
                    c.close();
                    
                    return film;
                    
                }
                else{
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
