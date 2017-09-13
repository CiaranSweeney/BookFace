package com.ciaran.bookface.dao;

import java.util.List;

import com.ciaran.bookface.entity.User;

public interface UserDAO {
	public User getUser(int id);
	public List<User> listUser();
	public User login(String name, String password);
	public void addUser(User u);
	public User getUserByName(String name);
	public List<User> userSearch(String search);
}
