package com.kpatil.friend.controller;

import com.kpatil.friend.model.Friend;
import com.kpatil.friend.service.FriendService;
import java.util.Optional;
import javax.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendController {

  private FriendService friendService;

  @Autowired
  public FriendController(FriendService friendService) {
    this.friendService = friendService;
  }

  @PostMapping("/friends")
  Friend create(@RequestBody Friend friend) throws ValidationException {
    if (friend.getId() == 0 && friend.getFirstName() != null && friend.getLastName() != null) {
      return friendService.save(friend);
    } else {
      throw new ValidationException("Friend can not be created with empty name.");
    }
  }

  @GetMapping("/friends")
  Iterable<Friend> read() {
    return friendService.findAll();
  }

  @PutMapping("/friends")
  ResponseEntity<Friend> update(@RequestBody Friend friend) {
    if (friendService.findById(friend.getId()).isPresent()) {
      return new ResponseEntity<>(friendService.save(friend), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(friend, HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/friends/{id}")
  void delete(@PathVariable Integer id) {
    friendService.deleteById(id);
  }

  @GetMapping("/friends/{id}")
  Optional<Friend> findById(@PathVariable Integer id) {
    return friendService.findById(id);
  }

  @GetMapping("/friends/search")
  Iterable<Friend> findByQuery(@RequestParam(value = "first", required = false) String firstName,
      @RequestParam(value = "last", required = false) String lastName) {

    if (firstName != null && lastName != null) {
      return friendService.findByFirstNameAndLastName(firstName, lastName);
    } else if (firstName != null) {
      return friendService.findByFirstName(firstName);
    } else if (lastName != null) {
      return friendService.findByLastName(lastName);
    } else {
      return friendService.findAll();
    }

  }


}
