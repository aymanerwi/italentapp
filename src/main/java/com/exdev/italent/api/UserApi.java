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

import com.exdev.italent.obj.UserObj;
import com.exdev.italent.service.UserService;
import com.exdev.italent.service.WorkService;

@RequestScoped
@Path("/user")
@Produces("application/json")
@Consumes("application/json")
public class UserApi {

	@POST
	public Response create(UserObj userobj) {
		UserService service = new UserService();
		service.registerUser(userobj);
		userobj = service.getUser(userobj.getId());
		service.close();
		return Response.created(UriBuilder.fromResource(OwnerApi.class).path(String.valueOf(userobj.getId())).build())
				.entity(userobj).build();
	}
	
	@PUT
	public Response confirm(UserObj userobj) {
		UserService service = new UserService();
		userobj = service.confirmSms(userobj);
		service.close();
		return Response.created(UriBuilder.fromResource(OwnerApi.class).path(String.valueOf(userobj.getId())).build())
				.entity(userobj).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final int id) {
		UserService service = new UserService();
		UserObj loginobj = service.getUser(id);
		service.close();
		if (loginobj == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(loginobj).build();
	}

	@GET
	public List<UserObj> listAll(@QueryParam("start") final int startPosition,
			@QueryParam("max") final int maxResult) {
		UserService service = new UserService(); 
		final List<UserObj> loginobjs = service.listUsers(startPosition, maxResult);
		service.close();
		return loginobjs;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") int id, UserObj userobj) {
		UserService service = new UserService();
		service.updateUser(id,userobj);
		userobj = service.getUser(id);
		service.close();
		return Response.ok(userobj).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id) {
		UserService service = new UserService();
		service.deleteUser(id);
		service.close();
		return Response.noContent().build();
	}

}
