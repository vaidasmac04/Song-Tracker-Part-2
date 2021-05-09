package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.MusicianMapper;
import lt.vu.mybatis.model.Musician;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class MusiciansMyBatis {
    @Inject
    private MusicianMapper musicianMapper;

    @Getter
    private List<Musician> allMusicians;

    @Getter
    @Setter
    private Musician musicianToCreate;


    @PostConstruct
    public void init() {
        musicianToCreate = new Musician();
        this.loadAllMusicians();
    }

    private void loadAllMusicians() {
        this.allMusicians = musicianMapper.selectAll();
    }

    @Transactional
    public String createMusician() {
        musicianMapper.insert(musicianToCreate);
        return "/myBatis/musicians?faces-redirect=true";
    }
}
