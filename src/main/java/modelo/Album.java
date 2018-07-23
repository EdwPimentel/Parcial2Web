package modelo;

import javax.persistence.*;
import java.util.List;
@Entity
public class Album {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Usuario usuario;
    @OneToMany
    @JoinTable(name = "Posts_Album", joinColumns = { @JoinColumn(name = "id_album") }, inverseJoinColumns = { @JoinColumn(name = "listaPostAlbum_id_PostAlbum") })
    private List<Post> imagenes;
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

    public List<Post> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Post> imagenes) {
        this.imagenes = imagenes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
