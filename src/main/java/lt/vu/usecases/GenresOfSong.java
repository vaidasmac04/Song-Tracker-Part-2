package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Genre;
import lt.vu.entities.Song;
import lt.vu.persistence.GenresDAO;
import lt.vu.persistence.SongsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Model
public class GenresOfSong {
    @Inject
    private SongsDAO songsDAO;

    @Inject
    private GenresDAO genresDAO;

    private List<Genre> allGenres;

    @Getter
    private List<Genre> availableGenres;

    @Getter
    @Setter
    private Genre genreToAdd = new Genre();

    @Getter
    @Setter
    private Song currentSong;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer songId = Integer.parseInt(requestParameters.get("songId"));
        this.currentSong = songsDAO.findOne(songId);
        this.allGenres = genresDAO.loadAll();
        this.availableGenres = findAvailableGenres();
    }

    @Transactional
    public String addGenreToSong(Genre genre){
        currentSong.getGenres().add(genre);
        songsDAO.merge(currentSong);
        return "/song-details?faces-redirect=true&songId=" + this.currentSong.getId();
    }

    @Transactional
    public String createGenre(){
        if(!allGenres.contains(genreToAdd)){
            genresDAO.persist(genreToAdd);
        }
        return addGenreToSong(genreToAdd);
    }

    private List<Genre> findAvailableGenres(){
        List<Genre> availableGenres = new ArrayList<>();
        Set<Genre> currentSongGenres = currentSong.getGenres();
        for(int i = 0; i < allGenres.size(); i++){
            if(!currentSongGenres.contains(allGenres.get(i))){
                availableGenres.add(allGenres.get(i));
            }
        }

        return availableGenres;
    }
}
