package org.study.self.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.self.spring.boot.dao.RoomDao;
import org.study.self.spring.boot.model.Room;

@Service
public class RoomService {
	@Autowired
	private RoomDao roomDao;
	
	public List<Room> list(){
		return roomDao.list();
	}
	
	public void save(Room room){
		roomDao.save(room);
	}
	
	public Room getById(Integer id) {
		return roomDao.findById(id);
	}
}
