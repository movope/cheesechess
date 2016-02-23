package de.movope.web

import de.movope.domain.ChessBoard
import de.movope.domain.Color
import de.movope.domain.Piece
import de.movope.domain.PieceType
import de.movope.domain.Square
import spock.lang.Specification

class GameServiceTest extends Specification {

    private final static String ID = "someId"

    ChessGameRepository chessGameRepository = new InMemChessGameRepository()
    GameService gameService = new GameService(chessGameRepository)

    def "a new game can be started"() {
        when:
        gameService.createCame(ID)

        then:
        chessGameRepository.findAll().size() == 1
        def game = chessGameRepository.findById(ID)
        game.board == ChessBoard.createNew()
    }

    def "a view of the chess-board is returned"() {
        given:
        gameService.createCame(ID)

        when:
        def view = gameService.getBoardFromGame(ID)

        then:
        def game = chessGameRepository.findById(ID)
        view == new ChessBoardView(game.getBoard())
    }

    def "a move can be executed"() {
        when:
        gameService.createCame(ID)
        gameService.makeMove(ID, whitePawnMove())

        then:
        def game = chessGameRepository.findById(ID)
        game.board != ChessBoard.createNew()
        game.board.getPieceAt(Square.create(whitePawnMove().to)) == new Piece(PieceType.PAWN, Color.WHITE)

    }

    def "games can be created and deleted"() {
        given:
        gameService.createCame("id1")
        gameService.createCame("id2")
        gameService.createCame("id3")

        expect:
        chessGameRepository.findAll().size() == 3

        when:
        gameService.deleteAllGames()

        then:
        chessGameRepository.findAll().isEmpty()
    }

    private static MoveResource whitePawnMove() {
        MoveResource moveResource = new MoveResource();
        moveResource.from = "A2"
        moveResource.to = "A4"
        return moveResource
    }


}
