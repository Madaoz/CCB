package com.rabbiter.oes.entity;

public class OtherScore {

    private String pjid;

    private String bpjid;

    private String score;

    private String scoreA;

    private String scoreB;

    private String scoreC;

    private String scoreD;

    private String scoreE;

    public String getScoreA() {
        return scoreA;
    }

    public void setScoreA(String scoreA) {
        this.scoreA = scoreA;
    }

    public String getScoreB() {
        return scoreB;
    }

    public void setScoreB(String scoreB) {
        this.scoreB = scoreB;
    }

    public String getScoreC() {
        return scoreC;
    }

    public void setScoreC(String scoreC) {
        this.scoreC = scoreC;
    }

    public String getScoreD() {
        return scoreD;
    }

    public void setScoreD(String scoreD) {
        this.scoreD = scoreD;
    }

    public String getScoreE() {
        return scoreE;
    }

    public void setScoreE(String scoreE) {
        this.scoreE = scoreE;
    }

    public String getPjid() {
        return pjid;
    }

    public void setPjid(String pjid) {
        this.pjid = pjid;
    }

    public String getBpjid() {
        return bpjid;
    }

    public void setBpjid(String bpjid) {
        this.bpjid = bpjid;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "info{   " + "    " + "pjid= " + pjid + "    " + "bpjid= " + bpjid + "score = " + score + "A = " + scoreA +
                "    " + "B = " + scoreB + "   C = " + scoreC + "  D = " + scoreD + "  E = " + scoreE + '}';
    }
}
