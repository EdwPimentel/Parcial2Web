package serivicios;

import modelo.Post;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PostService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("perunit");
    EntityManager em = emf.createEntityManager();
    public void savePost(Post post){
        em.getTransaction().begin();
        em.persist(post);
        em.getTransaction().commit();

    }

}
