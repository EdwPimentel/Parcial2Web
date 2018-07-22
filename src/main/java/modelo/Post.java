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
    @OneToMany
    private List<Usuario> usuarios;
    @OneToMany
    private List<Comentario> comentarios;
    private String descripcion;
    @OneToOne
    private Image imagen;

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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
}
