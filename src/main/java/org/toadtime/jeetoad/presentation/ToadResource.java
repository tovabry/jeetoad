package org.toadtime.jeetoad.presentation;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;
import org.toadtime.jeetoad.business.ToadService;
import org.toadtime.jeetoad.dto.*;
import org.toadtime.jeetoad.entity.Toad;

import java.util.List;


@Path("toads")
@Log
public class ToadResource {

    private ToadService toadService;

    @Inject
    public ToadResource(ToadService toadService) {
        this.toadService = toadService;
    }

    public ToadResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDto getToads() {
        return new ResponseDto(toadService.getAllToads());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ToadResponse getOneToad(@PathParam("id") Long id) {
        return toadService.getToadById(id);
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ToadResponse getOneToadByName(@PathParam("name") String name) {
        return toadService.getToadByName(name);
    }

    @GET
    @Path("gender/{gender}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ToadResponse> getOneToadByGender(@PathParam("gender") String gender) {
        char genderChar = gender.charAt(0);
        return toadService.getToadsByGender(genderChar);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewToad(@Valid @NotNull CreateToad toad) {
        if (toad == null)
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Toad cannot be null").build();

        Toad newToad = toadService.createToad(toad);
        log.info("Creating new toad: " + newToad);
        return Response
                .status(Response.Status.CREATED)
                .header("Location", "/api/toads/" + newToad.getId())
                .build();
    }

    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateToadFieldByField(@Valid UpdateToad toad, @PathParam("id") Long id) {
        toadService.updateToad(toad, id);
        return Response.noContent().build();
    }

}



