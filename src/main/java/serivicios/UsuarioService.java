package serivicios;

import modelo.Post;
import modelo.Usuario;

import javax.persistence.*;
import java.util.List;

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
    public List<Usuario> sugerirAmigos(Usuario usuario){
        try{
            Query query = em.createQuery("select u from Usuario u where u.lugarEstudio = ?1" +
                    " or u.lugarNac = ?2 or u.lugarVive = ?3 or u.lugarTrabajo = ?4 and u.id != ?5")
                    .setParameter(1,usuario.getLugarEstudio())
                    .setParameter(2,usuario.getLugarNac())
                    .setParameter(3,usuario.getLugarVive())
                    .setParameter(4,usuario.getLugarTrabajo())
                    .setParameter(5,usuario.getId());

            return (List<Usuario>)query.getResultList();
        }catch (NoResultException e){
            return null;
        }

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
    public void addFriend(Usuario usuario, Usuario amigo){
        em.getTransaction().begin();
        Usuario u = em.find(Usuario.class,usuario.getId());
        u.getFriendlist().add(amigo);
        Usuario a = em.find(Usuario.class,amigo.getId());
        a.getFriendlist().add(usuario);
        em.merge(u);
        em.merge(a);
        em.getTransaction().commit();

    }


}
