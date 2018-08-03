package modelo;

import javax.persistence.*;

@Entity
public class Megusta {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Comentario")
    private Comentario comentario;
    @ManyToOne
    @JoinColumn(name = "ID_Album")
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private boolean megusta=false;

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
