package com.ciaran.bookface.service;

import java.util.List;

import com.ciaran.bookface.entity.Post;

public interface PostService {
	public void addPost(Post p);
	public List<Post> getPosts();
}
