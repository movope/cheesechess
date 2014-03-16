package de.movope.game;

import de.movope.game.domain.Piece;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Player {

    private Board board;
    private Color color;

    public Player(Board board, Color color) {
        this.board = board;
        this.color = color;
    }

    public void makeRandomMove() {
        Piece randomPiece = null;
        Collection<Square> possibleTargets = Collections.emptyList();
        while (possibleTargets.size() == 0) {
            randomPiece = getRandomPiece();
            possibleTargets = randomPiece.getPossibleTargets(board);
        }
        Square target = chooseOneOf(possibleTargets);
        randomPiece.moveTo(target);
    }

    private Square chooseOneOf(Collection<Square> possibleTargets) {
        Square[] targets = possibleTargets.toArray(new Square[possibleTargets.size()]);
        int rand = (int) (possibleTargets.size() * Math.random());
        return targets[rand];
    }

    private Piece getRandomPiece() {
        Collection<Piece> pieces = board.getPieces(color);
        int randomInt = (int) (Math.random() * pieces.size());
        Iterator<Piece> it = pieces.iterator();
        for (int i = 0; i < randomInt - 1; i++) {
            it.next();
        }
        return it.next();
    }

}
