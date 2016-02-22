package de.movope.web;


import de.movope.domain.ChessGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GameViewController {

    @Autowired
    ChessGameRepository gameRepository;

    @RequestMapping("/game/overview")
    public String index(Model model) {
        List<ChessGame> allGames = gameRepository.findAll();
        List<String> gameIds = allGames.stream().map(ChessGame::getId).collect(Collectors.toList());
        model.addAttribute("gameIds", gameIds);

        return "index";
    }
}
