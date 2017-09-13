package com.ciaran.bookface.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="POSTS")
public class Post {
	
	@Id
	@Column(name="postid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int postId;
	
	//@ManyToOne
   // @JoinColumn(name = "username")
	@Column(name="username")
	private String name;
	
	private String post;
	
	@Column(name = "posttime", columnDefinition="DATETIME")
	//@Temporal(TemporalType.TIMESTAMP)
	private String posttime;
	
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPosttime() {
		return posttime;
	}

	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}
	
}
