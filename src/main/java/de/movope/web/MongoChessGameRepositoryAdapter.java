package de.movope.web;

import de.movope.domain.ChessGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "chessGameRepository")
public class MongoChessGameRepositoryAdapter implements ChessGameRepository {

    private MongoChessGameRepository mongoChessGameRepository;

    @Autowired
    public MongoChessGameRepositoryAdapter(MongoChessGameRepository mongoChessGameRepository) {
        this.mongoChessGameRepository = mongoChessGameRepository;
    }

    @Override
    public ChessGame findById(String id) {
        return mongoChessGameRepository.findById(id);
    }

    @Override
    public ChessGame save(ChessGame game) {
        return mongoChessGameRepository.save(game);

    }

    @Override
    public void deleteAll() {
        mongoChessGameRepository.deleteAll();
    }

    @Override
    public List<ChessGame> findAll() {
        return mongoChessGameRepository.findAll();
    }

    @Override
    public boolean exists(String gameId) {
        return mongoChessGameRepository.exists(gameId);
    }
}
