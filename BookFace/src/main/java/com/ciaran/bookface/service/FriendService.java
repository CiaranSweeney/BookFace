package com.ciaran.bookface.service;

import java.util.List;

import com.ciaran.bookface.entity.Friend;

public interface FriendService  {
	public void acceptFriend(Friend friend);
	public List<Friend> getAllFriends(String userName);
}
