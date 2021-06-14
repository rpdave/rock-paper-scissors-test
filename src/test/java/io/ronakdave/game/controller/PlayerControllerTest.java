package io.ronakdave.game.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import io.ronakdave.game.security.CustomUserDetailsService;
import io.ronakdave.game.service.PlayerService;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PlayerService playerService;

    @MockBean
    CustomUserDetailsService userDetailsService;

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void whenNotAdminGetPlayerList_thenNotAlowed() throws Exception {
        mvc
            .perform(get("/api/player/list"))
            .andDo(print())
            .andExpect(status().isForbidden());
    }

    @Test
    public void whenNoValidUser_thenNotAllowed() throws Exception {
        mvc 
            .perform(get("/api/player/list"))
            .andDo(print())
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void whenValidAdminUser_thenExpectOk() throws Exception {
        mvc 
            .perform(get("/api/player/list"))
            .andDo(print())
            .andExpect(status().isOk());
    }
    
}
