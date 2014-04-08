package no.noark.web.helpers;

import java.util.Iterator;

import no.noark.database.Database;
import no.noark.model.CaseBean;
import no.noark.model.PartyBean;
import no.noark.model.RecordBean;

import com.db4o.query.Query;

public class QueryHelper {

	@SuppressWarnings("unchecked")
	public static <T> Iterator<T> executeQuery(Class<T> clazz, String search) {

		Query query = Database.get().query();
		query.constrain(clazz);

		if (search != null) {

			String[] constraints = search.split(";");

			for (String constraint : constraints) {

				String[] operands = constraint.split("==");

				String left = operands[0];
				String right = operands[1];

				String rightNoWildcards = right;

				if (rightNoWildcards.lastIndexOf("*") == rightNoWildcards
						.length() - 1) {
					rightNoWildcards = rightNoWildcards.substring(0,
							rightNoWildcards.length() - 1);
				}

				if (clazz.equals(CaseBean.class)) {

					if (left.startsWith("case") || left.equals("systemId")) {

						if (right.contains("*")) {
							query.descend(left).constrain(rightNoWildcards)
									.like();
						} else {
							query.descend(left).constrain(right);
						}

					} else if (left.startsWith("party")) {

						if (right.contains("*")) {
							query.descend("refCaseParties").descend(left)
									.constrain(rightNoWildcards).like();
						} else {
							query.descend("refCaseParties").descend(left)
									.constrain(right);
						}

					} else if (left.startsWith("record")) {

						if (right.contains("*")) {
							query.descend("refRecords").descend(left)
									.constrain(rightNoWildcards).like();
						} else {
							query.descend("refRecords").descend(left)
									.constrain(right);
						}

					}
				} else if (clazz.equals(PartyBean.class)) {

					if (left.startsWith("party") || left.equals("systemId")) {

						if (right.contains("*")) {
							query.descend(left).constrain(rightNoWildcards)
									.like();
						} else {
							query.descend(left).constrain(right);
						}

					}

				} else if (clazz.equals(RecordBean.class)) {

					if (left.startsWith("record") || left.equals("systemId")) {

						if (right.contains("*")) {
							query.descend(left).constrain(rightNoWildcards)
									.like();
						} else {
							query.descend(left).constrain(right);
						}

					}

				}

			}

		}

		Iterator<T> iter = (Iterator<T>) query.execute().iterator();

		return iter;
	}

}
