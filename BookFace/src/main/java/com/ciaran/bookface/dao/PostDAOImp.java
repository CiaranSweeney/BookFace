package com.ciaran.bookface.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ciaran.bookface.entity.Post;;

@Repository
public class PostDAOImp implements PostDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImp.class);
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addPost(Post p){		
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getPosts(){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Post ORDER BY posttime DESC");
		List<Post> postList = query.list();
		return postList;
	}
}
