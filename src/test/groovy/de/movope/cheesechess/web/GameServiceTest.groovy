package de.movope.cheesechess.web

import de.movope.cheesechess.domain.ChessBoard
import de.movope.cheesechess.domain.Color
import de.movope.cheesechess.domain.Piece
import de.movope.cheesechess.domain.PieceType
import de.movope.cheesechess.domain.Square
import de.movope.cheesechess.web.api.ChessBoardMapper
import de.movope.cheesechess.web.api.MoveResource
import spock.lang.Specification

class GameServiceTest extends Specification {

    private final static String ID = "someId"

    ChessGameRepository chessGameRepository = new InMemChessGameRepository()
    GameService gameService = new GameService(chessGameRepository)

    def "a new game can be started"() {
        when:
        gameService.createGame(ID)

        then:
        chessGameRepository.findAll().size() == 1
        def game = chessGameRepository.findById(ID)
        game.board == ChessBoard.createNew()
    }

    def "two games can not have the same id"() {
        given:
        gameService.createGame(ID)

        when:
        gameService.createGame(ID)

        then:
        thrown IllegalArgumentException
    }


    def "a view of the chess-board is returned"() {
        given:
        gameService.createGame(ID)

        when:
        def view = gameService.getBoardFromGame(ID)

        then:
        def game = chessGameRepository.findById(ID)
        view == ChessBoardMapper.getViewOfBoard(game.getBoard())
    }

    def "a move can be executed"() {
        when:
        gameService.createGame(ID)
        gameService.makeMoveWithWhitePlayer(ID, whitePawnMove())

        then:
        def game = chessGameRepository.findById(ID)
        game.board != ChessBoard.createNew()
        game.board.getPieceAt(Square.create(whitePawnMove().to)) == new Piece(PieceType.PAWN, Color.WHITE)

    }

    def "games can be created and deleted"() {
        given:
        gameService.createGame("id1")
        gameService.createGame("id2")
        gameService.createGame("id3")

        expect:
        chessGameRepository.findAll().size() == 3

        when:
        gameService.deleteAllGames()

        then:
        chessGameRepository.findAll().isEmpty()
    }

    def "all game-ids are returned"() {
        given:
        gameService.createGame("ID1")
        gameService.createGame("ID2")
        gameService.createGame("ID3")

        when:
        def gameIds = gameService.getAllGameIds()

        then:
        gameIds.contains("ID1")
        gameIds.contains("ID2")
        gameIds.contains("ID3")
    }

    private static MoveResource whitePawnMove() {
        MoveResource moveResource = new MoveResource();
        moveResource.from = "A2"
        moveResource.to = "A4"
        return moveResource
    }


}
