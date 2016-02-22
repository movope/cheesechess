package de.movope.web;

import de.movope.domain.ChessGame;
import de.movope.domain.Color;
import de.movope.domain.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class GameService {

    private ChessGameRepository chessGameRepository;

    @Autowired
    public GameService(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    public void createCame(String gameId) {
        ChessGame game = ChessGame.createNew(gameId);
        chessGameRepository.save(game);
    }

    public ChessBoardView getBoardFromGame(String gameId) {
        ChessGame game = chessGameRepository.findById(gameId);
        if (game == null) {
            throw new GameNotFoundException(gameId);
        }
        checkNotNull(game, "Chessgame with id=" + gameId + " not found.");
        return new ChessBoardView(game.getBoard());
    }

    public void makeMove(String gameId, MoveResource moveRessource) {
        ChessGame game = chessGameRepository.findById(gameId);
        checkNotNull(game, "Chessgame with id=" + gameId + " not found.");

        Move move = Move.create(moveRessource.getFrom(), moveRessource.getTo());

        if (!game.isMovePossible(move, Color.WHITE)) {
            throw new IllegalStateException("Move can not be executed!");
        }
        game.execute(move);
        game.executeNextMoveForComputer();
        chessGameRepository.save(game);
    }

    public void deleteAllGames() {
        chessGameRepository.deleteAll();
    }
}
