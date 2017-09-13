package com.ciaran.bookface.service;

import java.util.List;

import com.ciaran.bookface.entity.FriendRequest;

public interface FriendRequestService {
	public void sendRequest(FriendRequest friendRequest);
	public List<FriendRequest> getAllFriendRequest(String receiver);
	public void removeRequest(String receiver,String requester);
}
