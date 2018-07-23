package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Megusta {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Comentario comentario;
    @OneToOne
    private Album album;
    @OneToOne
    private Post post;
    private boolean megusta;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean isMegusta() {
        return megusta;
    }

    public void setMegusta(boolean megusta) {
        this.megusta = megusta;
    }
}
