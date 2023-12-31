package mx.uv;

public class Publicacion {
    private String idPublicacion;
    private String idUsuario;
    private String contenido;
    private String fechaPub;

    public Publicacion(String idPublicacion, String idUsuario, String contenido, String fechaPub){
        this.idPublicacion=idPublicacion;
        this.idUsuario=idUsuario;
        this.contenido=contenido;
        this.fechaPub=fechaPub;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setFechaPub(String fechaPub) {
        this.fechaPub = fechaPub;
    }

    public void setIdPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getIdUsuario() {
        return idUsuario;
    }

    public String getContenido() {
        return contenido;
    }

    public String getFechaPub() {
        return fechaPub;
    }
    public String getIdPublicacion() {
        return idPublicacion;
    }
}
