package br.com.fiap.mygames.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    GameRepository repository;

    public List<Game> findAll(){
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var game = repository.findById(id);
        if(game.isEmpty()) return false;
        repository.deleteById(id);
        return true;
    }

    public void save(Game game) {
        repository.save(game);
    }
    
}