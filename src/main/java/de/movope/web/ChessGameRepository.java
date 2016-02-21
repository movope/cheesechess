package de.movope.web;


import de.movope.domain.ChessGame;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChessGameRepository extends MongoRepository<ChessGame, String> {

    ChessGame findById(String id);
}
