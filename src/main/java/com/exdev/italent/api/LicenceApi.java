package com.exdev.italent.api;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.exdev.italent.obj.LicenceObj;

@RequestScoped
@Path("/licence")
@Produces("application/json")
@Consumes("application/json")
public class LicenceApi {

	@POST
	public Response create(final LicenceObj licenceobj) {
		//TODO: process the given licenceobj 
		//you may want to use the following return statement, assuming that LicenceObj#getId() or a similar method 
		//would provide the identifier to retrieve the created LicenceObj resource:
		//return Response.created(UriBuilder.fromResource(LicenceApi.class).path(String.valueOf(licenceobj.getId())).build()).build();
		return Response.created(null).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the licenceobj 
		LicenceObj licenceobj = null;
		if (licenceobj == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(licenceobj).build();
	}

	@GET
	public List<LicenceObj> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the licenceobjs 
		final List<LicenceObj> licenceobjs = null;
		return licenceobjs;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final LicenceObj licenceobj) {
		//TODO: process the given licenceobj 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the licenceobj matching by the given id 
		return Response.noContent().build();
	}

}
