package serivicios;

import modelo.Album;
import modelo.Usuario;

import javax.persistence.*;
import java.util.List;

public class AlbumService {

    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("perunit");
    EntityManager em = emf.createEntityManager();

    public List<Album> getAlbums(Usuario usuario){

        try{
            Query query = em.createQuery("select a from Album a where a.usuario= ?1")
                    .setParameter(1,usuario);
            return (List<Album>)query.getResultList();

        }catch (NoResultException e){
            return null;
        }
    }

    public void guardarAlbum(Album p){
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();

    }
}
