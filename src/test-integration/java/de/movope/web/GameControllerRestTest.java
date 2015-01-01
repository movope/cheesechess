package de.movope.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.movope.game.ChessBoard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class GameControllerRestTest {

    private MockMvc mockMvc;

    @Autowired
    private ChessGameRepository chessGameRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.chessGameRepository.deleteAll();
    }

    @Test
    public void gameNotFound() throws Exception {
        mockMvc.perform(get("/game/notexisting"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void newGameCanBeStarted() throws Exception {
        String gameId = "ben";

        mockMvc.perform(put("/game/" + gameId)).andExpect(status().isCreated());

        MvcResult result = mockMvc.perform(get("/game/" + gameId)).andExpect(status().isOk()).andReturn();

        ChessBoardView response = fromJson(result, ChessBoardView.class);
        assertEquals(new ChessBoardView(ChessBoard.createNew(gameId)), response);

    }

    private <T> T fromJson(MvcResult result, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(result.getResponse().getContentAsString(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
