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
import javax.ws.rs.core.UriBuilder;

import com.exdev.italent.obj.WorkObj;
import com.exdev.italent.service.WorkService;

@RequestScoped
@Path("/work")
@Produces("application/json")
@Consumes("application/json")
public class WorkApi {

	@POST
	public Response create(WorkObj workobj) {
		
		WorkService service = new WorkService();
		service.createWork(workobj);
		workobj = service.getWork(workobj.getId());
		service.close();
		return Response.created(UriBuilder.fromResource(OwnerApi.class).path(String.valueOf(workobj.getId())).build())
				.entity(workobj).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final int id) {
		WorkService service = new WorkService(); 
		WorkObj workobj = service.getWork(id);
		service.close();
		if (workobj == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(workobj).build();
	}

	@GET
	@Path("/ad/{adid}")
	public List<WorkObj> listAll(@PathParam("adid") int adid,@QueryParam("start") final int startPosition,
			@QueryParam("max") final int maxResult) {
		WorkService service = new WorkService();
		final List<WorkObj> workobjs = service.listWorks(adid, startPosition, maxResult);
		service.close();
		return workobjs;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") int id, WorkObj workobj) {
		WorkService service = new WorkService();
		service.updateWork(id, workobj);
		workobj = service.getWork(id);
		service.close();
		return Response.ok().entity(workobj).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id) {
		WorkService service = new WorkService();
		service.deleteWork(id);
		service.close();
		return Response.noContent().build();
	}

}
