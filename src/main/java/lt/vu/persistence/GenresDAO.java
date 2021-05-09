package lt.vu.persistence;

import lt.vu.entities.Genre;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class GenresDAO {
    @Inject
    private EntityManager em;

    public List<Genre> loadAll() {
        return em.createNamedQuery("Genre.findAll", Genre.class).getResultList();
    }

    public Genre findOne(Integer id) {
        return em.find(Genre.class, id);
    }

    public void persist(Genre genre) {
        this.em.persist(genre);
    }

    public void update(Genre genre) {
        this.em.merge(genre);
    }
}
