package serivicios;

import modelo.Comentario;
import modelo.Post;
import modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PostService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("perunit");
    EntityManager em = emf.createEntityManager();
    public void savePost(Post post) {
        em.getTransaction().begin();
        em.persist(post);
        em.getTransaction().commit();
    }
        public void newComentario(Usuario user, Post post,Comentario comentario){
            em.getTransaction().begin();
            Usuario u = em.find(Usuario.class,user.getId());
            Post p = em.find(Post.class,post.getId());
            em.merge(u);
            em.merge(p);
            em.getTransaction().commit();

        }

    }


