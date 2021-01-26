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
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Permet de gerer les interactions entre la base de données et l'application pour les objets Projection
 * @author Issam
 */
public class ProjectionManager {
    
    // la connexion à la BDD
    private static Connection c;
    
        // Constructeur
        public ProjectionManager()
        {   
        }
        
        /**
         * Recherche et renvoi toutes les proejctions présentes en BDD
         * @return projections
         */
        public ArrayList<Projection> rechercheProjections(int id_planning)
        { 
            
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On crée une nouvel ArrayList
                ArrayList<Projection> projections = new ArrayList();
                
                // On crée un statement
                Statement statement = c.createStatement();
                
                // On écrit la requete
                String requete = "SELECT * FROM `projection` WHERE projection.id_planning = "+id_planning;

                //recuperation du resultat de la requete
                ResultSet result = statement.executeQuery(requete);
                
                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    //On recupère les resultats de la bdd et on cherche la salle et le film
                    
                    int id = result.getInt(1);
                    Timestamp DateDebut = result.getTimestamp(2);
                    Timestamp DateFin = result.getTimestamp(3);
                    int id_Salle = result.getInt(4);
                    int id_film = result.getInt(5);
                    boolean seanceLendemain = result.getBoolean(6);
                    id_planning = result.getInt(7);
                    
                    // On recupère la salle correspondant à l'id
                    SalleManager sm = new SalleManager();
                    Salle salle = sm.recupereSalleParId(id_Salle);
                    
                    // On recupère le film correspondant à l'id
                    FilmManager fm = new FilmManager();
                    Film film = fm.recupereFilmParId(id_film);
                    
                    // On crée l'objet projection
                    Projection g = new Projection(id,DateDebut,DateFin,salle,film,seanceLendemain,id_planning);     //vips      
                    
                    // Ajout à l'ArrayList
                    projections.add(g);
                }
                
