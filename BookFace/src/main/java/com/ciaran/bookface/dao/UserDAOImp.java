package com.ciaran.bookface.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ciaran.bookface.entity.User;

@Repository
public class UserDAOImp implements UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImp.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public User getUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		User user = (User) session.load(User.class, new Integer(id));
		logger.info("User loaded successfully");
		return user;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUser() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> usersList = session.createQuery("from User").list();
		for(User u : usersList){
			logger.info("User List::"+u);
		}
		return usersList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User login(String name,String password){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User user where user.name = :name and user.password= :password");
		//List<User> usersList = session.createQuery("from User").list();
		query.setParameter("name", name);
		query.setParameter("password", password);
		List<User> usersList = query.list();
		if(usersList.size()==0)
			return null;
		return usersList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User getUserByName(String name){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User user where user.name =:name");
		query.setParameter("name", name);
		List<User> usersList = query.list();
		if(usersList.size()==0)
			return null;
		return usersList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(u);
		logger.info("A new user was added");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> userSearch(String search){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User user where user.name like :search");
		query.setParameter("search", "%"+search+"%");
		List<User> usersList = query.list();
		return usersList;
	}

}
