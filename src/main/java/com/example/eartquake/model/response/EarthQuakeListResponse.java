package com.example.eartquake.model.response;

import java.util.List;

public class EarthQuakeListResponse {
    private List<EarthQuakeInfo> earthQuakeInfoList;

    public List<EarthQuakeInfo> getEarthQuakeInfoList() {
        return earthQuakeInfoList;
    }

    public void setEarthQuakeInfoList(List<EarthQuakeInfo> earthQuakeInfoList) {
        this.earthQuakeInfoList = earthQuakeInfoList;
    }
}