                // On ferme la connexion
                c.close();
                System.out.print("p"+projections.size());
                return projections;
            }
            catch(SQLException ex)
                {
                System.out.println(ex.getMessage());
                return null;
                }
        }
        
        /**
         * Recupère et renvoi les membres du jury correspondant à l'id entré en paramètre dans la bdd
         * @param id_projection
         * @return membresJury
         */
        public ArrayList<MembreJury> recupereMembresJuryProjection(int id_projection)
        {
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On crée une nouvel ArrayList
                ArrayList<MembreJury> membresJury = new ArrayList();
                
                // On crée un statement
                Statement statement = c.createStatement();
                
                // On écrit la requete
                String requete = "select * from membre_jury join projection_vip on membre_jury.id = projection_vip.vip_id where type_vip = MembreJury and projection_vip.id ="+id_projection;

                //recuperation du resultat de la requete
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
        
        /**
         * Recupère et renvoi les invites correspondant à l'id entré en paramètre dans la bdd
         * @param id_projection
         * @return 
         */
        public ArrayList<Vip> recupereInvitesProjection(int id_projection)
        {
            
            try{
                c = ConnexionBD.connect();
                
                ArrayList<Vip> invites = new ArrayList();
               
                Statement statement = c.createStatement();
                String requete = "select * from invite join projection_vip on invite.id = projection_vip.vip_id where type_vip = Invite and projection_vip.id ="+id_projection;

                //recuperation du resultat d'une requete
                ResultSet result = statement.executeQuery(requete);

                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    //creation d'un objet VIP
                    Invite i = new Invite(result.getInt(1), result.getString(2), result.getString(3),
                            result.getDate(4),result.getInt(5));           
                    invites.add(i);
                }
                c.close();
                return invites;
            }
            catch(SQLException ex)
                {
                    System.out.println(ex.getMessage());
                    return null;
                }
            

        }
        
        public ArrayList<Vip> recupereMembresEquipeProjection(int id_projection)
        {
            
            try{
                c = ConnexionBD.connect();
                
                ArrayList<Vip> membreEquipes = new ArrayList();
                
                Statement statement = c.createStatement();
                String requete = "select * from membre_equipe join projection_vip on membre_equipe.id = projection_vip.vip_id where type_vip = MembreEquipe and projection_vip.id ="+id_projection;

                //recuperation du resultat d'une requete
                ResultSet result = statement.executeQuery(requete);

                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    //creation d'un objet VIP
                    MembreEquipe me = new MembreEquipe(result.getInt(1), result.getString(2), result.getString(3),
                            result.getDate(4),result.getInt(5),result.getInt(6));           
                    membreEquipes.add(me);
                }
                c.close();
                return membreEquipes;
            }
            catch(SQLException ex)
                {
                System.out.println(ex.getMessage());
                return null;
                }

        }
        
        /**
         * Supprimer la projection de la BDD correpsondant à l'id entré en paramètre
         * @param projection_id
         * @return boolean
         */
        public boolean supprimeProjection(int projection_id){
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On crée un premier statement
                Statement st = c.createStatement();
                
                // On crée un deuxieme statement
                Statement st2 = c.createStatement();
                
                // On écrit les requetes
                String requete = "DELETE from projection where id = "+projection_id;
                String requete2 = "DELETE from projection_vip where id_projection = "+projection_id;
                
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
        
        /**
         * Ajouter en BDD la projection en paramètre
         * @param projection
         * @return boolean
         */
        public boolean ajouterProjection(Projection projection)
        {
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On écrit la requete
                String requete = "INSERT INTO `projection`(`id`, `dateDebut`, `dateFin`, `id_salle`, `id_film`, `seanceLendemain`, `id_planning`) VALUES (?,?,?,?,?,?,?)";
                
                // On crée un preparedStatement
                PreparedStatement ps = c.prepareStatement(requete);
                
                // On injecte les données dans le preparedStatement
                ps.setInt(1,projection.getId());
                ps.setTimestamp(2,projection.getDateDebut());
                ps.setTimestamp(3,projection.getDateFin());
                ps.setInt(4,projection.getSalle().getId());
                ps.setInt(5,projection.getFilm().getId());
                ps.setBoolean(6,projection.getSeanceLendemain());
                ps.setInt(7,projection.getId_planning());
                
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
         * Ajoute en base de données les vips correspondant à une projection
         * @param id_projection
         * @param id_vip
         * @param type_vip
         * @return boolean
         */
        public boolean ajouterProjectionVip(int id_projection, int id_vip, String type_vip)
        {
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On écrit la requete
                String requete = "INSERT INTO `projection_vip`(`id_projection`, `id_vip`, `type_vip`) VALUES (?,?,?)";
                
                // On crée un preparedStatement
                PreparedStatement ps = c.prepareStatement(requete);
                
                // On injecte les données dans le preparedStatement
                ps.setInt(1,id_projection);
                ps.setInt(2,id_vip);
                ps.setString(3,type_vip);
                
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
         * Modifie la projection entrée en BDD
         * @param projection
         * @return 
         */
        public boolean modifierProjection(Projection projection)
        {
            try{
                // la connexion à la BDD
                c = ConnexionBD.connect();
                
                // On écrit la requete
                String requete = "UPDATE `projection` SET `dateDebut`=?,`dateFin`=?,`id_salle`=?,`id_film`=?,`seanceLendemain`=?, `id_planning`=? WHERE id = ?";
                
                // On crée un preparedStatement
                PreparedStatement ps = c.prepareStatement(requete);
                
                // On injecte les données dans le preparedStatement
                ps.setTimestamp(1,projection.getDateDebut());
                ps.setTimestamp(2,projection.getDateFin());
                ps.setInt(3,projection.getSalle().getId());
                ps.setInt(4,projection.getFilm().getId());
                ps.setBoolean(5,projection.getSeanceLendemain());
                ps.setInt(6,projection.getId_planning());
                ps.setInt(7,projection.getId());
                
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
        
        
    
}
