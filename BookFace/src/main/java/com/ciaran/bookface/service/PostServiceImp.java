package com.ciaran.bookface.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ciaran.bookface.dao.PostDAO;
import com.ciaran.bookface.entity.Post;

@Service
public class PostServiceImp implements PostService {
	private PostDAO postDAO;

	public void setPostDAO(PostDAO postDAO) {
		this.postDAO = postDAO;
	}
	@Override
	@Transactional
	public void addPost(Post p){
		postDAO.addPost(p);
	}
	@Override
	@Transactional
	public List<Post> getPosts(){
		return postDAO.getPosts();
	}
	
}
