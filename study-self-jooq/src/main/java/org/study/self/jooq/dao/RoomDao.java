package org.study.self.jooq.dao;

import static org.study.self.jooq.db.Tables.ROOM;
import static org.study.self.jooq.db.Tables.USER;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Param;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.study.self.jooq.db.tables.pojos.Room;
import org.study.self.jooq.db.tables.records.RoomRecord;
import org.study.self.jooq.model.RoomModel;
import org.study.self.jooq.model.UserModel;

@Repository
public class RoomDao extends DAOImpl<RoomRecord, RoomModel, Integer> {

	@Autowired
	private DSLContext dslContext;
	
	@PersistenceContext
	private EntityManager entityManager; 
	
	protected RoomDao(Configuration configuration) {
		super(ROOM, RoomModel.class, configuration);
	}

	@Override
	protected Integer getId(RoomModel object) {
		return object.getId();
	}

	public List<RoomModel> list() {
		List<RoomModel> list = dslContext.select().from(ROOM)
				.fetch().map(record ->{
					RoomModel model = record.into(RoomModel.class);
					model.setUsers(dslContext.select().from(USER).where(USER.ROOM_ID.eq(model.getId())).fetchInto(UserModel.class));
					return model;
				});
		System.out.println(list);
		
		dslContext.select().from(ROOM)
				.join(USER).on(ROOM.ID.eq(USER.ROOM_ID))
				.fetch().map(record->{
					System.out.println(record.into(UserModel.class) + "," + record.into(RoomModel.class));
					return record;
				});
		return null;
	}
	
	@SuppressWarnings("unchecked")
	static List<Object[]> nativeQuery(EntityManager em, org.jooq.Query query) {

	    // Extract the SQL statement from the jOOQ query:
	    Query result = em.createNativeQuery(query.getSQL());

	    // Extract the bind values from the jOOQ query:
	    int i = 0;
	    for (Param<?> param : query.getParams().values()) {
	        result.setParameter(i + 1, convertToDatabaseType(param));
	        i++;
	    }

	    return result.getResultList();
	}

	static <T> Object convertToDatabaseType(Param<T> param) {
	    return param.getBinding().converter().to(param.getValue());
	}
	
}
