package no.noark.web.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import no.noark.model.Link;
import no.noark.web.helpers.ResponseHelper;

@Path("")
public class RootResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response root(@Context UriInfo uriInfo) {

		List<Link> links = new ArrayList<Link>();

		links.add(new Link("get-cases", uriInfo.getBaseUri() + "cases"));
		links.add(new Link("get-parties", uriInfo.getBaseUri() + "parties"));
		links.add(new Link("get-records", uriInfo.getBaseUri() + "records"));
		links.add(new Link("new-case", uriInfo.getBaseUri() + "cases/new"));
		links.add(new Link("new-party", uriInfo.getBaseUri() + "parties/new"));

		return ResponseHelper.generateResponse(uriInfo, links, false);
	}

}
