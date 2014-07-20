package de.movope.web;


import de.movope.game.ChessBoard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChessBoardRepository extends MongoRepository<ChessBoard, String> {

    public ChessBoard findById(String id);
}