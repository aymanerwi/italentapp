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
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;

import com.exdev.italent.obj.WorkDetailsObj;
import com.exdev.italent.service.OwnerService;
import com.exdev.italent.service.WorkDetailsService;

@RequestScoped
@Path("/workdetails")
@Produces("application/json")
@Consumes("application/json")
public class WorkDetailsApi {

	@POST
	public Response create(final WorkDetailsObj workdetailsobj) {
		WorkDetailsService service = new WorkDetailsService();
		service.createWorkDetails(workdetailsobj);
		service.close();
		return Response.created(UriBuilder.fromResource(OwnerApi.class).path(String.valueOf(workdetailsobj.getId())).build())
				.entity(workdetailsobj).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final int id) {
		WorkDetailsService service = new WorkDetailsService();
		WorkDetailsObj workdetailsobj = service.getWorkDetails(id);
		service.close();
		if (workdetailsobj == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(workdetailsobj).build();
	}

	@GET
	@Path("/work/{workid}")
	public List<WorkDetailsObj> listAll(@PathParam("workid") int workid,@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		WorkDetailsService service = new WorkDetailsService();
		final List<WorkDetailsObj> workdetailsobjs = service.listWorkDetails(workid, startPosition, maxResult);
		service.close();
		return workdetailsobjs;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") int id, final WorkDetailsObj workdetailsobj) {
		WorkDetailsService service = new WorkDetailsService();
		service.updateWorkDetails(id, workdetailsobj);
		service.close();
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id) {
		WorkDetailsService service = new WorkDetailsService();
		service.deleteWorkDetails(id);
		service.close();
		return Response.noContent().build();
	}

}
