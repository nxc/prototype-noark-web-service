package no.noark.web.resources;

import java.util.Iterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import no.noark.model.CaseBean;
import no.noark.web.helpers.QueryHelper;
import no.noark.web.helpers.ResponseHelper;

@Path("cases/{systemId}/records")
public class CaseRecordRel {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response caseRecordRel(@Context UriInfo uriInfo,
			@PathParam("systemId") String systemId) {

		Iterator<CaseBean> iter = QueryHelper.executeQuery(CaseBean.class,
				"systemId==" + systemId);

		return ResponseHelper.generateResponse(uriInfo, iter.next()
				.getRefRecords(), true);
	}
}
