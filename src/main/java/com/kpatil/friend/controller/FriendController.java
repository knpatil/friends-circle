package com.kpatil.friend.controller;

import com.kpatil.friend.model.Friend;
import com.kpatil.friend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendController {

  private FriendService friendService;

  @Autowired
  public FriendController(FriendService friendService) {
    this.friendService = friendService;
  }

  @PostMapping("/friends")
  Friend create(@RequestBody Friend friend) {
    return friendService.save(friend);
  }

  @GetMapping("/friends")
  Iterable<Friend> read() {
    return friendService.findAll();
  }

  @PutMapping("/friends")
  Friend update(@RequestBody Friend friend) {
    return friendService.save(friend);
  }

  @DeleteMapping("/friends/{id}")
  void delete(@PathVariable Integer id) {
    friendService.deleteById(id);
  }

}
