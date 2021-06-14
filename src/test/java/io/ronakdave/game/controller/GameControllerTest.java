package io.ronakdave.game.controller;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import io.ronakdave.game.security.CustomUserDetailsService;
import io.ronakdave.game.service.GameService;

@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    GameService gameService;

    @MockBean
    Authentication authentication;

    @MockBean
    CustomUserDetailsService userDetailsService;

    @Test
    public void whenNoValidUser_thenReturnForbidden() throws Exception {
        mvc
            .perform(get("/api/game/submit/rock"))
            .andDo(print())
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void whenSubmitValidShape_thenReturnGameSummary() throws Exception {
        mvc
            .perform(get("/api/game/submit/rock"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void whenShapeIsInvlaid_thenReturnBadRequest() throws Exception {
        mvc
            .perform(get("/api/game/submit/null"))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("Invalid shape:")));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void whenAdminTryToPlay_thenReturnFobidden() throws Exception {
        mvc
            .perform(get("/api/game/submit/rock"))
            .andDo(print())
            .andExpect(status().isForbidden());
    }

}
