package io.ronakdave.game.controller;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import io.ronakdave.game.service.GameService;

@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GameService gameService;

    @Test
    public void whenPlayerIdIsInvalid_thenReturnBadRequest() throws Exception {
        mvc
            .perform(post("/submit/0/rock").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("Invalid player id")));
    }

    @Test
    public void whenShapeIsInvlaid_thenReturnBadRequest() throws Exception {
        mvc
            .perform(post("/submit/1/null"))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("Invalid shape provided")));
    }

}
