package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Musician;
import lt.vu.entities.Song;
import lt.vu.persistence.MusiciansDAO;
import lt.vu.persistence.SongsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class SingleMusician {
    @Inject
    private MusiciansDAO musiciansDAO;

    @Getter
    @Setter
    private Musician musician;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer musicianId = Integer.parseInt(requestParameters.get("musicianId"));
        this.musician = musiciansDAO.findOne(musicianId);
    }
}
