/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DeDuplication;

import java.sql.ResultSet;
import javax.ejb.Local;

/**
 *
 * @author Nisha
 */
@Local
public interface UserLocal {

    boolean register(String first, String last, String id, String email, String pass);

    boolean login(String id, String pass);

    String compAlg(String input_file, String user_id);

    boolean copyFile(String input_file, String path, String user_id);

    ResultSet listFiles(String user_id);
    
}
