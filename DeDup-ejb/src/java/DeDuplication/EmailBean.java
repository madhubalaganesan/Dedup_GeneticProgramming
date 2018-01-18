/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DeDuplication;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Nisha
 */
@Stateless
public class EmailBean implements EmailBeanLocal {

    final String user = "baas4cloud@gmail.com";
    final String pass = "Thamizh$Rizwan";
   
    @Override
    public boolean sendEmail(String to, String subject, String body_cont) {          
        System.err.println("came to bean"+to);
        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");
System.out.println("Properties set");
        Session sess = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() { 
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("nimbusdrive.it@gmail.com" ,"nirmalit"); 
                    } 
                } 
                );
System.err.println("Mail Composed");
//        Session session = Session.getDefaultInstance(props,  
//   new javax.mail.Authenticator() {  
//   protected PasswordAuthentication getPasswordAuthentication() {  
//   return new PasswordAuthentication(user,pass);  
//   }  
//  });  
        try{
            MimeMessage message = new MimeMessage(sess);
            message.setFrom(new InternetAddress("baas4cloud@gmail.com"));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body_cont);
//            BodyPart msgPart1 = new MimeBodyPart();
//            msgPart1.setText(body_cont);
//            
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(msgPart1);
//            
//            message.setContent(multipart);
//            
            Transport.send(message);
            
            return true;
        }catch(Exception e){
            System.err.println(e);
        }
        return false;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean mailSLA(String to, String subject, String body_con, String SLA) {
        System.err.println("came to bean"+to);
        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");
System.out.println("Properties set");
        Session sess = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() { 
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("nimbusdrive.it@gmail.com" ,"nirmalit"); 
                    } 
                } 
                );
        try{
            MimeMessage message = new MimeMessage(sess);
            message.setFrom(new InternetAddress("baas4cloud@gmail.com"));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            BodyPart msgPart1 = new MimeBodyPart();
            msgPart1.setText(body_con);
            
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();    
            String filename = SLA;//change accordingly  
            DataSource source = new FileDataSource(filename);  
            messageBodyPart2.setDataHandler(new DataHandler(source));  
            messageBodyPart2.setFileName(filename);
            
             Multipart multipart = new MimeMultipart();  
            multipart.addBodyPart(msgPart1);  
            multipart.addBodyPart(messageBodyPart2);
            message.setContent(multipart);
            Transport.send(message);
            
            return true;
        }catch(Exception e){
            System.err.println(e);
        }
System.err.println("Mail Composed");
        return false;
    }

}
