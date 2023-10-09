package br.com.fiap.mygames.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService service;

    @GetMapping
    public String index(Model model){
        model.addAttribute("games", service.findAll());
        return "game/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if(service.delete(id)){
            redirect.addFlashAttribute("success", "Jogo apagadd com sucesso");
        }else{
            redirect.addFlashAttribute("error", "Jogo não foi encontrado");
        }
        return "redirect:/game";
    }
    
}
