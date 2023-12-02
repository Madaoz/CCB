package com.rabbiter.oes.entity;

public class Option {

    private String pjId;

    private String bpjId;

    private int quId;

    private String qu;

    private String quMark;

    private String quMarkName;

    private int option;

    private String score;

    public String getPjId() {
        return pjId;
    }

    public void setPjId(String pjId) {
        this.pjId = pjId;
    }

    public String getBpjId() {
        return bpjId;
    }

    public void setBpjId(String bpjId) {
        this.bpjId = bpjId;
    }

    public String getQu() {
        return qu;
    }

    public void setQu(String qu) {
        this.qu = qu;
    }

    public String getQuMark() {
        return quMark;
    }

    public void setQuMark(String quMark) {
        this.quMark = quMark;
    }

    public String getQuMarkName() {
        return quMarkName;
    }

    public void setQuMarkName(String quMarkName) {
        this.quMarkName = quMarkName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getQuId() {
        return quId;
    }

    public void setQuId(int quId) {
        this.quId = quId;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
}
