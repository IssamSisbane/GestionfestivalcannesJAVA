package Modele;

import java.sql.Connection; 
import java.sql.DriverManager;

public class ConnexionBD {
	
	private static Connection connection;
	
	public static Connection connect()
        {
        try {
            // On initialise les valeurs nécessaire à la connexion à la BDD
            Class.forName("com.mysql.jdbc.Driver");
            String user = "p1906661";
            String pwd = "444815";
            String db = "p1906661";
            
            // On crée l'objet connection à la BDD
            connection = DriverManager.getConnection("jdbc:mysql://iutdoua-web.univ-lyon1.fr/"+db+"?allowPublicKeyRetrieval=true&useSSL=false",user,pwd);

            // On verifie si la connexion est valide
            if (connection == null){
                    throw new Exception("Probleme");
            }

            return connection;
            }
        catch (Exception ex) {
            System.out.println("Erreur BDD "+ex.getMessage());
            return null;
        }
    
        }
}
