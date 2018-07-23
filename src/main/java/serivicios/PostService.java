package serivicios;

import modelo.Comentario;
import modelo.Post;
import modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class PostService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("perunit");
    EntityManager em = emf.createEntityManager();
    public void savePost(Post post) {
        em.getTransaction().begin();
        em.persist(post);
        em.getTransaction().commit();
    }
        public void newComentario(Post post,Comentario comentario){
            em.getTransaction().begin();
            Post p = em.find(Post.class,post.getId());
            p.getComentarios().add(comentario);
            em.merge(p);
            em.getTransaction().commit();

        }
    public Post findPost(Long id){
        Query query = em.createQuery("select p from Post p where p.id = :post")
                .setParameter("post", id);
        return (Post) query.getSingleResult();
    }

    public int findIndex(List<Comentario> lista, long id){
        int i=0;
        for (i=0; i<lista.size();i++){
            if(id==lista.get(i).getId()){
                return i;
            }
        }
        return 0;
    }

}


