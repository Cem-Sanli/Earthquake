package com.example.eartquake.model;

import java.io.Serializable;

public class UsgsProperties implements Serializable {
    private Double mag;
    private String place;
    private Long time;

    public Double getMag() {
        return mag;
    }

    public void setMag(Double mag) {
        this.mag = mag;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}