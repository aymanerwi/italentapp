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

import com.exdev.italent.obj.CommentObj;
import com.exdev.italent.service.BaseService;
import com.exdev.italent.service.CommentService;

@RequestScoped
@Path("/comment")
@Produces("application/json")
@Consumes("application/json")
public class CommentApi {

	@POST
	public Response create(CommentObj commentobj) {
		CommentService service = new CommentService();
		service.createComment(commentobj);
		commentobj = service.getComment(commentobj.getId());
		service.close();
		return Response
				.created(UriBuilder.fromResource(OwnerApi.class).path(String.valueOf(commentobj.getId())).build())
				.entity(commentobj).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final int id) {
		CommentService service = new CommentService();
		CommentObj commentobj = service.getComment(id);
		service.close();
		if (commentobj == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(commentobj).build();
	}

	@GET
	@Path("/ad/{adid}")
	public List<CommentObj> listAll(@PathParam("adid") int adid, @QueryParam("start") final int startPosition,
			@QueryParam("max") final int maxResult) {
		BaseService service = new CommentService();
		final List<CommentObj> commentobjs = service.listComments(adid, startPosition, maxResult);
		service.close();
		return commentobjs;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") int id, CommentObj commentobj) {
		CommentService service = new CommentService();
		service.updateComment(id, commentobj);
		commentobj = service.getComment(id);
		service.close();
		return Response.ok(commentobj).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id) {
		CommentService service = new CommentService();
		service.deleteComment(id);
		service.close();
		return Response.noContent().build();
	}

}
