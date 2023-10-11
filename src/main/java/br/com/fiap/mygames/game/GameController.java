package br.com.fiap.mygames.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService service;

    @GetMapping
    public String index(Model model){
        model.addAttribute("game", service.findAll());
        return "game/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if(service.delete(id)){
            redirect.addFlashAttribute("success", "Game apagado com sucesso");
        }else{
            redirect.addFlashAttribute("error", "Game não foi encontrada");
        }
        return "redirect:/game";
    }
    
    @GetMapping("new")
    public String form(Game game){
        return "game/form";
    }
    
    @PostMapping
    public String create(@Valid Game game, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) return "game/form";
        service.save(game);
        redirect.addFlashAttribute("success", "Tarefa cadastrada com sucesso");
        return "redirect:/game";
    }
    
}