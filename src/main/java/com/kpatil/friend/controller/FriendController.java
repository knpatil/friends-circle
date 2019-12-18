package com.kpatil.friend.controller;

import com.kpatil.friend.model.Friend;
import com.kpatil.friend.service.FriendService;
import com.kpatil.friend.util.FieldErrorMessage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendController {

  private FriendService friendService;

  @Autowired
  public FriendController(FriendService friendService) {
    this.friendService = friendService;
  }

  @PostMapping("/friends")
  public Friend create(@Valid @RequestBody Friend friend) {
    return friendService.save(friend);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
    List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
    return fieldErrors.stream()
        .map(fieldError -> new FieldErrorMessage(fieldError.getField(),
            fieldError.getDefaultMessage()))
        .collect(Collectors.toList());
  }

  @GetMapping("/friends")
  public Iterable<Friend> read() {
    return friendService.findAll();
  }

  @PutMapping("/friends")
  public ResponseEntity<Friend> update(@RequestBody Friend friend) {
    if (friendService.findById(friend.getId()).isPresent()) {
      return new ResponseEntity<>(friendService.save(friend), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(friend, HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/friends/{id}")
  public void delete(@PathVariable Integer id) {
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
