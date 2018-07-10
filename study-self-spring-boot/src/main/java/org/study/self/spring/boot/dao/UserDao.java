package org.study.self.spring.boot.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.jooq.Condition;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SelectField;
import org.jooq.impl.DAOImpl;
import org.jooq.impl.DSL;
import org.jooq.types.UInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.study.self.spring.boot.db.Tables;
import org.study.self.spring.boot.db.tables.records.UserRecord;
import org.study.self.spring.boot.model.Room;
import org.study.self.spring.boot.model.User;

import static org.study.self.spring.boot.db.Tables.*;

@Repository
public class UserDao extends DAOImpl<UserRecord, User, Integer>{

	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private DSLContext dslContext;
	
	protected UserDao(Configuration configuration) {
		super(Tables.USER, User.class, configuration);
	}

	@Override
	protected Integer getId(User object) {
		return object.getId();
	}
	
	public Integer save(User user) {
        if (Objects.isNull(getId(user))) {
            Integer userId = insertAndReturnId(user);
            user.setId(userId);
            return userId;
        }
        update(user);
        return getId(user);
    }
	
	private Integer insertAndReturnId(User user) {
        UserRecord record = dslContext.newRecord(USER, user);
        record.insert();
        return record.getId();
    }
	
	public List<User> list() {
		List<User> courses = dslContext
				.select(USER.fields())
				.select(ROOM.fields())
				.from(Tables.USER)
				.leftJoin(Tables.ROOM)
				.on(Tables.USER.ROOM_ID.eq(Tables.ROOM.ID))
				.fetchInto(User.class);
//				.fetch().map(r->{
//					User user = r.into(User.class);
//					user.setRoom(r.into(Room.class));
//					return user;
//				});
//				.map(record -> {
//			    	  User user = record.into(User.class);
//			    	  user.setRoom(roomDao.findById(user.getRoomId());
//			        return user;
//			      });
		return courses;
	}
	
	public void search() {
		List<User> products = dslContext.select()
			      .from(Tables.USER)
			      .leftJoin(DSL.table(
			          DSL.select()
			            .from(Tables.ROOM)
			        ).as("room")
			      )
			      .on(USER.ID.eq(DSL.field(DSL.name("room", ROOM.ID.getName()), Integer.class))) 
			      .fetch()
			      .map(record -> {
			    	  User product = record.into(User.class); 
			    	  System.out.println(product);
			        return product;
			      });
	}
	
}
