package com.ciaran.bookface.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ciaran.bookface.dao.FriendDAO;
import com.ciaran.bookface.entity.Friend;

@Service
public class FriendServiceImp implements FriendService{
private FriendDAO friendDAO;
	
	public void setFriendDAO(FriendDAO friendDAO) {
		this.friendDAO = friendDAO;
	}
	
	@Override
	@Transactional
	public void acceptFriend(Friend friend){
		friendDAO.acceptFriend(friend);
	}
	@Override
	@Transactional
	public List<Friend> getAllFriends(String userName){
		return friendDAO.getAllFriends(userName);
	}
}
