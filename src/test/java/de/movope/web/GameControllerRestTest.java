package de.movope.web;

import de.movope.domain.ChessBoard;
import de.movope.domain.Move;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
    public void Status404ReturnedIfGameDoesNotExist() throws Exception {
        String gameId = "notExisting";
        mockMvc.perform(get("/game/{id}/board", gameId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void newGameCanBeStarted() throws Exception {
        String gameId = "ben";

        mockMvc.perform(put("/game/{id}", gameId)).andExpect(status().isCreated());

        MvcResult result = mockMvc.perform(get("/game/{gameId}/board", gameId)).andExpect(status().isOk()).andReturn();

        ChessBoardView response = fromJson(result, ChessBoardView.class);
        assertThat(response, is(new ChessBoardView(ChessBoard.createNew(gameId))));
    }

    @Test
    public void aValidMoveCanBeExecuted() throws Exception {
        String gameId = "ben";
        mockMvc.perform(put("/game/{id}", gameId)).andExpect(status().isCreated());

        Move move = Move.create("A2", "A4");

        mockMvc.perform(post("/game/{id}/move", gameId)
                .content(this.json(toResource(move)))
                .contentType(contentType))
                .andExpect(status().isOk());

        ChessBoard expected = ChessBoard.createNew("ben");
        expected.execute(move);

        MvcResult result = mockMvc.perform(get("/game/{id}/board", gameId))
                .andExpect(status().isOk())
                .andReturn();
        ChessBoardView response = fromJson(result, ChessBoardView.class);

        assertThat(response, is(new ChessBoardView(expected)));
    }

    private static MoveResource toResource(Move move) {
        MoveResource resource = new MoveResource();
        resource.setTo(move.to().toString());
        resource.setFrom(move.from().toString());
        return resource;
    }

}
