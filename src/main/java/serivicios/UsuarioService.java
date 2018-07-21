package serivicios;

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
}
