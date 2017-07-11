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

import com.exdev.italent.obj.CommentObj;

@RequestScoped
@Path("/comment")
@Produces("application/json")
@Consumes("application/json")
public class CommentApi {

	@POST
	public Response create(final CommentObj commentobj) {
		//TODO: process the given commentobj 
		//you may want to use the following return statement, assuming that CommentObj#getId() or a similar method 
		//would provide the identifier to retrieve the created CommentObj resource:
		//return Response.created(UriBuilder.fromResource(CommentApi.class).path(String.valueOf(commentobj.getId())).build()).build();
		return Response.created(null).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the commentobj 
		CommentObj commentobj = null;
		if (commentobj == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(commentobj).build();
	}

	@GET
	public List<CommentObj> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the commentobjs 
		final List<CommentObj> commentobjs = null;
		return commentobjs;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final CommentObj commentobj) {
		//TODO: process the given commentobj 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the commentobj matching by the given id 
		return Response.noContent().build();
	}

}
