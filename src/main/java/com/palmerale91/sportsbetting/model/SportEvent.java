package com.palmerale91.sportsbetting.model;

import java.time.ZonedDateTime;

public class SportEvent {
    private int Id;
    private String name;
    private Double odds1stTeam;
    private Double oddsForDraw;
    private Double odds2ndTeam;
    private ZonedDateTime date;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getOdds1stTeam() {
        return odds1stTeam;
    }

    public void setOdds1stTeam(Double odds1stTeam) {
        this.odds1stTeam = odds1stTeam;
    }

    public Double getOddsForDraw() {
        return oddsForDraw;
    }

    public void setOddsForDraw(Double oddsForDraw) {
        this.oddsForDraw = oddsForDraw;
    }

    public Double getOdds2ndTeam() {
        return odds2ndTeam;
    }

    public void setOdds2ndTeam(Double odds2ndTeam) {
        this.odds2ndTeam = odds2ndTeam;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
}
