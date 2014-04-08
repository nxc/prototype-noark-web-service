package no.noark.templates;

import java.util.HashMap;
import java.util.Map;

public class TemplateParams {

	private Map<String, String> params = new HashMap<String, String>();

	public TemplateParams add(String paramName, String paramValue) {

		params.put(paramName, paramValue);

		return this;
	}

	public Map<String, String> getParams() {
		return params;
	}

}