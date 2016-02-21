package de.movope.web;


import de.movope.domain.ChessBoard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChessBoardRepository extends MongoRepository<ChessBoard, String> {

    ChessBoard findById(String id);
}
