package serivicios;

import modelo.Post;
import modelo.Usuario;

import javax.persistence.*;

public class UsuarioService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("perunit");
    EntityManager em = emf.createEntityManager();

    public Usuario login(String name, String password){

        try{
            Query query = em.createQuery("select user from Usuario user where user.username = :username AND user.password = :password")
                    .setParameter("username", name)
                    .setParameter("password", password);
            return (Usuario)query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }

    }
    public Usuario checkUser(String name){

        try{
            Query query = em.createQuery("select user from Usuario user where user.username = :username")
                    .setParameter("username", name);
            return (Usuario)query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }

    }
    public void saveUser(Usuario usuario){
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

    }
    public void newPost(Usuario user, Post post){
        em.getTransaction().begin();
        Usuario u = em.find(Usuario.class, user.getId());
        u.getWall().add(post);
        em.merge(u);
        em.getTransaction().commit();

    }
    public Usuario getUsuarioId(Long id){

        try{
            Query query = em.createQuery("select u from Usuario u where u.id = :id")
                    .setParameter("id", id);
            return (Usuario)query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }

    }


}
