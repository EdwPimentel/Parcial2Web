package modelo;

import javax.persistence.*;

@Entity
public class Comentario {
    @Id
    private long id;
    private String texto;
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;
    @ManyToOne(fetch = FetchType.EAGER)
    private Image image;

}
