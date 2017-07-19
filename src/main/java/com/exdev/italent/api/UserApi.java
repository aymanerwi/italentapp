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

import com.exdev.italent.obj.UserObj;

@RequestScoped
@Path("/user")
@Produces("application/json")
@Consumes("application/json")
public class UserApi {

	@POST
	public Response create(final UserObj userobj) {
		//TODO: process the given loginobj 
		//you may want to use the following return statement, assuming that UserObj#getId() or a similar method 
		//would provide the identifier to retrieve the created UserObj resource:
		//return Response.created(UriBuilder.fromResource(UserApi.class).path(String.valueOf(loginobj.getId())).build()).build();
		return Response.created(null).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the loginobj 
		UserObj loginobj = null;
		if (loginobj == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(loginobj).build();
	}

	@GET
	public List<UserObj> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the loginobjs 
		final List<UserObj> loginobjs = null;
		return loginobjs;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final UserObj userobj) {
		//TODO: process the given loginobj 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the loginobj matching by the given id 
		return Response.noContent().build();
	}

}
