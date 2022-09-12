package com.game.service;

import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PlayerServiceImpl  implements PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    @Override
    public List<Player> getAllPlayers(Specification<Player> specifications, Pageable pageable) {
        Page<Player> pages =  playerRepository.findAll(specifications,  pageable);

        return  pages.getContent();
    }

    @Override
    public List<Player> getAllPlayers(Specification<Player> specifications) {
        return playerRepository.findAll(specifications);
    }

    @Override
    public Player getById(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }

    @Override
    public void createPlayer(Player player) {
        playerRepository.save(player);
    }

}
