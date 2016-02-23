package de.movope.web;

import de.movope.domain.ChessGame;
import de.movope.domain.Color;
import de.movope.domain.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class GameService {

    private ChessGameRepository chessGameRepository;

    @Autowired
    public GameService(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    public void createGame(String gameId) {
        checkArgument(!chessGameRepository.exists(gameId), "A Game with id=" + gameId + " already exists.");
        ChessGame game = ChessGame.createNew(gameId);
        chessGameRepository.save(game);
    }

    public ChessBoardView getBoardFromGame(String gameId) {
        checkIfGameExists(gameId);

        ChessGame game = chessGameRepository.findById(gameId);
        return new ChessBoardView(game.getBoard());
    }

    public void makeMove(String gameId, MoveResource moveRessource) {
        checkIfGameExists(gameId);

        ChessGame game = chessGameRepository.findById(gameId);
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

    private void checkIfGameExists(String gameId) {
        if (!chessGameRepository.exists(gameId)) {
            throw new GameNotFoundException(gameId);
        }
    }

    public List<String> getAllGameIds() {
        List<ChessGame> allGames = chessGameRepository.findAll();
        return allGames.stream()
                .map(ChessGame::getId)
                .collect(Collectors.toList());
    }
}
