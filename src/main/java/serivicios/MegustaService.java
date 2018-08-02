package serivicios;

import modelo.Comentario;
import modelo.Megusta;
import modelo.Post;
import modelo.Usuario;

import javax.persistence.*;
import java.util.List;

public class MegustaService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("perunit");
    EntityManager em = emf.createEntityManager();

    public void guardarLike(Megusta r){
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();

    }

    public List<Megusta> getMegusta(){

        try{
            Query query = em.createQuery("select g from Megusta g");
            return (List<Megusta>)query.getResultList();

        }catch (NoResultException e){
            return null;
        }
    }
    public void deleteMegusta(Megusta g){
        em.getTransaction().begin();
        Megusta re = em.find(Megusta.class,g.getId());
        em.remove(re);
        em.getTransaction().commit();
    }

    public void updateMegusta(Megusta g, boolean valor){
        em.getTransaction().begin();
        g.setMegusta(valor);
        em.merge(g);
        em.getTransaction().commit();
    }

    public Megusta CheckLikePost(Usuario Usuario, Post post){
        try{
            Query query = em.createQuery("select g from Megusta g where g.post = ?1 AND g.usuario = ?2")
                    .setParameter(1, post)
                    .setParameter(2, Usuario);
            return (Megusta)query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }

    public Megusta ComentarioLikeCheck(Usuario Usuario, Comentario comentario){
        try{
            Query query = em.createQuery("select g from Megusta g where g.comentario = ?1 AND g.usuario = ?2")
                    .setParameter(1, comentario)
                    .setParameter(2, Usuario);
            return (Megusta)query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
}
