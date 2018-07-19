package modelo;

import javax.persistence.*;
import java.util.List;
@Entity
public class Image {
    @Id
    private long id;
    @OneToOne
    private Usuario usuario;
    private String titulo;
    private String descripcion;
    @ManyToMany
    @JoinTable(name = "Imagen_Usuarios", joinColumns = { @JoinColumn(name = "id_imaen") }, inverseJoinColumns = { @JoinColumn(name = "ListUsuariosImagen_Id_Usuario") })
    private List<Usuario> imageUsuarios;

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Usuario> getImageUsuarios() {
        return imageUsuarios;
    }

    public void setImageUsuarios(List<Usuario> imageUsuarios) {
        this.imageUsuarios = imageUsuarios;
    }
}
