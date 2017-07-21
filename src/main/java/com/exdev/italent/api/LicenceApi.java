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

import com.exdev.italent.obj.LicenceObj;
import com.exdev.italent.service.LicenceService;
import com.exdev.italent.service.OwnerService;

@RequestScoped
@Path("/lic")
@Produces("application/json")
@Consumes("application/json")
public class LicenceApi {

	@POST
	public Response create(LicenceObj licenceobj) {
		LicenceService service = new LicenceService();
		service.createLicence(licenceobj);
		licenceobj = service.getLicence(licenceobj.getId());
		service.close();
		return Response.created(UriBuilder.fromResource(OwnerApi.class).path(String.valueOf(licenceobj.getId())).build())
				.entity(licenceobj).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final int id) {
		LicenceService service = new LicenceService();
		LicenceObj licenceobj = service.getLicence(id);
		service.close();
		if (licenceobj == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(licenceobj).build();
	}

	@GET
	@Path("/owner/{ownerid}")
	public List<LicenceObj> listAll(@PathParam("ownerid") int ownerid,@QueryParam("start") final int startPosition,
			@QueryParam("max") final int maxResult) {
		
		LicenceService service = new LicenceService();
		final List<LicenceObj> licenceobjs = service.listLicences(ownerid, startPosition, maxResult);
		service.close();
		return licenceobjs;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") int id, LicenceObj licenceobj) {
		LicenceService service = new LicenceService();
		service.updateLicence(id, licenceobj);
		licenceobj = service.getLicence(id);
		service.close();
		return Response.ok(licenceobj).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id) {
		LicenceService service = new LicenceService();
		service.deleteLicence(id);
		service.close();
		return Response.noContent().build();
	}

}
