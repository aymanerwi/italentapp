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

import com.exdev.italent.obj.AdvertisementObj;
import com.exdev.italent.service.AdvertisementService;

@RequestScoped
@Path("/ad")
@Produces("application/json")
@Consumes("application/json")
public class AdvertisementApi {

	@POST
	public Response create(AdvertisementObj advertisementobj) {
		AdvertisementService service = new AdvertisementService();
		service.createAdvertisement(advertisementobj);
		advertisementobj = service.getAdvertisement(advertisementobj.getId());
		service.close();
		return Response.created(UriBuilder.fromResource(OwnerApi.class).path(String.valueOf(advertisementobj.getId())).build())
				.entity(advertisementobj).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final int id) {
		AdvertisementService service = new AdvertisementService();
		AdvertisementObj advertisementobj = service.getAdvertisement(id);
		service.close();
		if (advertisementobj == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(advertisementobj).build();
		
	}

	@GET
	public List<AdvertisementObj> listAll(@QueryParam("start") final int startPosition,
			@QueryParam("max") final int maxResult) {
		AdvertisementService service = new AdvertisementService();
		final List<AdvertisementObj> advertisementobjs = service.listAdvertisements(startPosition, maxResult);
		return advertisementobjs;
	}
	
	@GET
	@Path("/owner/{ownerid:[0-9][0-9]*}")
	public List<AdvertisementObj> listAll(@PathParam("ownerid") int ownerid,@QueryParam("start") final int startPosition,
			@QueryParam("max") final int maxResult) {
		AdvertisementService service = new AdvertisementService();
		final List<AdvertisementObj> advertisementobjs = service.listAdvertisements(ownerid,startPosition, maxResult);
		return advertisementobjs;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") int id, AdvertisementObj advertisementobj) {
		AdvertisementService service = new AdvertisementService();
		service.updateAdvertisement(id, advertisementobj);
		advertisementobj = service.getAdvertisement(id);
		service.close();
		return Response.ok(advertisementobj).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id) {
		AdvertisementService service = new AdvertisementService();
		service.deleteAdvertisement(id);
		return Response.noContent().build();
	}

}
