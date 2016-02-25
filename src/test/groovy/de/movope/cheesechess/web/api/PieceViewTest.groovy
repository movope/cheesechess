package de.movope.cheesechess.web.api

import de.movope.cheesechess.domain.Color
import de.movope.cheesechess.domain.PieceType
import spock.lang.Specification

class PieceViewTest extends Specification {

    def "correct image-url is returned"() {

        given:
        PieceView pieceView = new PieceView()
        pieceView.setColor(color)
        pieceView.setPieceType(piece)

        expect:
        pieceView.getImgUrl() == url

        where:
        color | piece | url
        Color.BLACK | PieceType.PAWN | "PAWN_BLACK.png"
        Color.WHITE | PieceType.ROOK | "ROOK_WHITE.png"
        Color.UNDEFINED | PieceType.NULL | "NULL_UNDEFINED.png"
    }

}
