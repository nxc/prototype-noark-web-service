package no.noark.web.resources;

import java.io.IOException;

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
import no.noark.model.CaseBean;
import no.noark.model.Link;
import no.noark.templates.TemplateParams;
import no.noark.templates.Templates;
import no.noark.web.helpers.ResponseHelper;

@Path("cases/new")
public class CaseNew {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response caseNewUnsupported(@Context UriInfo uriInfo)
			throws IOException {

		String output = Templates.get().produce(
				"case-new.xml",
				new TemplateParams()
						.add("url", uriInfo.getAbsolutePath().toString())
						.add("method", "PUT").add("caseFileId", "")
						.add("caseTitle", "").add("caseOfficialTitle", ""));

		return Response.status(405).entity(output).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response caseNew(@Context UriInfo uriInfo, CaseBean sak) {

		Database.get().save(sak);

		Link link = new Link("self", uriInfo.getBaseUri() + "cases/"
				+ sak.getSystemId());

		return ResponseHelper.generateResponse(uriInfo, link, false);
	}

}
