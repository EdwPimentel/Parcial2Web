package serivicios;

import modelo.Comentario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ComentarioService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("perunit");
    EntityManager em = emf.createEntityManager();
    public void saveComentario(Comentario c){
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();

    }
}
