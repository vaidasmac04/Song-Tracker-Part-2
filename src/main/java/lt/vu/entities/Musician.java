package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "Musician.findAll", query = "select m from Musician as m")
})
public class Musician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String stageName;

    @OneToMany(mappedBy = "musician")
    private List<Song> songs = new ArrayList<>();

    @OneToMany(mappedBy = "musician")
    private List<Album> albums = new ArrayList<>();

    public Musician(){

    }
}
