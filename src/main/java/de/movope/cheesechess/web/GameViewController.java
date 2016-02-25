package de.movope.cheesechess.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
        return "game";
    }
}
