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

import com.exdev.italent.obj.AdvertisementObj;

@RequestScoped
@Path("/ad")
@Produces("application/json")
@Consumes("application/json")
public class AdvertisementApi {

	@POST
	public Response create(final AdvertisementObj advertisementobj) {
		//TODO: process the given advertisementobj 
		//you may want to use the following return statement, assuming that AdvertisementObj#getId() or a similar method 
		//would provide the identifier to retrieve the created AdvertisementObj resource:
		//return Response.created(UriBuilder.fromResource(AdvertisementApi.class).path(String.valueOf(advertisementobj.getId())).build()).build();
		return Response.created(null).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the advertisementobj 
		AdvertisementObj advertisementobj = null;
		if (advertisementobj == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(advertisementobj).build();
	}

	@GET
	public List<AdvertisementObj> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the advertisementobjs 
		final List<AdvertisementObj> advertisementobjs = null;
		return advertisementobjs;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final AdvertisementObj advertisementobj) {
		//TODO: process the given advertisementobj 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the advertisementobj matching by the given id 
		return Response.noContent().build();
	}

}
