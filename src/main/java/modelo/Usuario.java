package modelo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
public class Usuario {
    @Id
    @GeneratedValue

    private long id;
    private String Nombre;
    private String Apellido;
    private String username;
    private String password;
    private Date fechaNac;
    private String lugarNac;
    private String lugarVive;
    private String lugarTrabajo;
    private String lugarEstudio;
    private boolean admin;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Amigos_Usuarios", joinColumns = { @JoinColumn(name = "id_usuario") }, inverseJoinColumns = { @JoinColumn(name = "listaAmigoUsuario_id_amigoUsuario") })
    private List<Usuario> friendlist;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getLugarNac() {
        return lugarNac;
    }

    public void setLugarNac(String lugarNac) {
        this.lugarNac = lugarNac;
    }

    public String getLugarVive() {
        return lugarVive;
    }

    public void setLugarVive(String lugarVive) {
        this.lugarVive = lugarVive;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getLugarEstudio() {
        return lugarEstudio;
    }

    public void setLugarEstudio(String lugarEstudio) {
        this.lugarEstudio = lugarEstudio;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Usuario> getFriendlist() {
        return friendlist;
    }

    public void setFriendlist(List<Usuario> friendlist) {
        this.friendlist = friendlist;
    }
}