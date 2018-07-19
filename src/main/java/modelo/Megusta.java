package modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Megusta {
    @Id
    private long id;
    @OneToOne
    private Image image;
    @OneToOne
    private Comentario comentario;
    @OneToOne
    private Album album;
    @OneToOne
    private Post post;
    
    private boolean megusta;
}
