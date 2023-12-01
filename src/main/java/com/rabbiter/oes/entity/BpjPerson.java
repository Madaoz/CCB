package com.rabbiter.oes.entity;

public class BpjPerson {

    /**
     * 与score_manage表字段对应
     */
    private String pjId;

    private String bpjName;

    private String bpjId;

    private String bpjUass;

    private String score;

    private String level;

    private String submit;

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
    //所属机构
    private String instName;

    private String scoreA;

    private String scoreB;

    private String scoreC;

    private String scoreD;

    private String scoreE;

    private String markTotalScore;

    private String selfA;

    private String selfB;

    private String selfC;

    private String selfD;

    private String selfE;

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

    public String getSelfA() {
        return selfA;
    }

    public void setSelfA(String selfA) {
        this.selfA = selfA;
    }

    public String getSelfB() {
        return selfB;
    }

    public void setSelfB(String selfB) {
        this.selfB = selfB;
    }

    public String getSelfC() {
        return selfC;
    }

    public void setSelfC(String selfC) {
        this.selfC = selfC;
    }

    public String getSelfD() {
        return selfD;
    }

    public void setSelfD(String selfD) {
        this.selfD = selfD;
    }

    public String getSelfE() {
        return selfE;
    }

    public void setSelfE(String selfE) {
        this.selfE = selfE;
    }

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

    public String getMarkTotalScore() {
        return markTotalScore;
    }

    public void setMarkTotalScore(String markTotalScore) {
        this.markTotalScore = markTotalScore;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPjId() {
        return pjId;
    }

    public void setPjId(String pjId) {
        this.pjId = pjId;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
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

    @Override
    public String toString() {
        return "info{  " + "pjId= " + pjId + "    " + "bpjName= " + bpjName + "    " + "bpjId= " + bpjId + "    " + "bpjUass" +
                bpjUass + "    " + "score= " + score + "    " + "id= " + id + "    " + "name= " + name + "    " + "uass= " + uass + "    " +
                "selfevaluation= " + selfevaluation + "    " + "superior= " + superior + "    " + "superiorNm= " + superiorNm +
                "    " + "equal= " + equal + "    " + "equalNm= " + equalNm + "    " + "" + "subordinate= " + subordinate + "    " +
                "subordinateNm= " + subordinateNm + "    " + "totalscore= " + totalscore + "    " + "totalNm= " + totalNm + "    " +
                "instName= " + instName + "scoreA = " + scoreA + "    scoreB = " + scoreB + "   scoreC = " + scoreC + "   scoreD = " +
                scoreD + "   scoreE = " + scoreE + '}';
    }
}
