package com.rabbiter.oes.entity;

public class BpjPerson {

    /**
     * 与score_manage表字段对应
     */
    private String bpjName;

    private String bpjId;

    private String bpjUass;

    private String score;

    /**
     * 与leaderinfo表字段对应
     */
    private String id;

    private String name;

    private String uass;
    //上级评价平均分
    private String superior;
    //同级评价平均分
    private String equal;
    //下级评价平均分
    private String subordinate;
    //上级评价人数
    private String superiorNm;
    //同级评价人数
    private String equalNm;
    //下级评价人数
    private String subordinateNm;
    //自评价得分
    private String selfevaluation;
    //总得分
    private String totalscore;
    //总评价人数
    private String totalNm;

    public String getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(String totalscore) {
        this.totalscore = totalscore;
    }

    public String getTotalNm() {
        return totalNm;
    }

    public void setTotalNm(String totalNm) {
        this.totalNm = totalNm;
    }

    private String filePath;

    private String fileName;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUass() {
        return uass;
    }

    public void setUass(String uass) {
        this.uass = uass;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public String getEqual() {
        return equal;
    }

    public void setEqual(String equal) {
        this.equal = equal;
    }

    public String getSubordinate() {
        return subordinate;
    }

    public void setSubordinate(String subordinate) {
        this.subordinate = subordinate;
    }

    public String getSuperiorNm() {
        return superiorNm;
    }

    public void setSuperiorNm(String superiorNm) {
        this.superiorNm = superiorNm;
    }

    public String getEqualNm() {
        return equalNm;
    }

    public void setEqualNm(String equalNm) {
        this.equalNm = equalNm;
    }

    public String getSubordinateNm() {
        return subordinateNm;
    }

    public void setSubordinateNm(String subordinateNm) {
        this.subordinateNm = subordinateNm;
    }

    public String getSelfevaluation() {
        return selfevaluation;
    }

    public void setSelfevaluation(String selfevaluation) {
        this.selfevaluation = selfevaluation;
    }

    public String getBpjName() {
        return bpjName;
    }

    public void setBpjName(String bpjName) {
        this.bpjName = bpjName;
    }

    public String getBpjId() {
        return bpjId;
    }

    public void setBpjId(String bpjId) {
        this.bpjId = bpjId;
    }

    public String getBpjUass() {
        return bpjUass;
    }

    public void setBpjUass(String bpjUass) {
        this.bpjUass = bpjUass;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
