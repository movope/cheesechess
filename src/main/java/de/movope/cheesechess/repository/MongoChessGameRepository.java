package de.movope.cheesechess.repository;


import de.movope.cheesechess.domain.ChessGame;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoChessGameRepository extends MongoRepository<ChessGame, String> {

    ChessGame findById(String id);
}
