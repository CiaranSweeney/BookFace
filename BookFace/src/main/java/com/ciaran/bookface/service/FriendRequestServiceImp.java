package com.ciaran.bookface.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ciaran.bookface.dao.FriendRequestDAO;
import com.ciaran.bookface.entity.FriendRequest;

@Service
public class FriendRequestServiceImp implements FriendRequestService {
	
	private FriendRequestDAO friendRequestDAO;
	
	public void setFriendRequestDAO(FriendRequestDAO friendRequestDAO) {
		this.friendRequestDAO = friendRequestDAO;
	}

	@Override
	@Transactional
	public void sendRequest(FriendRequest friendRequest) {
		friendRequestDAO.sendRequest(friendRequest);

	}
	
	@Override
	@Transactional
	public List<FriendRequest> getAllFriendRequest(String receiver){
		return friendRequestDAO.getAllFriendRequest(receiver);
	}
	
	@Override
	@Transactional
	public void removeRequest(String receiver,String requester){
		friendRequestDAO.removeRequest(receiver,requester);
	}

}
