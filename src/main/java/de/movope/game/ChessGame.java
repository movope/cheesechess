package de.movope.game;


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
        Move move = players.get(color).getRandomMove(board);
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
        MoveEvaluation evaluation = MoveEvaluator.on(board)
                .considerKingInCheck()
                .analyse(color);

        if (evaluation.possibleTargets().contains(move) ||
                evaluation.possibleAttacks().contains(move)) {
            return true;
        }
        return false;
    }

    public void execute(Move move) {
        board.execute(move);
        colorOfNextMove = colorOfNextMove.invert();
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
}