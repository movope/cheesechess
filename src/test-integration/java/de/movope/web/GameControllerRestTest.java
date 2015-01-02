package de.movope.web;

import de.movope.game.ChessBoard;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameControllerRestTest extends SystemTest {

    @Autowired
    private ChessGameRepository chessGameRepository;

    @Before
    public void setup() throws Exception {
        super.setup();
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

        MvcResult result = mockMvc.perform(get("/game/{gameId}/board", gameId)).andExpect(status().isOk()).andReturn();

        ChessBoardView response = fromJson(result, ChessBoardView.class);
        assertEquals(new ChessBoardView(ChessBoard.createNew(gameId)), response);

    }
}
