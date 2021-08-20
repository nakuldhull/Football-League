package com.stackroute.oops.league.service;

/**
 * Enum to store the four team titles
 * Contains one field description and a parameterized constructor to initialize it
 * Modify this code by adding description to each enum constants
 */
public enum LeagueTeamTitles {
    HIPHOP(hiphop), WIN2WIN(win2win),
    HAPPYFEET("Happy feet"), LUCKYSTRIKE("Lucky strike");
    private String teamTile;

    private LeagueTeamTitles(String teamTitle){
        this.teamTile=teamTitle;
    }

    public String getValue(){
        return this.teamTile;
    }

}
