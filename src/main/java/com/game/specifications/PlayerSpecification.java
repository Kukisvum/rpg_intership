package com.game.specifications;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import java.sql.Date;


public class PlayerSpecification  {

    public static Specification<Player> containsName(String name) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("name"),"%"+name+"%");
        });
    }

    public static Specification<Player> containsTitle(String title) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("title"),"%"+title+"%");
        });
    }

    public static Specification<Player> hasRace(Race race) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("race"),race);
        });
    }

    public static Specification<Player> hasProfession(Profession profession) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("profession"), profession);
        });
    }

    public static Specification<Player> moreExperienceMin(Integer expMin) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("experience"), expMin);
        });
    }

    public static Specification<Player> lessExperienceMax(Integer expMax) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("experience"), expMax);
        });
    }

    public static Specification<Player> moreLevelMin(Integer lvlMin) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("level"), lvlMin);
        });
    }

    public static Specification<Player> lessLevelMax(Integer lvlMax) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("level"), lvlMax);
        });
    }

    public static Specification<Player> hasBanned(Boolean banned) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("banned"), banned);
        });
    }

    public static Specification<Player> moreBefore(Long before) {
        Date bef = new Date(before);

        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), bef);
        });
    }

    public static Specification<Player> lessAfter(Long after) {
        Date aft = new Date(after);
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), aft);
        });
    }






}
