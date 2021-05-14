package lt.vu.rest;

import lombok.*;
import lt.vu.entities.Genre;
import lt.vu.persistence.GenresDAO;
import lt.vu.rest.contracts.GenreDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/genres")
public class GenresController {

    @Inject
    @Setter @Getter
    private GenresDAO genresDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        List<Genre> genres = genresDAO.loadAll();

        List<GenreDto> genresDto = new ArrayList<>();
        for(Genre genre : genres) {
            genresDto.add(mapToDto(genre));
        }

        return Response.ok(genresDto).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") final Integer id) {
        Genre genre = genresDAO.findOne(id);
        if (genre == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        GenreDto genreDto = mapToDto(genre);

        return Response.ok(genreDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(GenreDto genreDto) {
        genresDAO.persist(mapFromDto(genreDto));
        return Response.ok().build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer id,
            GenreDto genreDto) {
        try {
            Genre existingGenre = genresDAO.findOne(id);
            if (existingGenre == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingGenre.setName(genreDto.getName());
            genresDAO.update(existingGenre);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    private GenreDto mapToDto(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setName(genre.getName());
        return genreDto;
    }

    private Genre mapFromDto(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setName(genreDto.getName());
        return genre;
    }
}