package de.movope.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(String gameId) {
        super("could not find game '" + gameId + "'.");
    }
}

