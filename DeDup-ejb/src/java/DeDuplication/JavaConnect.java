/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DeDuplication;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Thamizh
 */
public class JavaConnect {
    Connection conn;
    public static Connection connectDb(){        
        Connection con=null;
        try{
           Class.forName("oracle.jdbc.OracleDriver");
           con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","thamizh","nith");
           return con;
        }catch(Exception e){
           System.err.println(e);
        }
        return con;
    }
}
