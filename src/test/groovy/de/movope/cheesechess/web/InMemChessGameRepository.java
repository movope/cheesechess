package de.movope.cheesechess.web;

import de.movope.cheesechess.domain.ChessGame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemChessGameRepository implements ChessGameRepository {

    private List<ChessGame> games = new ArrayList<>();

    @Override
    public ChessGame findById(String id) {
        return games.stream()
                .filter(chessGame -> chessGame.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public ChessGame save(ChessGame game) {
        ChessGame found = findById(game.getId());

        if (found == null) {
            games.add(game);
            return game;
        }
        found = game;
        return found;
    }

    @Override
    public void deleteAll() {
        games.clear();
    }

    @Override
    public List<ChessGame> findAll() {
        return games;
    }

    @Override
    public boolean exists(String gameId) {
        return games.stream()
                .map(ChessGame::getId)
                .collect(Collectors.toList())
                .contains(gameId);
    }
}
