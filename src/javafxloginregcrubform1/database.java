/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxloginregcrubform1;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Jean_Nico .T
 */
public class database {

    public static Connection coonect() {
        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/javafxcrud", "root", ""); //root is the default username 
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
