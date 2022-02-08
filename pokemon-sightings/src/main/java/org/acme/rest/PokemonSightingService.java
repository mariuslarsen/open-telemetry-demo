package org.acme.rest;

import message.PokemonSighting;
import org.acme.messaging.persistence.dao.PokemonSightingDao;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("pokemon-sightings")
@Produces(MediaType.APPLICATION_JSON)
public class PokemonSightingService {

    @Inject
    PokemonSightingDao dao;

    @GET
    @Path("id/{id}")
    public Optional<PokemonSighting> getById(@PathParam("id") int id) {
        return dao.getById(id);
    }

    @GET
    @Path("name/{name}")
    public List<PokemonSighting> getAllWithName(@PathParam("name") String name) {
        return dao.getAllByName(name);
    }
}
