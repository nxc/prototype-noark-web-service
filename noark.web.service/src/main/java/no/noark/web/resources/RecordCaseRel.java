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

import no.noark.model.RecordBean;
import no.noark.web.helpers.QueryHelper;
import no.noark.web.helpers.ResponseHelper;

@Path("records/{systemId}/case")
public class RecordCaseRel {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response recordCaseRel(@Context UriInfo uriInfo,
			@PathParam("systemId") String systemId) {

		Iterator<RecordBean> iter = QueryHelper.executeQuery(RecordBean.class,
				"systemId==" + systemId);

		return ResponseHelper.generateResponse(uriInfo, iter.next()
				.getRefCase(), true);
	}

}
