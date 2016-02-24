package de.movope.cheesechess.web;


import de.movope.cheesechess.domain.ChessGame;

import java.util.List;

public interface ChessGameRepository {

    ChessGame findById(String id);
    ChessGame save(ChessGame game);
    void deleteAll();
    List<ChessGame> findAll();
    boolean exists(String gameId);
}
