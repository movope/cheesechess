package de.movope.web;

import de.movope.domain.ChessGame;

import java.util.ArrayList;
import java.util.List;

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
}
