package com.example.eartquake.model.request;

public class EarthQuakeListRequest {
    private String countryName;
    private Integer countOfPastDays;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getCountOfPastDays() {
        return countOfPastDays;
    }

    public void setCountOfPastDays(Integer countOfPastDays) {
        this.countOfPastDays = countOfPastDays;
    }
}
