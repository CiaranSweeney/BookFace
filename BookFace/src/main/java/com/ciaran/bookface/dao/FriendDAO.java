package com.ciaran.bookface.dao;

import java.util.List;

import com.ciaran.bookface.entity.Friend;

public interface FriendDAO {
	public void acceptFriend(Friend friend);
	public List<Friend> getAllFriends(String userName);
}
