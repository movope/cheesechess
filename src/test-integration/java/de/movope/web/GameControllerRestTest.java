package de.movope.web;

import de.movope.game.ChessBoard;
import de.movope.game.Move;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    public void aValidMoveCanBeExecuted() throws Exception {
        String gameId = "ben";
        mockMvc.perform(put("/game/" + gameId)).andExpect(status().isCreated());

        Move move = Move.create("A2", "A4");

        mockMvc.perform(post("/game/{gameId}/move", gameId)
                .content(this.json(toResource(move)))
                .contentType(contentType))
                .andExpect(status().isOk());

        ChessBoard expected = ChessBoard.createNew("ben");
        expected.execute(move);

        MvcResult result = mockMvc.perform(get("/game/{gameId}/board", gameId))
                .andExpect(status().isOk())
                .andReturn();
        ChessBoardView response = fromJson(result, ChessBoardView.class);
        assertEquals(new ChessBoardView(expected), response);
    }

    private MoveRessource toResource(Move move) {
        MoveRessource ressource = new MoveRessource();
        ressource.setTo(move.to().toString());
        ressource.setFrom(move.from().toString());
        return ressource;
    }

}
