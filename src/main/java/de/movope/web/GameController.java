package de.movope.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/game/{gameId}", method = RequestMethod.PUT)
    public ResponseEntity<?> startNewGame(@PathVariable String gameId) {
        gameService.createGame(gameId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(gameId).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/game/{gameId}/board", method = RequestMethod.GET)
    public ChessBoardView getBoardFromGame(@PathVariable String gameId) {
        return gameService.getBoardFromGame(gameId);
    }

    @RequestMapping(value = "/game/{gameId}/move", method = RequestMethod.POST)
    public HttpStatus makeMove(@PathVariable String gameId, @RequestBody MoveResource moveRessource) {
        gameService.makeMove(gameId, moveRessource);
        return HttpStatus.CREATED;
    }

    @RequestMapping(value = "/game/delete", method = RequestMethod.DELETE)
    public void delete() {
        gameService.deleteAllGames();
    }
}