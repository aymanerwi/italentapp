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

import com.exdev.italent.obj.OwnerObj;
import com.exdev.italent.service.OwnerService;

@RequestScoped
@Path("/owner")
@Produces("application/json")
@Consumes("application/json")
public class OwnerApi {

	@POST
	public Response create(OwnerObj ownerobj) {
		OwnerService service = new OwnerService();
		service.createOwner(ownerobj);
		ownerobj = service.getOwner(ownerobj.getId());
		service.close();
		return Response.created(UriBuilder.fromResource(OwnerApi.class).path(String.valueOf(ownerobj.getId())).build())
				.entity(ownerobj).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final int id) {
		OwnerService service = new OwnerService();
		OwnerObj ownerobj = service.getOwner(id);
		service.close();
		if (ownerobj == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(ownerobj).build();
	}

	@GET
	public List<OwnerObj> listAll(@QueryParam("start") final int startPosition,
			@QueryParam("max") final int maxResult) {
		OwnerService service = new OwnerService();
		final List<OwnerObj> ownerobjs = service.listOwners(startPosition, maxResult);
		service.close();
		return ownerobjs;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") int id, OwnerObj ownerobj) {
		OwnerService service = new OwnerService();
		service.updateOwner(id, ownerobj);
		ownerobj = service.getOwner(ownerobj.getId());
		service.close();
		return Response.ok(ownerobj).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id) {
		OwnerService service = new OwnerService();
		service.deleteOwner(id);
		service.close();
		return Response.noContent().build();
	}

}
