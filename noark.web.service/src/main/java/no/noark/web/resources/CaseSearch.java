package no.noark.web.resources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import no.noark.model.CaseBean;
import no.noark.web.helpers.QueryHelper;
import no.noark.web.helpers.ResponseHelper;

@Path("cases")
public class CaseSearch {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response caseSearch(@Context UriInfo uriInfo,
			@QueryParam("search") String search) {

		List<CaseBean> cases = new ArrayList<CaseBean>();

		Iterator<CaseBean> iter = QueryHelper.executeQuery(CaseBean.class,
				search);

		while (iter.hasNext()) {
			cases.add(iter.next());
		}

		return ResponseHelper.generateResponse(uriInfo, cases, true);
	}

}
