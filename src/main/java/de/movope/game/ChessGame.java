package de.movope.game;


import org.springframework.data.annotation.Id;

public class ChessGame {

    @Id
    private String id;

    ChessBoard board;
    Player white;
    Player black;

    private ChessGame(String id) {
        this.id = id;
        board = ChessBoard.createNew(id);
        white = new Player(board, Color.WHITE);
        black = new Player(board, Color.BLACK);
    }

    public static ChessGame createNew(String id) {
        return new ChessGame(id);
    }

    public void makeRandomMoveForPlayer(Color color) {
        Move move = null;
        if (color == Color.WHITE) {
            move = white.getRandomMove(board);
        } else if (color == Color.BLACK) {
            move = black.getRandomMove(board);
        }
        if (move != null) {
            board.execute(move);
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
    }
}