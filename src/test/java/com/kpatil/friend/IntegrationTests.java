package com.kpatil.friend;

import com.kpatil.friend.controller.FriendController;
import com.kpatil.friend.model.Address;
import com.kpatil.friend.model.Friend;
import java.util.Collections;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {

  @Autowired
  private FriendController friendController;

  @Test
  public void testCreateReadDelete() {
    Friend friend = new Friend("George", "Cl", 54, false,
        Collections.singletonList(new Address("14 Holly Blvd", "Beverly Hills")));

    Friend friendResponse = friendController.create(friend);

    Iterable<Friend> friends = friendController.read();
    Assertions.assertThat(friends).first().hasFieldOrPropertyWithValue("firstName", "George");

    friendController.delete(friendResponse.getId());
    Assertions.assertThat(friendController.read()).isEmpty();

  }
}
