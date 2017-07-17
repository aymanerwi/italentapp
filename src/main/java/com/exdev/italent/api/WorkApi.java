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

import com.exdev.italent.obj.WorkObj;
import com.exdev.italent.service.OwnerService;
import com.exdev.italent.service.WorkService;

@RequestScoped
@Path("/work")
@Produces("application/json")
@Consumes("application/json")
public class WorkApi {

	@POST
	public Response create(final WorkObj workobj) {
		
		WorkService service = new WorkService();
		service.createWork(workobj);
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
	@Path("/owner/{ownerid}")
	public List<WorkObj> listAll(@PathParam("ownerid") int ownerid,@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		WorkService service = new WorkService();
		final List<WorkObj> workobjs = service.listWorks(ownerid, startPosition, maxResult);
		service.close();
		return workobjs;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") int id, final WorkObj workobj) {
		WorkService service = new WorkService();
		service.updateWork(id, workobj);
		service.close();
		return Response.noContent().build();
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
