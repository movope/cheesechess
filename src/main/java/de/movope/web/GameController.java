package de.movope.web;

import de.movope.game.ChessGame;
import de.movope.game.Color;
import de.movope.game.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.google.common.base.Preconditions.checkNotNull;

@RestController
public class GameController {

    @Autowired
    private ChessGameRepository gameRepository;

    @RequestMapping(value = "/game/{gameId}", method = RequestMethod.PUT)
    public HttpStatus startNewGame(@PathVariable String gameId) {
        ChessGame game = ChessGame.createNew(gameId);
        gameRepository.save(game);
        return HttpStatus.CREATED;
    }


    @RequestMapping(value = "/game/{gameId}", method = RequestMethod.GET)
    public ChessBoardView getBoardFromGame(@PathVariable String gameId) {
        ChessGame game = gameRepository.findById(gameId);
        checkNotNull(game, "Chessgame with id=" + gameId + " not found.");
        return new ChessBoardView(game.getBoard());
    }

    @RequestMapping(value = "/game/{gameId}/move", method = RequestMethod.POST)
    public HttpStatus makeMove(@PathVariable String gameId, @RequestBody MoveRessource moveRessource) {
        ChessGame game = gameRepository.findById(gameId);
        checkNotNull(game, "Chessgame with id=" + gameId + " not found.");

        Move move = Move.create(moveRessource.getFrom(), moveRessource.getTo());

        if (!game.isMovePossible(move, Color.WHITE)) {
            throw new IllegalStateException("Move can not be executed!");
        }
        game.execute(move);
        game.makeRandomMoveForPlayer(Color.BLACK);
        gameRepository.save(game);
        return HttpStatus.CREATED;
    }

    @RequestMapping("/game/delete")
    public void delete() {
        gameRepository.deleteAll();
    }
}