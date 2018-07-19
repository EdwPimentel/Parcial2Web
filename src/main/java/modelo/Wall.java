package modelo;

import javax.persistence.*;
import java.util.List;
@Entity
public class Wall {
    @Id
    private long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @OneToMany
    @JoinTable(name = "Muro_Post", joinColumns = { @JoinColumn(name = "Id_muro") }, inverseJoinColumns = { @JoinColumn(name = "ListPost_ID_post") })
    private List<Post> posts;
}
