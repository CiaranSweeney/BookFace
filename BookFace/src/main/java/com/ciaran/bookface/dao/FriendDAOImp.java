package com.ciaran.bookface.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ciaran.bookface.entity.Friend;

@Repository
public class FriendDAOImp implements FriendDAO {
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void acceptFriend(Friend friend){
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(friend);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Friend> getAllFriends(String userName){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Friend friend where friend.userName =:userName ");
		query.setParameter("userName", userName);
		List<Friend> friendList=query.list();
		return friendList;
	}
}
