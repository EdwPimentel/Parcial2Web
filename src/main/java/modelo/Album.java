package modelo;

import javax.persistence.*;
import java.util.List;
@Entity
public class Album {
    @Id
    private long id;
    @ManyToOne
    private Usuario usuario;
    @OneToMany
    @JoinTable(name = "IMAGEN_ALBUM", joinColumns = { @JoinColumn(name = "ID_Album") }, inverseJoinColumns = { @JoinColumn(name = "ListImagen_Id_Imagen") })
    private List<Image> imagenes;
    private String descripcion;

}
