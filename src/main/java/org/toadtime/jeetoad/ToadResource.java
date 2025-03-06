package org.toadtime.jeetoad;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;
import org.toadtime.jeetoad.dto.CreateToad;
import org.toadtime.jeetoad.dto.ToadResponse;
import org.toadtime.jeetoad.entity.Toad;
import org.toadtime.jeetoad.exceptions.NotFound;
import org.toadtime.jeetoad.mapper.ToadMapper;
import org.toadtime.jeetoad.dto.UpdateToad;

import java.util.List;

@Path("toads")
@Log
public class ToadResource {

    private ToadRepository repository;

    @Inject
    public ToadResource(ToadRepository repository) {
        this.repository = repository;
    }

    public ToadResource() {
    }


    @PersistenceContext
    private EntityManager entityManager;


    @GET
    @Produces(MediaType .APPLICATION_JSON)
    public List<ToadResponse> getToads(){
        return repository.findAll().map(ToadResponse::new).toList();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ToadResponse getOneToad(@PathParam("id") Long id){
        return repository.findById(id)
                .map(ToadResponse::new)
                .orElseThrow(
                () -> new NotFound("Toad with id " + id + " not found")
        );
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewToad(CreateToad toad){
        Toad newToad = ToadMapper.map(toad);

        newToad = repository.save(newToad);
        return Response
                .status(Response.Status.CREATED)
                .header("Location", "/api/books/" + newToad.getId())
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateToad(UpdateToad toad, @PathParam("id") Long id){
        var oldToad = repository.findById(id).orElseThrow(() -> new NotFound("Toad with id " + id + " not found"));
        oldToad.setName(toad.name());
        oldToad.setDescription(toad.description());
        oldToad.setWeight(toad.weight());
        oldToad.setAge(toad.age());
        oldToad.setGender(toad.gender());
        oldToad.setBirthday(toad.birthday());
        repository.update(oldToad);
        return Response.noContent().build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateToadFieldByField(UpdateToad toad, @PathParam("id") Long id){
        var oldToad = repository.findById(id).orElseThrow(() -> new NotFound("Toad with id " + id + " not found"));
        if(toad.name() != null)
        oldToad.setName(toad.name());

        if(toad.description() != null)
        oldToad.setDescription(toad.description());

        if(toad.weight() != null)
        oldToad.setWeight(toad.weight());

        if(toad.age() != null)
        oldToad.setAge(toad.age());

        if(toad.gender() != null)
        oldToad.setGender(toad.gender());

        if(toad.birthday() != null)
        oldToad.setBirthday(toad.birthday());
        repository.update(oldToad);
        return Response.noContent().build();
    }

//    public static final class Toads{
//        private final List<Toad> values;
//        public Toads(List<Toad> values){
//            this.values = values;
//        }
//        public List<Toad> getValues(){
//            return values;
//        }
//    }

}



