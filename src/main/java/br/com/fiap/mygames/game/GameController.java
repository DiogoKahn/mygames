package br.com.fiap.mygames.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

    @Autowired
    MessageSource message;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        model.addAttribute("games", service.findAll());
        return "game/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if(service.delete(id)){
            redirect.addFlashAttribute("success", getMessage("game.delete.success") );
        }else{
            redirect.addFlashAttribute("error", getMessage("game.notfound"));
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
        redirect.addFlashAttribute("success", getMessage("game.create.success"));
        return "redirect:/game";
    }

    private String getMessage(String code){
        return message.getMessage(code, null, LocaleContextHolder.getLocale());
    }
    
}