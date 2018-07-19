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
    private List<Usuario> usuarios;
    private String descripcion;
    @OneToOne
    private Image imagen;

}
