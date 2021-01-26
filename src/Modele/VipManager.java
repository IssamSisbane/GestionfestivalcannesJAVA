package Modele;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Permet de gerer les interactions entre la base de données et l'application pour les objets Vip
 * @author Issam
 */
public class VipManager {
    
    private Connection c;
    
        public VipManager()
        {
        }
        
        public ArrayList<Vip> rechercheLesVips()
        { 
            
            try{
                c = ConnexionBD.connect();
                
                ArrayList<Vip> lesVIPs = new ArrayList();
                
                Statement statement = c.createStatement();
                String requete = "select * from vip";

                //recuperation du resultat d'une requete
                ResultSet result = statement.executeQuery(requete);

                //tant qu'il y a une ligne resultat
                while(result.next())
                {
                    //creation d'un objet VIP
                    Vip g = new Vip(result.getInt(1), result.getString(2), result.getString(3),
                            result.getDate(4),result.getInt(5));           
                    lesVIPs.add(g);
                }
                return lesVIPs;
            }
            catch(SQLException ex)
                {
                    System.out.println(ex.getMessage());
                    return null;
                }
        }
}