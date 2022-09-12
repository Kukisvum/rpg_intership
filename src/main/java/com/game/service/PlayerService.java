package com.game.service;

import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;


public interface PlayerService {

    public List<Player> getAllPlayers(Specification<Player> specifications, Pageable pageable);
    public List<Player> getAllPlayers(Specification<Player> specifications);

    public Player getById(Long id);

    public void deletePlayer(Long id);

    public void updatePlayer(Player player);

    public void createPlayer(Player player);
}
