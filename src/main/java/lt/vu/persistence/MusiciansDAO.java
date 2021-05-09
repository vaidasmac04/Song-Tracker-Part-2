package lt.vu.persistence;

import lt.vu.entities.Musician;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class MusiciansDAO {

    @Inject
    private EntityManager em;

    public List<Musician> loadAll() {
        return em.createNamedQuery("Musician.findAll", Musician.class).getResultList();
    }

    public void persist(Musician musician){
        this.em.persist(musician);
    }

    public Musician findOne(Integer id) { return em.find(Musician.class, id); }
}
