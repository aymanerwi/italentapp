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

import com.exdev.italent.obj.WorkDetailsObj;

@RequestScoped
@Path("/workdetails")
@Produces("application/json")
@Consumes("application/json")
public class WorkDetailsApi {

	@POST
	public Response create(final WorkDetailsObj workdetailsobj) {
		//TODO: process the given workdetailsobj 
		//you may want to use the following return statement, assuming that WorkDetailsObj#getId() or a similar method 
		//would provide the identifier to retrieve the created WorkDetailsObj resource:
		//return Response.created(UriBuilder.fromResource(WorkDetailsApi.class).path(String.valueOf(workdetailsobj.getId())).build()).build();
		return Response.created(null).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the workdetailsobj 
		WorkDetailsObj workdetailsobj = null;
		if (workdetailsobj == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(workdetailsobj).build();
	}

	@GET
	public List<WorkDetailsObj> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the workdetailsobjs 
		final List<WorkDetailsObj> workdetailsobjs = null;
		return workdetailsobjs;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final WorkDetailsObj workdetailsobj) {
		//TODO: process the given workdetailsobj 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the workdetailsobj matching by the given id 
		return Response.noContent().build();
	}

}
