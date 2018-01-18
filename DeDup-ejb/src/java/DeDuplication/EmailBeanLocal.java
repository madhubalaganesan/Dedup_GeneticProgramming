/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DeDuplication;

import javax.ejb.Local;

/**
 *
 * @author Nisha
 */
@Local
public interface EmailBeanLocal {
     boolean sendEmail(String parameter, String parameter1, String parameter2);

    boolean mailSLA(String to, String subject, String body_con, String SLA);
   
}
