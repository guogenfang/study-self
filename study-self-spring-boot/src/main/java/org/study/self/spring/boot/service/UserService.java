package org.study.self.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.self.spring.boot.dao.UserDao;
import org.study.self.spring.boot.model.Room;
import org.study.self.spring.boot.model.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public List<User> list(){
		return userDao.list();
	}
	
	public void save(User user){
		userDao.save(user);
	}
	
	public void search(){
		userDao.search();
	}
	
	public User getById(Integer id) {
		return userDao.findById(id);
	}
}
