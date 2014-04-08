package no.noark.web.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

@Path("parties/{systemId}/update")
public class PartyUpdate {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response partyUpdateUnsupported(@Context UriInfo uriInfo,
			@PathParam("systemId") String systemId) {

		PartyBean party = Database.get().findBySystemId(systemId,
				PartyBean.class);

		String output = Templates.get().produce(
				"party-update.xml",
				new TemplateParams()
						.add("url", uriInfo.getAbsolutePath().toString())
						.add("method", "PUT")
						.add("partyName", party.getPartyName())
						.add("partyMailingAddress",
								party.getPartyMailingAddress())
						.add("partyPhoneNumber", party.getPartyPhoneNumber()));

		return Response.status(405).entity(output).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response partyUpdate(@Context UriInfo uriInfo,
			@PathParam("systemId") String systemId, PartyBean updates) {

		updates.setSystemId(systemId);

		Database.get().update(updates);

		Link link = new Link("self", uriInfo.getBaseUri() + "parties/"
				+ updates.getSystemId());

		return ResponseHelper.generateResponse(uriInfo, link, false);
	}

}
