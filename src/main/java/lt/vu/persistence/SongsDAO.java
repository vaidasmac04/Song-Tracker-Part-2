package lt.vu.persistence;

import lt.vu.entities.Song;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class SongsDAO {
    @Inject
    private EntityManager em;

    public void persist(Song song){
        this.em.persist(song);
    }

    public void merge(Song song) {
        this.em.merge(song);
    }

    public Song findOne(Integer id) { return em.find(Song.class, id); }

    public List<Song> loadAll() {
        return em.createNamedQuery("Song.findAll", Song.class).getResultList();
    }
}
