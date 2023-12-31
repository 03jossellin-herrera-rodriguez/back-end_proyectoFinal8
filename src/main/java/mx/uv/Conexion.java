package mx.uv;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexion {
    public static String url="jdbc:mysql://db4free.net:3306/proyectspherebw8";
    public static String Drivername="com.mysql.cj.jdbc.Driver";
    public static String username="sphereteam_08";
    public static String password="eslmqcerd08";

    private static Connection conexion=null;

    public static Connection getConnection(){
        try {
            Class.forName(Drivername);
            conexion = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(" SQL:" + e);
        } catch (ClassNotFoundException e){
            System.out.println("Driver:" + e);
        }
        return conexion;
    }
}
