package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Genre;
import lt.vu.entities.Musician;
import lt.vu.entities.Song;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.GenresDAO;
import lt.vu.persistence.MusiciansDAO;
import lt.vu.persistence.SongsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Genres {
    @Inject
    private GenresDAO genresDAO;

    @Getter
    @Setter
    private Genre genreToCreate = new Genre();

    @Getter
    private List<Genre> allGenres;

    @PostConstruct
    public void init(){
        loadAllGenres();
    }

    private void loadAllGenres(){
        allGenres = genresDAO.loadAll();
    }

    @LoggedInvocation
    @Transactional
    public String createGenre(){
        this.genresDAO.persist(genreToCreate);
        return "genres?faces-redirect=true";
    }


}