package modelo;

import javax.persistence.Entity;

@Entity
public class Megusta {
    private long id;
    private Image image;
    private Comentario comentario;
    private Album album;
    private Post post;
    private boolean megusta;
}
