package modelo;

import javax.persistence.*;
import java.util.List;
@Entity
public class Post {
    @Id
    private long id;
    @OneToOne
    private Usuario usuario;
    @OneToMany
    @JoinTable(name = "Post_Usuarios", joinColumns = { @JoinColumn(name = "id_post") }, inverseJoinColumns = { @JoinColumn(name = "ListUsuariosPost_Id_Usuario") })
    private List<Usuario> usuarios;
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
