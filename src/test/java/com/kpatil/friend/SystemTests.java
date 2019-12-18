package com.kpatil.friend;

import com.kpatil.friend.model.Address;
import com.kpatil.friend.model.Friend;
import java.util.Collections;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SystemTests {

  @Test
  public void testCreateReadDelete() {
    String url = "http://localhost:8080/friends";

    RestTemplate restClient = new RestTemplate();

    Friend friend = new Friend("John", "Tr", 59, true,
        Collections.singletonList(new Address("1 Holly Blvd", "Los Angeles")));

    // create
    ResponseEntity<Friend> entity = restClient.postForEntity(url, friend, Friend.class);

    // read
    Friend[] friends = restClient.getForObject(url, Friend[].class);
    Assertions.assertThat(friends).extracting(Friend::getFirstName).contains("John");

    // delete
    restClient.delete(url + "/" + Objects.requireNonNull(entity.getBody()).getId());
    Assertions.assertThat(restClient.getForObject(url, Friend[].class)).extracting(Friend::getId)
        .doesNotContain(entity.getBody().getId());
  }
}
