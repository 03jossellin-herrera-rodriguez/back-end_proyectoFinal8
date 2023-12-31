package mx.uv;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DAO {
    private static Conexion c = new Conexion();

    public static Usuario datosUsuario(String correoUsuario) {
        Connection conn = c.getConnection();
        Usuario usuario = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE correo='" + correoUsuario + "'");
            while (rs.next()) {
                usuario = new Usuario( rs.getString("idUsuario"), rs.getString("correo"), rs.getString("contraseña"));
            }
        } catch (Exception ex) {
            System.out.println("Error al obtener datos del usuario: " + ex.toString());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return usuario;
    }

    public static List<Publicacion> damePublicacion() {
        Connection conn = c.getConnection();
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM publicacion");
            while (rs.next()) {
                Publicacion publicacion = new Publicacion(rs.getString("idPublicacion"), rs.getString("idUsuario"), rs.getString("contenido"), rs.getString("fechaPub"));
                publicaciones.add(publicacion);
            }
        } catch (Exception ex) {
            System.out.println("Error al obtener publicaciones: " + ex.toString());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return publicaciones;
    }

    public static List<Publicacion> dameMiPublicacion(String nombreUsuario){
        Connection conn = c.getConnection();
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        try{
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM publicacion WHERE idUsuario = '" + nombreUsuario + "';");
             while (rs.next()){
                Publicacion publicacion = new Publicacion(rs.getString("idPublicacion"), rs.getString("idUsuario"), 
                rs.getString("contenido"), rs.getString("fechaPub"));
                publicaciones.add(publicacion);
             }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return publicaciones;
    }

    public static String crearUsuario(Usuario u) {
        PreparedStatement stm = null;
        Connection conn = null;
        String msj = "";

        conn = c.getConnection();
        try {
            String sql = "INSERT INTO usuario (idUsuario, correo, contraseña) values (?,?,?)";
            stm = (PreparedStatement) conn.prepareStatement(sql);
            stm.setString(1, u.getIdUsuario());
            stm.setString(2, u.getCorreo());
            stm.setString(3, u.getContraseña());
            if (stm.executeUpdate() > 0)
                msj = "Usuario agregado";
            else
                msj = "usuario no agregado";

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                stm = null;
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return msj;
    }

    public static boolean usuarioRegistrado(String correo, String contraseña) {
        boolean respuesta = false;
        Connection conn = c.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT correo, contraseña FROM usuario WHERE correo='" + correo + "' AND contraseña='" + contraseña + "';");        
            //ResultSet rs = st.executeQuery("SELECT correo FROM usuario WHERE correo='" + correo + "';");
            if (rs.next()) {
                respuesta = true;
            }
        } catch (Exception ex) {
            System.out.println("Error al iniciar sesion: " + ex.toString());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return respuesta;
    }

    /*public static String realizarPublicacion(String idPublicacion, String idUsuario, String contenido, String fechaPub){
        Connection conn = c.getConnection();
        PreparedStatement stm = null;
        String msj = "";
        try{
            String sql = "INSERT INTO publicacion values (?,?,?,?)";
            stm = (PreparedStatement) conn.prepareStatement(sql);
            stm.setString(1, idPublicacion);
            stm.setString(2, idUsuario);
            stm.setString(3, contenido);
            stm.setString(4, fechaPub);
            if (stm.executeUpdate() > 0)
                msj = "Publicacion realizada";
            else
                msj = "La publicacion no se realizo";
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return msj;
    }*/

    public static String realizarPublicacion(Publicacion p){
        Connection conn = c.getConnection();
        PreparedStatement stm = null;
        String msj = "";
        try{
            String sql = "INSERT INTO publicacion values (?,?,?,?)";
            stm = (PreparedStatement) conn.prepareStatement(sql);
            stm.setString(1, p.getIdPublicacion());
            stm.setString(2, p.getFechaPub());
            stm.setString(3, p.getContenido());
            stm.setString(4, p.getIdUsuario());
            if (stm.executeUpdate() > 0)
                msj = "Publicacion realizada";
            else
                msj = "La publicacion no se realizo";
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return msj;
    }

    public static Boolean eliminarPublicacion(String idPublicacion) {
        Connection conn = c.getConnection();
        boolean eliminado = false;
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM publicacion WHERE idPublicacion='" + idPublicacion + "'");
            ps.executeUpdate();
            eliminado = true;
        }catch (Exception ex) {
            System.out.println("Error al eliminar publicacion: " + ex.toString());
        }finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return eliminado;
    }

}
