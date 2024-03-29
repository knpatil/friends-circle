package com.kpatil.friend;

import com.kpatil.friend.controller.FriendController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class FriendCircleApplicationTests {

  @Autowired
  private FriendController friendController;

  @Test
  void contextLoads() {
    Assert.assertNotNull(friendController);
  }

}
