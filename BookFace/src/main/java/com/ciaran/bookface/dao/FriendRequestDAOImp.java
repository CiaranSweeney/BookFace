package com.ciaran.bookface.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ciaran.bookface.entity.FriendRequest;
import com.ciaran.bookface.entity.User;

@Repository
public class FriendRequestDAOImp implements FriendRequestDAO {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void sendRequest(FriendRequest friendRequest){
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(friendRequest);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FriendRequest> getAllFriendRequest(String receiver){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from FriendRequest friendRequest where friendRequest.receiverName =:receiver");
		query.setParameter("receiver", receiver);
		List<FriendRequest> friendRequestList = query.list();
		return friendRequestList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void removeRequest(String receiver,String requester){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from FriendRequest friendRequest where friendRequest.receiverName =:receiver "
				+ "AND friendRequest.requesterName= :requester");
		query.setParameter("receiver", receiver);
		query.setParameter("requester", requester);
		List<FriendRequest> friendRequestList = query.list();
		FriendRequest friendRequest=friendRequestList.get(0);
		if(friendRequest != null)
			session.delete(friendRequest);
	}
}
