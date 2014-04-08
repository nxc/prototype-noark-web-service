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

import no.noark.model.PartyBean;
import no.noark.web.helpers.QueryHelper;
import no.noark.web.helpers.ResponseHelper;

@Path("parties/{systemId}")
public class PartySelf {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response partySelf(@Context UriInfo uriInfo,
			@PathParam("systemId") String systemId) {

		Iterator<PartyBean> iter = QueryHelper.executeQuery(PartyBean.class,
				"systemId==" + systemId);

		return ResponseHelper.generateResponse(uriInfo, iter.next(), true);
	}

}
