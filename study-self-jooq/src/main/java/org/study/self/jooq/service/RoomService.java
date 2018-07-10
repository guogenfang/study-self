package org.study.self.jooq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.self.jooq.dao.RoomDao;

@Service
public class RoomService {
	
	@Autowired
	private RoomDao roomDao;
	
	public void list() {
		roomDao.list();
	}
}
