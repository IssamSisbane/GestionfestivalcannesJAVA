/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Issam
 */
public class ConnexionTest {
    
    public static DataSource getDbcpBasicDataSource(){
        
        BasicDataSource basicDataSource = new BasicDataSource();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String user = "p1906661";
            String pwd = "444815";
            String db = "p1906661";
            
            basicDataSource.setUrl("jdbc:mysql://iutdoua-web.univ-lyon1.fr/"+db+"?allowPublicKeyRetrieval=true&useSSL=false");
            basicDataSource.setUsername(user);
            basicDataSource.setPassword(pwd);
        }
        catch( Exception e )
        {
                e.printStackTrace();
        }
        return basicDataSource;
    }
    
}
