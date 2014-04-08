package no.noark.model;

import java.util.List;

public class BaseBean {

	/** M001 - systemID (xs:string) */
	protected String systemId;

	protected transient List<String> screenedFields;

	protected transient List<String> writableFields;

	protected transient List<Link> links;

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public List<String> getScreenedFields() {
		return screenedFields;
	}

	public void setScreenedFields(List<String> screenedFields) {
		this.screenedFields = screenedFields;
	}

	public List<String> getWritableFields() {
		return writableFields;
	}

	public void setWritableFields(List<String> writableFields) {
		this.writableFields = writableFields;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

}
