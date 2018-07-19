package modelo;

import javax.persistence.*;
import java.util.List;
@Entity
public class Album {
    @Id
    private long id;
    @OneToOne
    private Usuario usuario;
    @OneToMany
    @JoinTable(name = "IMAGEN_ALBUM", joinColumns = { @JoinColumn(name = "ID_Album") }, inverseJoinColumns = { @JoinColumn(name = "ListImagen_Id_Imagen") })
    private List<Image> imagenes;
    private String descripcion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Image> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Image> imagenes) {
        this.imagenes = imagenes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
