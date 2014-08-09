package de.movope.web;


import de.movope.game.ChessGame;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChessGameRepository extends MongoRepository<ChessGame, String> {

    public ChessGame findById(String id);
}
