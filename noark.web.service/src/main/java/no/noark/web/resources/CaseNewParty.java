package no.noark.web.resources;

import java.util.HashSet;

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
import no.noark.model.CaseBean;
import no.noark.model.Link;
import no.noark.model.PartyBean;
import no.noark.templates.TemplateParams;
import no.noark.templates.Templates;
import no.noark.web.helpers.ResponseHelper;

@Path("cases/{systemId}/parties/new")
public class CaseNewParty {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response caseNewPartyUnsupported(@Context UriInfo uriInfo) {

		String output = Templates.get().produce(
				"case-new-party.xml",
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
	public Response caseNewParty(@Context UriInfo uriInfo,
			@PathParam("systemId") String systemId, PartyBean party) {

		// get the case
		CaseBean sak = Database.get().findBySystemId(systemId, CaseBean.class);

		if (party.getSystemId() != null) {
			party = Database.get().findBySystemId(party.getSystemId(),
					PartyBean.class);
		}

		// db4o workaround
		sak.setRefCaseParties(new HashSet<PartyBean>(sak.getRefCaseParties()));

		sak.getRefCaseParties().add(party);
		party.getRefCases().add(sak);

		Database.get().save(sak, party);

		Link link = new Link("self", uriInfo.getBaseUri() + "parties/"
				+ party.getSystemId());

		return ResponseHelper.generateResponse(uriInfo, link, false);
	}

}
