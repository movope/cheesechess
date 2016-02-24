package de.movope.cheesechess.web;

import de.movope.cheesechess.web.api.ChessBoardView;
import de.movope.cheesechess.web.api.MoveResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/game/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void startNewGame(@RequestParam(value = "gameId") String gameId) {
        gameService.createGame(gameId);
    }

    @RequestMapping(value = "/game/{gameId}/board", method = RequestMethod.GET)
    @ResponseBody
    public ChessBoardView getBoardFromGame(@PathVariable String gameId) {
        return gameService.getBoardFromGame(gameId);
    }

    @RequestMapping(value = "/game/{gameId}/move", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void makeMove(@PathVariable String gameId, @RequestBody MoveResource moveRessource) {
        gameService.makeMoveWithWhitePlayer(gameId, moveRessource);
    }

    @RequestMapping(value = "/game/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete() {
        gameService.deleteAllGames();
    }
}