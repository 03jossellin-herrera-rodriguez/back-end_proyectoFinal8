package mx.uv;

public class Usuario {
    private String idUsuario;
    private String correo;
    private String contraseña;

    public Usuario(String idUsuario, String correo, String contraseña){
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }
    public String getCorreo() {
        return correo;
    }
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setId(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
