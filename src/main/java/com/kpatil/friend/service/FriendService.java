package com.kpatil.friend.service;

import com.kpatil.friend.model.Friend;
import org.springframework.data.repository.CrudRepository;

public interface FriendService extends CrudRepository<Friend, Integer> {

}
