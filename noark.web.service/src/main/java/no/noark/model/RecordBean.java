package no.noark.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({ "refCase" })
public class RecordBean extends BaseBean {

	/** M020 - tittel (xs:string) */
	protected String recordTitle;

	/** M025 - offentligTittel (xs:string) */
	protected String recordOfficialTitle;

	protected CaseBean refCase;

	public String getRecordTitle() {
		return recordTitle;
	}

	public void setRecordTitle(String recordTitle) {
		this.recordTitle = recordTitle;
	}

	public String getRecordOfficialTitle() {
		return recordOfficialTitle;
	}

	public void setRecordOfficialTitle(String recordOfficialTitle) {
		this.recordOfficialTitle = recordOfficialTitle;
	}

	public CaseBean getRefCase() {
		return refCase;
	}

	public void setRefCase(CaseBean refCase) {
		this.refCase = refCase;
	}

}
