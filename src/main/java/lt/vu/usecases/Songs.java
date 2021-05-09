package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Album;
import lt.vu.entities.Musician;
import lt.vu.entities.Song;
import lt.vu.persistence.AlbumsDAO;
import lt.vu.persistence.GenresDAO;
import lt.vu.persistence.MusiciansDAO;
import lt.vu.persistence.SongsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Model
public class Songs {
    @Inject
    private SongsDAO songsDAO;

    @Inject
    private MusiciansDAO musiciansDAO;

    @Inject
    private AlbumsDAO albumsDAO;

    @Inject
    private GenresDAO genresDAO;

    @Getter
    @Setter
    private Song songToCreate = new Song();

    @Getter
    @Setter
    private int songToCreate_musicianId;

    @Getter
    @Setter
    private int songToCreate_albumId;

    @Getter
    @Setter
    private List<Integer> songToCreate_genresId = new ArrayList();

    @Getter
    private List<Song> allSongs;

    @PostConstruct
    public void init(){
        loadAllSongs();
    }

    @Transactional
    public String createSong(){
        Musician musician = musiciansDAO.findOne(songToCreate_musicianId);

        if(musician != null){
            songToCreate.setMusician(musiciansDAO.findOne(songToCreate_musicianId));
        }

        Album album = albumsDAO.findOne(songToCreate_albumId);
        if(album != null){
            songToCreate.setAlbum(albumsDAO.findOne(songToCreate_albumId));
        }

        addGenres();
        songsDAO.persist(songToCreate);
        return "songs?faces-redirect=true";
    }

    private void loadAllSongs(){
        allSongs = songsDAO.loadAll();
    }

    private void addGenres() {
        for(int i = 0; i < songToCreate_genresId.size(); i++){
            songToCreate.getGenres().add(genresDAO.findOne(songToCreate_genresId.get(i)));
        }
    }
}
