package modelo;

import javax.persistence.*;
import java.util.List;
@Entity
public class Post {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Usuario usuario;

    private String descripcion;

    private String img;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Album album;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
