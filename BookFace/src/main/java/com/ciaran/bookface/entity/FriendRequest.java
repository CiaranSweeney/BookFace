package com.ciaran.bookface.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FRIEND_REQUESTS")
public class FriendRequest {
	@Id
	@Column(name="friendship_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int friendShipId;
	
	@Column(name="friend_receiver_name")
	private String receiverName;
	
	@Column(name="friend_requester_name")
	private String requesterName;

	public int getFriendShipId() {
		return friendShipId;
	}

	public void setFriendShipId(int friendShipId) {
		this.friendShipId = friendShipId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}
	
}
