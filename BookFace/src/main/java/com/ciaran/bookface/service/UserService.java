package com.ciaran.bookface.service;

import java.util.List;

import com.ciaran.bookface.entity.User;


public interface UserService {
	public User getUser(int id);
	public List<User> listUsers();
	public User login(String name, String password);
	public void addUser(User u);
	public User getUserByName(String name);
	public List<User> userSearch(String search);
}
