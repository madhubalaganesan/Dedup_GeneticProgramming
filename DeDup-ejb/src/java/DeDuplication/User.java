/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DeDuplication;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.swing.JOptionPane;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

/**
 *
 * @author Nisha
 */
@Stateless
public class User implements UserLocal {
    Connection conn;
    OraclePreparedStatement pst;
    OracleResultSet rs;
    @Override
    public boolean register(String first, String last, String id, String email, String pass) {
        conn = JavaConnect.connectDb();
        try{
            String sql = "insert into drive_user values('"+first+"','"+last+"','"+id+"','"+email+"','"+pass+"')";
            pst = (OraclePreparedStatement) conn.prepareStatement(sql);
            pst.execute();
            return true;
        }catch(Exception e){
            System.err.println(e);
        }
        return false;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean login(String id, String pass) {
        conn = JavaConnect.connectDb();
        try{
            String sql = "select * from drive_user where user_id='"+id+"' and pass='"+pass+"'";
            pst = (OraclePreparedStatement) conn.prepareStatement(sql);
            rs = (OracleResultSet) pst.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(Exception e){
            System.err.println(e);
        }
        return false;
    }

    @Override
    public String compAlg(String input_file, String user_id) {
        conn = JavaConnect.connectDb();
        try{
    
    File originalfile=new File(input_file);
    File inputfolder=new File("C:/project/storage");
    String ky=cv.convert(new File(input_file),"C:/project/temp/key.txt");
    File inputfile=new File(ky);
    int flag=0;
    
    File fl[]=inputfolder.listFiles();
    for(int i=0;i<fl.length;i++)
    {       //System.out.println("i="+i);
        
        String cfp=cv.convert(fl[i],"C:/project/temp/cur.txt");
        //System.out.println("processed "+cfp);
        Thread.sleep(1000);
        int cr=0;
        System.err.println("Came to the loop "+i);
        if(seq.compare(inputfile.getAbsolutePath(), cfp) >  80  )
                {  
                //JOptionPane.showMessageDialog(rootPane, " matching file found:"+fl[i].getName());
                //op=0 f-or yes op=1 for no
                
    //                System.out.println(op2);
                        //File temp=new File(path.getText());
                      //  seq.copy(originalfile.getPath(),"C:/project/storage/copy of " +originalfile.getName());//destination);
                        
                        
                flag=1;
                System.err.println("Match Found");
                return "C:/project/storage/copy of " +originalfile.getName();
                //break;
                }
            
    }
    if(flag==0){
        String dest = "C:/project/storage/"+originalfile.getName();
        seq.copy(originalfile.getPath(), "C:/project/storage/"+originalfile.getName());
        String sql = "insert into user_files values('"+user_id+"','"+dest+"')";
        pst = (OraclePreparedStatement) conn.prepareStatement(sql);
        pst.execute();
        System.err.println("No match so the file wrote in "+dest);
        return "Success";
    }
    }
    catch(Exception ex)
    {
    Logger.getLogger(upload.class.getName()).log(Level.SEVERE, null, ex);
    }//
        return "Error";
    }

    @Override
    public boolean copyFile(String input_file, String path, String user_id) {
        conn = JavaConnect.connectDb();
        try{
            File originalfile=new File(input_file);
            seq.copy(originalfile.getPath(), path);//destination);
            String sql = "insert into user_files values('"+user_id+"','"+path+"')";
            pst = (OraclePreparedStatement) conn.prepareStatement(sql);
            pst.execute();
            return true;
        }catch(Exception e){
            System.err.println(e);
        }
        return false;
    }

    @Override
    public ResultSet listFiles(String user_id) {
        conn = JavaConnect.connectDb();
        try{
            String sql = "select * from user_files where user_id='"+user_id+"'";
            System.err.println(sql);
            pst = (OraclePreparedStatement) conn.prepareStatement(sql);
            rs = (OracleResultSet) pst.executeQuery();
            return rs;
        }catch(Exception e){
            System.err.println(e);
        }
        return null;
    }
}
