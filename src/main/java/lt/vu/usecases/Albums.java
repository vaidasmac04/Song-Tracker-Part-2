package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Album;
import lt.vu.entities.Musician;
import lt.vu.persistence.AlbumsDAO;
import lt.vu.persistence.MusiciansDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Albums {
    @Inject
    private AlbumsDAO albumsDAO;

    @Getter
    @Setter
    private Album albumToCreate = new Album();

    @Getter
    private List<Album> allAlbums;

    @PostConstruct
    public void init(){
        loadAllAlbums();
    }

    @Transactional
    public String createAlbum(){
        this.albumsDAO.persist(albumToCreate);
        return "albums?faces-redirect=true";
    }

    private void loadAllAlbums(){
        this.allAlbums = albumsDAO.loadAll();
    }
}
