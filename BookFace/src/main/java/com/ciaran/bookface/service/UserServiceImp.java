package com.ciaran.bookface.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ciaran.bookface.dao.UserDAO;
import com.ciaran.bookface.entity.User;


@Service
public class UserServiceImp implements UserService {
	
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	@Transactional
	public User getUser(int id) {
		return userDAO.getUser(id);
	}
	
	@Override
	@Transactional
	public List<User> listUsers(){
		return userDAO.listUser();
	}
	@Override
	@Transactional
	public User login(String name, String password){
		return userDAO.login(name,password);
	}
	
	@Override
	@Transactional
	public void addUser(User u){
		userDAO.addUser(u);
	}
	
	@Override
	@Transactional
	public User getUserByName(String name){
		return userDAO.getUserByName(name);
	}
	
	@Override
	@Transactional
	public List<User> userSearch(String search){
		return userDAO.userSearch(search);
	}

}
