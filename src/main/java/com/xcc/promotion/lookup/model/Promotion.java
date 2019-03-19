package com.xcc.promotion.lookup.model;

import java.io.Serializable;

public class Promotion implements Serializable {
    private static final long serialVersionUID = -7788619177798333712L;

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
