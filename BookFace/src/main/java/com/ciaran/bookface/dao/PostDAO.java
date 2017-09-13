package com.ciaran.bookface.dao;

import java.util.List;

import com.ciaran.bookface.entity.Post;

public interface PostDAO {
	public void addPost(Post p);
	public List<Post> getPosts();
}
