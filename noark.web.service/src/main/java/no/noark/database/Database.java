package no.noark.database;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.UUID;

import no.noark.model.BaseBean;
import no.noark.model.CaseBean;
import no.noark.model.PartyBean;
import no.noark.model.RecordBean;

import org.apache.commons.lang.reflect.FieldUtils;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.io.MemoryStorage;
import com.db4o.query.Query;

public class Database {

	// the only instance of this class
	private static Database instance;

	private ObjectContainer container;

	private Database() {

		EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
		MemoryStorage memory = new MemoryStorage();
		configuration.file().storage(memory);
		container = Db4oEmbedded.openFile(configuration, "database.db4o");
	}

	public static void init() {

		instance = new Database();

		createStructure();
	}

	public static void reinit() {

		get().container.close();

		instance = new Database();
	}

	public static Database get() {
		return instance;
	}

	public Query query() {
		return container.query();
	}

	public void save(BaseBean... objects) {

		for (BaseBean obj : objects) {

			if (obj.getSystemId() == null) {
				obj.setSystemId(UUID.randomUUID().toString());
			}

			container.store(obj);
		}
	}

	public void update(BaseBean updates) {

		try {
			BaseBean original = findBySystemId(updates.getSystemId(), null);

			Field[] fields = original.getClass().getDeclaredFields();

			for (Field field : fields) {

				Object val = FieldUtils.readField(field, updates, true);

				if (val == null || val instanceof Collection) {
					continue;
				}

				FieldUtils.writeDeclaredField(original, field.getName(), val,
						true);
			}

			container.store(original);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}

	@SuppressWarnings("unchecked")
	public <T> T findBySystemId(String systemId, Class<T> clazz) {

		Query query = query();

		if (clazz != null) {
			query.constrain(clazz);
		}
		query.descend("systemId").constrain(systemId);

		return (T) query.execute().iterator().next();
	}

	private static void createStructure() {

		// create objects

		PartyBean party1 = new PartyBean();
		party1.setPartyName("John Doe");
		party1.setPartyPhoneNumber("9332 8994");
		party1.setPartyMailingAddress("Bogstadveien 1, Oslo, Norway");

		PartyBean party2 = new PartyBean();
		party2.setPartyName("Jane Doe");
		party2.setPartyPhoneNumber("3443 7002");
		party2.setPartyMailingAddress("Abels gate 1, Trondheim, Norway");

		CaseBean case1 = new CaseBean();
		case1.setCaseFileId("1955/0001");
		case1.setCaseTitle("Case 1");
		case1.setCaseOfficialTitle("Case 1");

		CaseBean case2 = new CaseBean();
		case2.setCaseFileId("1955/0002");
		case2.setCaseTitle("Case 2");
		case2.setCaseOfficialTitle("Case 2");

		CaseBean case3 = new CaseBean();
		case3.setCaseFileId("1955/0003");
		case3.setCaseTitle("Case 3");
		case3.setCaseOfficialTitle("Case 3");

		RecordBean record1 = new RecordBean();
		record1.setRecordTitle("Record 1");
		record1.setRecordOfficialTitle("Record 1");

		RecordBean record2 = new RecordBean();
		record2.setRecordTitle("Record 2");
		record2.setRecordOfficialTitle("Record 2");

		RecordBean record3 = new RecordBean();
		record3.setRecordTitle("Record 3");
		record3.setRecordOfficialTitle("Record 3");

		RecordBean record4 = new RecordBean();
		record4.setRecordTitle("Record 4");
		record4.setRecordOfficialTitle("Record 4");

		// link the objects

		case1.getRefCaseParties().add(party1);
		party1.getRefCases().add(case1);
		case1.getRefRecords().add(record1);
		record1.setRefCase(case1);

		case2.getRefCaseParties().add(party2);
		party2.getRefCases().add(case2);
		case2.getRefRecords().add(record2);
		record2.setRefCase(case2);

		case3.getRefCaseParties().add(party1);
		party1.getRefCases().add(case3);
		case3.getRefCaseParties().add(party2);
		party2.getRefCases().add(case3);
		case3.getRefRecords().add(record3);
		record3.setRefCase(case3);
		case3.getRefRecords().add(record4);
		record4.setRefCase(case3);

		// save the objects
		get().save(party1, party2, case1, case2, case3, record1, record2,
				record3, record4);
	}

}
