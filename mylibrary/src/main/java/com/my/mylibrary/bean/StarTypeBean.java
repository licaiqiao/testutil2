package com.my.mylibrary.bean;

import java.util.List;

public class StarTypeBean {

    String name;
    List<StarBean> starts;

    int score;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StarBean> getStarts() {
        return starts;
    }

    public void setStarts(List<StarBean> starts) {
        this.starts = starts;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
