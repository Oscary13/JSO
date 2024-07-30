/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alien 2
 */
public class ConnectionDB {

    public static Connection GetConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
        String usuario = "OBasurtoProgramacionNCapas";
        String password = "password1";

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            //try-with-resources - mejora el cierre de los recursos
            Connection context = DriverManager.getConnection(url, usuario, password);
            return context;
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }
}
