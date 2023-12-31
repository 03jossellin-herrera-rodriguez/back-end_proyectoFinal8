package mx.uv;

import com.google.gson.JsonObject;
import static spark.Spark.*;
import java.util.HashMap;
import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
    static Gson gson = new Gson();
    static HashMap<String, Usuario> usuarios = new HashMap<>();
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        //CORS
        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
        //CORS

        post("/registro", (request, response) -> {
            response.type("application/json");
            String payload = request.body();
            Usuario usuario = gson.fromJson(payload, Usuario.class);
            System.out.println("payload " + payload);
            String respuesta = DAO.crearUsuario(usuario);

            return respuesta;
        });

        post("/realizarPublicacion", (request, response) -> {
            response.type("application/json");
            String payload = request.body();
            Publicacion publicacion = gson.fromJson(payload, Publicacion.class);
            System.out.println("payload " + payload);
            String respuesta = DAO.realizarPublicacion(publicacion);

            return respuesta;
        });

        post("/validacion", (request, response) -> {
            response.type("application/json");
            String payload = request.body();
            String mensaje = "";
            Usuario usuario = gson.fromJson(payload, Usuario.class);
            System.out.println("usuario " + usuario.getCorreo() + usuario.getContraseña());
            boolean respuesta = DAO.usuarioRegistrado(usuario.getCorreo(), usuario.getContraseña());
            System.out.println(respuesta);
            if (respuesta == true) {
                System.out.println("Usuario Correcto");
                mensaje = "Usuario Correcto";
            } else {
                System.out.println("Usuario incorrecto");
                mensaje = "Usuario incorrecto";
            }
            return mensaje;
        });

        get("/Publicaciones", (request, response) -> {
            response.type("application/json");
            return gson.toJson(DAO.damePublicacion());
        });

        get("/MisPublicaciones", (request, response) -> {
            String nombre = request.queryParams("idUsuario");
            response.type("application/json");
            return gson.toJson(DAO.dameMiPublicacion(nombre));
        });

        get("/datosUsuario", (request, response) -> {
            String correo = request.queryParams("correo");
            response.type("application/json");
            return gson.toJson(DAO.datosUsuario(correo));
        });

        get("/eliminarPublicacion", (request, response) -> {
            response.type("application/json");
            String idPublicacion = request.queryParams("idPublicacion");
            System.out.println("Publicacion con id: " + idPublicacion);
            boolean respuesta = DAO.eliminarPublicacion(idPublicacion);
            JsonObject mensaje = new JsonObject();
            mensaje.addProperty("respuesta", respuesta);
            return mensaje;
        });
    }
}
