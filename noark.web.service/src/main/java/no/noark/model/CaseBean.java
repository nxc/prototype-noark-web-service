package no.noark.model;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({ "refCaseParties", "refRecords" })
public class CaseBean extends BaseBean {

	/** M003 - mappeID (xs:string) */
	protected String caseFileId;

	/** M020 - tittel (xs:string) */
	protected String caseTitle;

	/** M025 - offentligTittel (xs:string) */
	protected String caseOfficialTitle;

	protected Set<PartyBean> refCaseParties = new HashSet<PartyBean>();

	protected Set<RecordBean> refRecords = new HashSet<RecordBean>();

	public String getCaseFileId() {
		return caseFileId;
	}

	public void setCaseFileId(String caseFileId) {
		this.caseFileId = caseFileId;
	}

	public String getCaseTitle() {
		return caseTitle;
	}

	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}

	public String getCaseOfficialTitle() {
		return caseOfficialTitle;
	}

	public void setCaseOfficialTitle(String caseOfficialTitle) {
		this.caseOfficialTitle = caseOfficialTitle;
	}

	public Set<PartyBean> getRefCaseParties() {
		return refCaseParties;
	}

	public void setRefCaseParties(Set<PartyBean> refCaseParties) {
		this.refCaseParties = refCaseParties;
	}

	public Set<RecordBean> getRefRecords() {
		return refRecords;
	}

	public void setRefRecords(Set<RecordBean> refRecords) {
		this.refRecords = refRecords;
	}

}
