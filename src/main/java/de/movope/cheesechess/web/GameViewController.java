package de.movope.cheesechess.web;


import de.movope.cheesechess.web.api.ChessBoardView;
import de.movope.cheesechess.web.api.MoveResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class GameViewController {

    @Autowired
    GameService gameService;

    @RequestMapping(value = "/game/overview", method = GET)
    public String index(Model model) {

        List<String> gameIds = gameService.getAllGameIds();
        model.addAttribute("gameIds", gameIds);

        return "index";
    }


    @RequestMapping(value = "/game/{gameId}", method = GET)
    public String game(Model model, @PathVariable(value = "gameId") String gameId) {

        model.addAttribute("gameId", gameId);
        ChessBoardView board = gameService.getBoardFromGame(gameId);
        model.addAttribute("pieces", board.getBoardView());
        model.addAttribute("move", new MoveResource());
        return "game";
    }


    @RequestMapping(value = "/game/{gameId}/move/new", method = POST)
    public String makeMove(@PathVariable(value = "gameId") String gameId,
                           @ModelAttribute(value = "move") MoveResource move,
                           Model model) {
        model.addAttribute("move", move);
        gameService.makeMoveWithWhitePlayer(gameId, move);
        return "redirect:/game/" + gameId;
    }
}
