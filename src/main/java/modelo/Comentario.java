package modelo;

import javax.persistence.*;

@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private long id;
    private String texto;

    @OneToOne

    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
