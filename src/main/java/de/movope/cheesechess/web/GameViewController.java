package de.movope.cheesechess.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GameViewController {

    @Autowired
    GameService gameService;

    @RequestMapping("/game/overview")
    public String index(Model model) {

        List<String> gameIds = gameService.getAllGameIds();
        model.addAttribute("gameIds", gameIds);

        return "index";
    }
}
