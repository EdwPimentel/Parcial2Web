package serivicios;

import modelo.Comentario;

import javax.persistence.*;
import java.util.List;

public class ComentarioService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("perunit");
    EntityManager em = emf.createEntityManager();
    public void saveComentario(Comentario c){
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();

    }
    public List<Comentario> getComentarios(){
        try{
            Query query = em.createQuery("select c from Comentario c");
            return (List<Comentario>)query.getResultList();
        }catch (NoResultException e){
            return null;
        }

    }
}
