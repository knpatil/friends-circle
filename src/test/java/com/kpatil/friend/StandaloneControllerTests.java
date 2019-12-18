package com.kpatil.friend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kpatil.friend.controller.FriendController;
import com.kpatil.friend.model.Address;
import com.kpatil.friend.model.Friend;
import com.kpatil.friend.service.FriendService;
import java.util.Collections;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(FriendController.class)
public class StandaloneControllerTests {

  @MockBean
  private FriendService friendService;

  @Autowired
  MockMvc mockMvc;

  @Test
  public void testCreateReadDelete() throws Exception {
    Friend friend = new Friend("Matt", "D", 45, true,
        Collections.singletonList(new Address("133 Holly Blvd", "Los Angeles")));
    List<Friend> friends = Collections.singletonList(friend);

    Mockito.when(friendService.findAll()).thenReturn(friends);

    mockMvc.perform(get("/friend"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(1)))
        .andExpect(jsonPath("$[0].firstName", Matchers.is("Gordon")));
  }
}
