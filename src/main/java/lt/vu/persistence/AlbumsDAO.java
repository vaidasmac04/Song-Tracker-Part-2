package lt.vu.persistence;

import lt.vu.entities.Album;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AlbumsDAO {
    @Inject
    private EntityManager em;

    public List<Album> loadAll() {
        return em.createNamedQuery("Album.findAll", Album.class).getResultList();
    }

    public Album findOne(Integer id) {
        return em.find(Album.class, id);
    }

    public void persist(Album album) {
        this.em.persist(album);
    }

    public void merge(Album album) {
        this.em.merge(album);
    }
}
