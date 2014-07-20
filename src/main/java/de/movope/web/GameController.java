package de.movope.web;

import de.movope.game.ChessBoard;
import de.movope.game.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.google.common.base.Preconditions.checkNotNull;

@RestController
public class GameController {

    @Autowired
    private ChessBoardRepository repository;

    @RequestMapping(value = "/game/{gameId}", method = RequestMethod.PUT)
    public HttpStatus startNewGame(@PathVariable String gameId) {
        ChessBoard newBoard = new ChessBoard(gameId);
        newBoard.initPieces();
        repository.save(newBoard);
        return HttpStatus.CREATED;
    }


    @RequestMapping(value = "/game/{gameId}", method = RequestMethod.GET)
    public ChessBoardView getBoardFromGame(@PathVariable String gameId) {
        ChessBoard board = repository.findById(gameId);
        checkNotNull(board, "Chessboard with id=" + gameId + " not found.");
        return new ChessBoardView(board);
    }

    @RequestMapping(value = "/game/{gameId}/move", method = RequestMethod.POST)
    public HttpStatus makeMove(@PathVariable String gameId, @RequestBody MoveRessource move) {
        ChessBoard board = repository.findById(gameId);
        checkNotNull(board, "Chessboard with id=" + gameId + " not found.");
        board.execute(Move.create(move.getFrom(), move.getTo()));
        repository.save(board);
        return HttpStatus.CREATED;
    }

    @RequestMapping("/game/delete")
    public void delete() {
        repository.deleteAll();
    }
}