package com.game.controller;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerService;
import com.game.service.PlayerServiceImpl;
import com.game.specifications.PlayerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/rest")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping("/players")
    public ResponseEntity<List<Player>> getAllPlayers(@RequestParam(name = "name", required = false) String name,
                                      @RequestParam(name = "title", required = false) String title,
                                      @RequestParam(name = "race", required = false) Race race,
                                      @RequestParam(name = "profession", required = false) Profession profession,
                                      @RequestParam(name = "minLevel", required = false) Integer minLevel,
                                      @RequestParam(name = "maxLevel", required = false) Integer maxLevel,
                                      @RequestParam(name = "minExperience", required = false) Integer minExperience,
                                      @RequestParam(name = "maxExperience", required = false) Integer maxExperience,
                                      @RequestParam(name = "after", required = false) Long after,
                                      @RequestParam(name = "before", required = false) Long before,
                                      @RequestParam(name = "banned", required = false) Boolean banned,
                                      @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                                      @RequestParam(name = "order", required = false) PlayerOrder order,
                                      @RequestParam(name = "pageSize", required = false) Integer pageSize) {

        Specification<Player> specifications = Specification.where(null);

        if (name != null) {
            specifications = specifications.and(PlayerSpecification.containsName(name));
        }

        if (title != null) {
            specifications = specifications.and(PlayerSpecification.containsTitle(title));
        }

        if (race != null) {
            specifications = specifications.and(PlayerSpecification.hasRace(race));
        }

        if (profession != null) {
            specifications = specifications.and(PlayerSpecification.hasProfession(profession));
        }

        if (minLevel != null) {
            specifications = specifications.and(PlayerSpecification.moreLevelMin(minLevel));
        }

        if (maxLevel != null) {
            specifications = specifications.and(PlayerSpecification.lessLevelMax(maxLevel));
        }

        if (minExperience != null) {
            specifications = specifications.and(PlayerSpecification.moreExperienceMin(minExperience));
        }

        if (maxExperience != null) {
            specifications = specifications.and(PlayerSpecification.lessExperienceMax(maxExperience));
        }

        if (banned != null) {
            specifications = specifications.and(PlayerSpecification.hasBanned(banned));
        }


        if (after != null) {
            specifications = specifications.and(PlayerSpecification.lessAfter(after));
        }

        if (before != null) {
            specifications = specifications.and(PlayerSpecification.moreBefore(before));
        }

        if (pageNumber == null) {
            pageNumber = 0;
        }
        if (pageSize == null) {
            pageSize = 3;
        }

        if (order == null) {
            order = PlayerOrder.ID;
        }
        Pageable pageable;

        if (order.equals(PlayerOrder.ID)) {
            pageable =  PageRequest.of(pageNumber, pageSize, Sort.by("id"));
            return new ResponseEntity(playerService.getAllPlayers(specifications, pageable), HttpStatus.OK);
        }

        if (order.equals(PlayerOrder.LEVEL)) {
            pageable =  PageRequest.of(pageNumber, pageSize, Sort.by("level"));
            return new ResponseEntity(playerService.getAllPlayers(specifications, pageable), HttpStatus.OK);
        }

        if (order.equals(PlayerOrder.EXPERIENCE)) {
            pageable =  PageRequest.of(pageNumber, pageSize, Sort.by("experience"));
            return new ResponseEntity(playerService.getAllPlayers(specifications, pageable), HttpStatus.OK);
        }

        if (order.equals(PlayerOrder.BIRTHDAY)) {
            pageable =  PageRequest.of(pageNumber, pageSize, Sort.by("birthday"));
            return new ResponseEntity(playerService.getAllPlayers(specifications, pageable), HttpStatus.OK);
        }

        if (order.equals(PlayerOrder.NAME)) {
            pageable =  PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            return new ResponseEntity(playerService.getAllPlayers(specifications, pageable), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }



    @GetMapping("/players/count")
    public ResponseEntity<Integer> countAllPlayers(@RequestParam(name = "name", required = false) String name,
                                                   @RequestParam(name = "title", required = false) String title,
                                                   @RequestParam(name = "race", required = false) Race race,
                                                   @RequestParam(name = "profession", required = false) Profession profession,
                                                   @RequestParam(name = "minLevel", required = false) Integer minLevel,
                                                   @RequestParam(name = "maxLevel", required = false) Integer maxLevel,
                                                   @RequestParam(name = "minExperience", required = false) Integer minExperience,
                                                   @RequestParam(name = "maxExperience", required = false) Integer maxExperience,
                                                   @RequestParam(name = "before", required = false) Long before,
                                                   @RequestParam(name = "after", required = false) Long after,
                                                   @RequestParam(name = "banned", required = false) Boolean banned) {

        Specification<Player> specifications = Specification.where(null);

        if (name != null) {
            specifications = specifications.and(PlayerSpecification.containsName(name));
        }

        if (title != null) {
            specifications = specifications.and(PlayerSpecification.containsTitle(title));
        }

        if (race != null) {
            specifications = specifications.and(PlayerSpecification.hasRace(race));
        }

        if (profession != null) {
            specifications = specifications.and(PlayerSpecification.hasProfession(profession));
        }

        if (minLevel != null) {
            specifications = specifications.and(PlayerSpecification.moreLevelMin(minLevel));
        }

        if (maxLevel != null) {
            specifications = specifications.and(PlayerSpecification.lessLevelMax(maxLevel));
        }

        if (minExperience != null) {
            specifications = specifications.and(PlayerSpecification.moreExperienceMin(minExperience));
        }

        if (maxExperience != null) {
            specifications = specifications.and(PlayerSpecification.lessExperienceMax(maxExperience));
        }

        if (banned != null) {
            specifications = specifications.and(PlayerSpecification.hasBanned(banned));
        }

        if (after != null) {
            specifications = specifications.and(PlayerSpecification.lessAfter(after));
        }

        if (before != null) {
            specifications = specifications.and(PlayerSpecification.moreBefore(before));
        }

        return new ResponseEntity(playerService.getAllPlayers(specifications).size(), HttpStatus.OK);



    }



    @GetMapping("/players/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String id) {
        if (Pattern.compile("[^0-9]").matcher(id).find() || id.equals("0")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {

            Long newId = Long.parseLong(id);
            try {
                return new ResponseEntity(playerService.getById(newId), HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity deletePlayer(@PathVariable String id) {
        if (Pattern.compile("[^0-9]").matcher(id).find() || id.equals("0")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {

            Player player;

            Long newId = Long.parseLong(id);
            try {
                player = playerService.getById(newId);
            } catch (NoSuchElementException e) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }


            if (player != null) {
                playerService.deletePlayer(newId);
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/players/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable String id,
                                       @RequestBody Player update) {

        if (Pattern.compile("[^0-9]").matcher(id).find() || id.equals("0")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

            Player player;
            Long newId = Long.parseLong(id);
            try {
                player = playerService.getById(newId);
            } catch (NoSuchElementException e) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

            if (update.getName() != null) {
                if (update.getName().length()>12) {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                } else {
                    player.setName(update.getName());
                }
            }

            if (update.getTitle() != null) {
                if (update.getTitle().length()>30) {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                } else {
                    player.setTitle(update.getTitle());
                }
            }

            if (update.getRace() != null) {
                player.setRace(update.getRace());
            }

            if (update.getProfession() != null) {
                player.setProfession(update.getProfession());
            }

            if (update.getBirthday() !=  null) {
                if (update.getBirthday().getTime() <= 0) {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                } else {
                    player.setBirthday(update.getBirthday());
                }
            }

            if (update.getBanned() != null) {
                player.setBanned(update.getBanned());
            }

            if (update.getExperience() != null) {

                if (update.getExperience() < 0 || update.getExperience() > 10000000) {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                } else {
                    player.setExperience(update.getExperience());
                }

            }


        return new ResponseEntity(player, HttpStatus.OK);

        }




        @PostMapping("/players")
        public ResponseEntity<Player> createNewPlayer(@RequestBody Player create) {
        if (create == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if (create.getName() == null
                || create.getTitle() == null
                || create.getExperience() == null
                || create.getRace() == null
                || create.getProfession() == null
                || create.getBirthday() == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if (create.getName().length() > 12
            || create.getName().equals("")
            || create.getTitle().length() > 30
            || create.getBirthday().getTime() <= 0
            || create.getExperience() < 0
            || create.getExperience() > 10000000) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Player player = new Player();
        player.setName(create.getName());
        player.setTitle(create.getTitle());
        player.setBirthday(create.getBirthday());
        player.setExperience(create.getExperience());
        player.setRace(create.getRace());
        player.setProfession(create.getProfession());
        if (create.getBanned()==null) {
            player.setBanned(false);
        } else {
            player.setBanned(create.getBanned());
        }


        playerService.createPlayer(player);
        return new ResponseEntity(player,HttpStatus.OK);


        }
}



