package org.study.self.spring.boot.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.jooq.Condition;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DAOImpl;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.study.self.spring.boot.db.Tables;
import org.study.self.spring.boot.db.tables.records.RoomRecord;
import org.study.self.spring.boot.model.Room;

import static org.study.self.spring.boot.db.Tables.*;

@Repository
public class RoomDao extends DAOImpl<RoomRecord, Room, Integer>{

	@Autowired
	private DSLContext dslContext;
	
	protected RoomDao(Configuration configuration) {
		super(Tables.ROOM, Room.class, configuration);
	}

	@Override
	protected Integer getId(Room object) {
		return object.getId();
	}
	
	public Integer save(Room room) {
        if (Objects.isNull(getId(room))) {
            Integer roomId = insertAndReturnId(room);
            room.setId(roomId);
            return roomId;
        }
        update(room);
        return getId(room);
    }
	
	private Integer insertAndReturnId(Room room) {
        RoomRecord record = dslContext.newRecord(ROOM, room);
        record.insert();
        return record.getId();
    }
	
	public List<Room> list() {
		List<Condition> conditions =new ArrayList<>();
		
		List<Room> courses = dslContext.select().from(Tables.ROOM)
				.join(Tables.USER).on(Tables.ROOM.ID.eq(Tables.USER.ROOM_ID))
				.where(conditions)
				.fetchInto(Room.class);
		return courses;
	}
	
	public void getRoom() {
		List<Condition> conditions =new ArrayList<>();
		dslContext.select().from(Tables.ROOM)
				.join(Tables.USER).on(Tables.ROOM.ID.eq(Tables.USER.ROOM_ID))
				.where(conditions)
				.fetchInto(Room.class).stream().forEach(r -> {
					System.out.println(r);
				});
	}
}
