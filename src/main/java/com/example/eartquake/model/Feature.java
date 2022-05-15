package com.example.eartquake.model;

import java.io.Serializable;

public class Feature implements Serializable {
    private String type;
    private UsgsProperties properties;
    private UsgsGeometry geometry;
    private String id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UsgsProperties getProperties() {
        return properties;
    }

    public void setProperties(UsgsProperties properties) {
        this.properties = properties;
    }

    public UsgsGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(UsgsGeometry geometry) {
        this.geometry = geometry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
