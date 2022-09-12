package com.game.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    @Enumerated(EnumType.STRING)
    private Race race;

    @Enumerated(EnumType.STRING)
    private Profession profession;

    private Integer experience;

    private Integer level;

    private Integer untilNextLevel;

    private Date birthday;

    private Boolean banned;

    public Player() {

    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", race=" + race +
                ", profession=" + profession +
                ", experience=" + experience +
                ", level=" + level +
                ", untilNextLevel=" + untilNextLevel +
                ", birthday=" + birthday +
                ", banned=" + banned +
                '}';
    }

    public Player(String name, String title, Race race, Profession profession, Integer experience, Date birthday) {

        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.level = calculateLevel(experience);
        this.untilNextLevel = calculateUntilNextLevel(experience, calculateLevel(experience));
        this.birthday = birthday;
        this.banned = false;
    }

    public Player( String name, String title, Race race, Profession profession, Integer experience, Date birthday, Boolean banned) {

        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.level = calculateLevel(experience);
        this.untilNextLevel = calculateUntilNextLevel(experience, calculateLevel(experience));
        this.birthday = birthday;
        this.banned = banned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
        setLevel(experience);
        Integer lev = getLevel();
        setUntilNextLevel(experience, lev);
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer experience) {
        this.level = calculateLevel(experience);
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer experience, Integer level) {
        this.untilNextLevel = calculateUntilNextLevel(experience, level);
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer calculateLevel(Integer exp) {
        double level = (Math.sqrt(2500 + 200*exp)-50)/100;
        Integer lev = (int) level;
        return lev;
    }

    public Integer calculateUntilNextLevel(Integer exp, Integer lvl) {
        return (int) (50*(lvl+1)*(lvl+2)-exp);
    }
}
