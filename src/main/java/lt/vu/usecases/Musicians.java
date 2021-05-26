package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Musician;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.MusiciansDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Musicians {

    @Inject
    private MusiciansDAO musiciansDAO;

    @Getter
    @Setter
    private Musician musicianToCreate = new Musician();

    @Getter
    private List<Musician> allMusicians;


    @PostConstruct
    public void init(){
        loadAllMusicians();
    }

    @LoggedInvocation
    @Transactional
    public String createMusician(){
        this.musiciansDAO.persist(musicianToCreate);
        return "musicians?faces-redirect=true";
    }

    private void loadAllMusicians(){
        this.allMusicians = musiciansDAO.loadAll();
    }
}
