package com.example.eartquake.model;

import java.io.Serializable;
import java.util.List;

public class UsgsResponse implements Serializable {
    private List<Feature> features;

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

}