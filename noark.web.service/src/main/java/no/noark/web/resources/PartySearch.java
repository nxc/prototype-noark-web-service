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

import no.noark.model.PartyBean;
import no.noark.web.helpers.QueryHelper;
import no.noark.web.helpers.ResponseHelper;

@Path("parties")
public class PartySearch {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response partySearch(@Context UriInfo uriInfo,
			@QueryParam("search") String search) {

		List<PartyBean> parties = new ArrayList<PartyBean>();

		Iterator<PartyBean> iter = QueryHelper.executeQuery(PartyBean.class,
				search);

		while (iter.hasNext()) {
			parties.add(iter.next());
		}

		return ResponseHelper.generateResponse(uriInfo, parties, true);

	}

}
