package de.movope.web;


import de.movope.domain.ChessGame;

import java.util.List;

public interface ChessGameRepository {

    ChessGame findById(String id);
    ChessGame save(ChessGame game);
    void deleteAll();
    List<ChessGame> findAll();
}
