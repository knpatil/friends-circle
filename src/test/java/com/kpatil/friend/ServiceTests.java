package com.kpatil.friend;

import com.kpatil.friend.model.Address;
import com.kpatil.friend.model.Friend;
import com.kpatil.friend.service.FriendService;
import java.util.Collections;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ServiceTests {

  @Autowired
  private FriendService friendService;

  @Test
  public void testCreateReadDelete() {
    Friend friend = new Friend("Julie", "Rob", 35, true,
        Collections.singletonList(new Address("21 Hollywood Blvd", "Los Angeles")));

    friendService.save(friend);

    Iterable<Friend> friends = friendService.findAll();
    Assertions.assertThat(friends).extracting(Friend::getFirstName).containsOnly("Julie");

    friendService.deleteAll();
    Assertions.assertThat(friendService.findAll()).isEmpty();
  }

}
