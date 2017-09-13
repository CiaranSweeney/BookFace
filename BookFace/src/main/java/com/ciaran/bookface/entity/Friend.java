package com.ciaran.bookface.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FRIENDS")
public class Friend {
	@Id
	@Column(name="friendship_id")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int friendShipId;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="friend")
	private String friendName;

	public int getFriendShipId() {
		return friendShipId;
	}

	public void setFriendShipId(int friendShipId) {
		this.friendShipId = friendShipId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
}
