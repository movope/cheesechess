package de.movope.domain;


import de.movope.evaluation.MoveEvaluator;
import de.movope.util.ChessGameUtils;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    @Id
    private String id;

    ChessBoard board;
    Map<Color, Player> players = new HashMap<>();

    Color colorOfNextMove = Color.WHITE;

    private ChessGame(String id) {
        this.id = id;
        board = ChessBoard.createNew(id);
        players.put(Color.WHITE, new Player(Color.WHITE));
        players.put(Color.BLACK, new Player(Color.BLACK));
    }

    public static ChessGame createNew(String id) {
        return new ChessGame(id);
    }

    public void makeRandomMoveForPlayer(Color color) {
        Move move = MoveEvaluator.on(board).selectRandomMoveForColor(color);

        if (move != null) {
            execute(move);
        }
    }

    public void print() {
        ChessGameUtils.print(board);
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public ChessBoard getBoard() {
        return board;
    }

    public boolean isMovePossible(Move move, Color color) {
        if (!(board.getPieceAt(move.from.toString()).getColor() == color)) {
            return false;
        }
        return MoveEvaluator.on(board).analyse(color).contains(move);
    }

    public void execute(Move move) {
        board.execute(move);
        colorOfNextMove = colorOfNextMove.invert();
        checkIfGameOver();
    }

    private void checkIfGameOver() {
       if (MoveEvaluator.on(board)
                        .isPlayerGameOver(colorOfNextMove)) {
            players.get(colorOfNextMove).gameOver();
        }
    }

    public Color nextPlayerToMove() {
        return colorOfNextMove;
    }

    public void activateComputeForColor(Color color) {
        players.get(color).activateComputer();
    }

    public void executeNextMoveForComputer() {
        if (players.get(nextPlayerToMove()).isControlledByComputer()) {
            makeRandomMoveForPlayer(nextPlayerToMove());
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
}