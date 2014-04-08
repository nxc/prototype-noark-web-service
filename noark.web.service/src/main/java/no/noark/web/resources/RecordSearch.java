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

import no.noark.model.RecordBean;
import no.noark.web.helpers.QueryHelper;
import no.noark.web.helpers.ResponseHelper;

@Path("records")
public class RecordSearch {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response recordSearch(@Context UriInfo uriInfo,
			@QueryParam("search") String search) {

		List<RecordBean> records = new ArrayList<RecordBean>();

		Iterator<RecordBean> iter = QueryHelper.executeQuery(RecordBean.class,
				search);

		while (iter.hasNext()) {
			records.add(iter.next());
		}

		return ResponseHelper.generateResponse(uriInfo, records, true);
	}

}
