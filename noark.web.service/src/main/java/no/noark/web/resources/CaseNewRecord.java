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
import no.noark.model.RecordBean;
import no.noark.templates.TemplateParams;
import no.noark.templates.Templates;
import no.noark.web.helpers.ResponseHelper;

@Path("cases/{systemId}/records/new")
public class CaseNewRecord {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response caseNewRecordUnsupported(@Context UriInfo uriInfo) {

		String output = Templates.get().produce(
				"case-new-record.xml",
				new TemplateParams()
						.add("url", uriInfo.getAbsolutePath().toString())
						.add("method", "PUT").add("recordTitle", "")
						.add("recordOfficialTitle", ""));

		return Response.status(405).entity(output).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response caseNewRecord(@Context UriInfo uriInfo,
			@PathParam("systemId") String systemId, RecordBean record) {

		CaseBean sak = Database.get().findBySystemId(systemId, CaseBean.class);

		// db4o workaround
		sak.setRefRecords(new HashSet<RecordBean>(sak.getRefRecords()));

		sak.getRefRecords().add(record);
		record.setRefCase(sak);

		Database.get().save(sak, record);

		Link link = new Link("self", uriInfo.getBaseUri() + "records/"
				+ record.getSystemId());

		return ResponseHelper.generateResponse(uriInfo, link, false);
	}

}
