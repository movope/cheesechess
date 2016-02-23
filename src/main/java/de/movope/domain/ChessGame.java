package de.movope.domain;


import de.movope.evaluation.MoveEvaluator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Document
public class ChessGame {

    @Id
    private final String id;

    private final ChessBoard board;
    private final Map<Color, Player> players;

    private Color nextPlayerToMove = Color.WHITE;

    private ChessGame(String id) {
        this.id = id;
        board = ChessBoard.createNew();
        players = newPlayers();
        }

    public static ChessGame createNew(String id) {
        return new ChessGame(id);
    }

    public String getId() {
        return id;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public boolean isMovePossible(Move move, Color color) {
        if (!isSquareOccupiedByPiece(move.from(), color)) {
            return false;
        }
        return MoveEvaluator.on(board).analyse(color).contains(move);
    }

    private boolean isSquareOccupiedByPiece(Square square, Color color) {
        return board.getPieceAt(square.toString()).getColor() == color;
    }

    public void execute(Move move) {
        board.execute(move);
        nextPlayerToMove = nextPlayerToMove.invert();
        checkIfGameOver();
    }

    private void checkIfGameOver() {
       if (MoveEvaluator.on(board)
                        .isPlayerGameOver(nextPlayerToMove)) {
            players.get(nextPlayerToMove).gameOver();
        }
    }

    public void executeNextMoveForComputer() {
        if (players.get(nextPlayerToMove).isControlledByComputer()) {
            makeRandomMoveForPlayer(nextPlayerToMove);
        }
    }

    private void makeRandomMoveForPlayer(Color color) {
        Move move = MoveEvaluator.on(board).selectRandomMoveForColor(color);

        if (move != null) {
            execute(move);
        }
    }

    public Color colorOfWinningPlayer() {
        for (Map.Entry<Color, Player>  entry : players.entrySet()) {
            if (entry.getValue().isGameOver()) {
                return entry.getKey().invert();
            }
        }
        return Color.UNDEFINED;
    }

    private static Map<Color, Player> newPlayers() {
        Map<Color, Player> players = new HashMap<>();
        players.put(Color.WHITE, Player.newPlayer());
        players.put(Color.BLACK, Player.computer());
        return Collections.unmodifiableMap(players);
    }
}