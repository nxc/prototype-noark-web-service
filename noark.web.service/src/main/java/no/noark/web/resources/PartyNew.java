package no.noark.web.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import no.noark.database.Database;
import no.noark.model.Link;
import no.noark.model.PartyBean;
import no.noark.templates.TemplateParams;
import no.noark.templates.Templates;
import no.noark.web.helpers.ResponseHelper;

@Path("parties/new")
public class PartyNew {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response partyNewUnsupported(@Context UriInfo uriInfo) {

		String output = Templates.get().produce(
				"party-new.xml",
				new TemplateParams()
						.add("url", uriInfo.getAbsolutePath().toString())
						.add("method", "PUT").add("partyName", "")
						.add("partyMailingAddress", "")
						.add("partyPhoneNumber", ""));

		return Response.status(405).entity(output).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response partyNew(@Context UriInfo uriInfo, PartyBean party) {

		Database.get().save(party);

		Link link = new Link("self", uriInfo.getBaseUri() + "parties/"
				+ party.getSystemId());

		return ResponseHelper.generateResponse(uriInfo, link, false);
	}

}
