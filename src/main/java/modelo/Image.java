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
    private List<Usuario> imageUsuarios;
}
