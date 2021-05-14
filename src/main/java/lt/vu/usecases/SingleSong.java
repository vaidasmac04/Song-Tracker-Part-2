package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Album;
import lt.vu.entities.Genre;
import lt.vu.entities.Musician;
import lt.vu.entities.Song;
import lt.vu.persistence.AlbumsDAO;
import lt.vu.persistence.GenresDAO;
import lt.vu.persistence.MusiciansDAO;
import lt.vu.persistence.SongsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

@ViewScoped
@Named
@Getter
@Setter
public class SingleSong implements Serializable {
    @Inject
    private AlbumsDAO albumsDAO;

    @Inject
    private MusiciansDAO musiciansDAO;

    @Inject
    private SongsDAO songsDAO;

    @Inject
    private GenresDAO genresDAO;

    private List<Genre> availableGenres = new ArrayList<>();

    private Song songToUpdate = new Song();

    private int songToUpdate_musicianId;

    private int songToUpdate_albumId;

    private List<Integer> songToUpdate_genresId = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer songId = Integer.parseInt(requestParameters.get("songId"));
        songToUpdate = songsDAO.findOne(songId);
        prepareSong();
        availableGenres = loadAvailableGenres();
    }

    private List<Integer> loadCurrentGenresId() {
        List<Integer> currentGenresId = new ArrayList<>();
        List<Genre> currentGenres = new ArrayList<>(songToUpdate.getGenres());
        for(Genre g : currentGenres){
            currentGenresId.add(g.getId());
        }
        return currentGenresId;
    }

    private List<Genre> loadAvailableGenres(){
        List<Genre> genres = new ArrayList<>();

        for(Genre g : genresDAO.loadAll()){
          if(!songToUpdate.getGenres().contains(g)){
              genres.add(g);
          }
        }

        return genres;
    }

    @Transactional
    public String updateSong(){
        try {
            Musician musician = musiciansDAO.findOne(songToUpdate_musicianId);

            if(musician != null){
                songToUpdate.setMusician(musiciansDAO.findOne(songToUpdate_musicianId));
            }
            else{
                songToUpdate.setMusician(null);
            }

            Album album = albumsDAO.findOne(songToUpdate_albumId);
            if(album != null){
                songToUpdate.setAlbum(albumsDAO.findOne(songToUpdate_albumId));
            }
            else{
                songToUpdate.setAlbum(null);
            }

            updateGenres();
            songsDAO.merge(songToUpdate);
        } catch(OptimisticLockException e) {
            return "/song-details?faces-redirect=true&songId="+ songToUpdate.getId() + "&error=optimistic-lock-exception";
        }

        return "/songs?faces-redirect=true";
    }



    private void prepareSong() {
        if(songToUpdate.getMusician() == null){
            songToUpdate_musicianId = 0;
        }
        else {
            songToUpdate_musicianId = songToUpdate.getMusician().getId();
        }

        if(songToUpdate.getAlbum() == null){
            songToUpdate_albumId = 0;
        }
        else {
            songToUpdate_albumId = songToUpdate.getAlbum().getId();
        }

        songToUpdate_genresId = loadCurrentGenresId();
    }

    private void updateGenres() {
        List<Genre> oldGenres = new ArrayList<>(songToUpdate.getGenres());
        List<Genre> newGenres = new ArrayList<>(loadNewGenres());

        for(Genre g : newGenres){
            if(!oldGenres.contains(g)){
                songToUpdate.getGenres().add(g);
            }
        }

        for(Genre g : oldGenres){
            if(!newGenres.contains(g)){
                songToUpdate.getGenres().remove(g);
            }
        }
    }

    private Set<Genre> loadNewGenres(){
        Set<Genre> genres = new HashSet<>();
        for(Integer id : songToUpdate_genresId){
            genres.add(genresDAO.findOne(id));
        }
        return genres;
    }
}
