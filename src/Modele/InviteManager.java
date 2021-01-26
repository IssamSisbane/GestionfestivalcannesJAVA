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
 * Permet de gerer les interactions entre la base de données et l'application pour les objets Invite
 * @author Issam
 */
public class InviteManager {
    
    // la connexion à la BDD
    private Connection c;
    
    // Constructeur
    public InviteManager(){

    }
    
    /**
     * Recherche et retourne tous les invites de la BDD
     * @param filtre 
     * @return invites
     */
    public ArrayList<Invite> rechercheInvites(String filtre)
        { 
            try{
                // la connexion à la BDD
            	c = ConnexionBD.connect();
                
                // On crée une nouvel ArrayList
                ArrayList<Invite> invites = new ArrayList();
            	
                // On crée un statement
                String requete = "select * from invite WHERE nom LIKE '%"+filtre+"%' or prenom LIKE '%"+filtre+"%'";
                
                // On écrit la requete
                Statement statement = c.createStatement();
                
                
                //recuperation du resultat de la requete
                ResultSet result = statement.executeQuery(requete);

                
                
                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    //creation d'un objet Invite
                    Invite i = new Invite(result.getInt(1), result.getString(2), result.getString(3),
                            result.getDate(4),result.getInt(5));  
                    
                    // Ajout à l'ArrayList
                    invites.add(i);
                }
                // On ferme la connexion
                c.close();
                
                return invites;
            }
            catch(SQLException ex)
            {
                System.out.println(ex.getMessage());
                return null;
            }
           
        }
    
    public ArrayList<Invite> rechercheInvitesByProjection(int id_projection)
        { 
            try{
                // la connexion à la BDD
            	c = ConnexionBD.connect();
                
                // On crée une nouvel ArrayList
                ArrayList<Invite> invites = new ArrayList();
            	
                // On crée un statement
                String requete = "SELECT * FROM invite JOIN projection_vip ON invite.id = projection_vip.id_vip "
                        + "where projection_vip.type_vip = 'Invite' and projection_vip.id_projection = "+id_projection;
                
                // On écrit la requete
                Statement statement = c.createStatement();
                
                
                //recuperation du resultat de la requete
                ResultSet result = statement.executeQuery(requete);

                
                
                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    //creation d'un objet Invite
                    Invite i = new Invite(result.getInt(1), result.getString(2), result.getString(3),
                            result.getDate(4),result.getInt(5));  
                    
                    // Ajout à l'ArrayList
                    invites.add(i);
                }
                // On ferme la connexion
                c.close();
                
                return invites;
            }
                catch(SQLException ex)
            {
                System.out.println(ex.getMessage());
                return null;
            }
           
        }
}
