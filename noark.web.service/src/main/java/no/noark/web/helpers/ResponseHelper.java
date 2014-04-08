package no.noark.web.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import no.noark.model.BaseBean;
import no.noark.model.CaseBean;
import no.noark.model.Link;
import no.noark.model.PartyBean;
import no.noark.model.RecordBean;

import org.codehaus.jackson.map.ObjectMapper;

public class ResponseHelper {

	public static Response generateResponse(UriInfo uriInfo, Object obj,
			boolean enhance) {

		if (enhance) {
			if (obj instanceof Collection) {
				enhanceObjectCollection(uriInfo, (Collection<?>) obj);
			} else if (obj instanceof BaseBean) {
				enhanceObject(uriInfo, (BaseBean) obj);
			}
		}

		try {
			final ObjectMapper mapper = new ObjectMapper();

			String output = mapper.writeValueAsString(obj);

			return Response.ok(output, MediaType.APPLICATION_JSON).build();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		throw new RuntimeException();
	}

	private static void enhanceObjectCollection(UriInfo uriInfo,
			Collection<?> collection) {

		Iterator<?> iter = collection.iterator();
		while (iter.hasNext()) {
			enhanceObject(uriInfo, (BaseBean) iter.next());
		}

	}

	private static void enhanceObject(UriInfo uriInfo, BaseBean obj) {

		obj.setScreenedFields(new ArrayList<String>());
		obj.setWritableFields(new ArrayList<String>());
		obj.setLinks(new ArrayList<Link>());

		if (obj instanceof CaseBean) {

			obj.getWritableFields().add("caseFileId");
			obj.getWritableFields().add("caseTitle");
			obj.getWritableFields().add("caseOfficialTitle");

			obj.getLinks().add(
					new Link("self", uriInfo.getBaseUri() + "cases/"
							+ ((CaseBean) obj).getSystemId()));

			obj.getLinks().add(
					new Link("update", uriInfo.getBaseUri() + "cases/"
							+ ((CaseBean) obj).getSystemId() + "/update"));

			obj.getLinks().add(
					new Link("get-parties", uriInfo.getBaseUri() + "cases/"
							+ ((CaseBean) obj).getSystemId() + "/parties"));
			obj.getLinks().add(
					new Link("new-party", uriInfo.getBaseUri() + "cases/"
							+ ((CaseBean) obj).getSystemId() + "/parties/new"));

			obj.getLinks().add(
					new Link("get-records", uriInfo.getBaseUri() + "cases/"
							+ ((CaseBean) obj).getSystemId() + "/records"));
			obj.getLinks().add(
					new Link("new-record", uriInfo.getBaseUri() + "cases/"
							+ ((CaseBean) obj).getSystemId() + "/records/new"));

		} else if (obj instanceof PartyBean) {

			obj.getWritableFields().add("partyName");
			obj.getWritableFields().add("partyMailingAddress");
			obj.getWritableFields().add("partyPhoneNumber");

			obj.getLinks().add(
					new Link("update", uriInfo.getBaseUri() + "parties/"
							+ ((PartyBean) obj).getSystemId() + "/update"));

			obj.getLinks().add(
					new Link("self", uriInfo.getBaseUri() + "parties/"
							+ ((PartyBean) obj).getSystemId()));
			obj.getLinks().add(
					new Link("get-cases", uriInfo.getBaseUri() + "parties/"
							+ ((PartyBean) obj).getSystemId() + "/cases"));

		} else if (obj instanceof RecordBean) {

			obj.getWritableFields().add("recordTitle");
			obj.getWritableFields().add("recordOfficialTitle");

			obj.getLinks().add(
					new Link("update", uriInfo.getBaseUri() + "records/"
							+ ((RecordBean) obj).getSystemId() + "/update"));

			obj.getLinks().add(
					new Link("self", uriInfo.getBaseUri() + "records/"
							+ ((RecordBean) obj).getSystemId()));
			obj.getLinks().add(
					new Link("get-case", uriInfo.getBaseUri() + "records/"
							+ ((RecordBean) obj).getSystemId() + "/case"));

		}

	}

}
