package no.noark.model;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({ "refCases" })
public class PartyBean extends BaseBean {

	/** M302 sakspartNavn, M400 korrespondansepartNavn **/
	protected String partyName;

	/** M406 postadresse **/
	protected String partyMailingAddress;

	/** M411 telefonnummer **/
	protected String partyPhoneNumber;

	protected Set<CaseBean> refCases = new HashSet<CaseBean>();

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyMailingAddress() {
		return partyMailingAddress;
	}

	public void setPartyMailingAddress(String partyMailingAddress) {
		this.partyMailingAddress = partyMailingAddress;
	}

	public String getPartyPhoneNumber() {
		return partyPhoneNumber;
	}

	public void setPartyPhoneNumber(String partyPhoneNumber) {
		this.partyPhoneNumber = partyPhoneNumber;
	}

	public Set<CaseBean> getRefCases() {
		return refCases;
	}

	public void setRefCases(Set<CaseBean> refCases) {
		this.refCases = refCases;
	}

}
