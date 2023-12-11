package com.rabbiter.oes.entity;

public class SelfScore {

    private String name;

    private String userId;

    private String selfevaluation;

    private String selfA;

    private String selfB;

    private String selfC;

    private String selfD;

    private String selfE;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSelfevaluation() {
        return selfevaluation;
    }

    public void setSelfevaluation(String selfevaluation) {
        this.selfevaluation = selfevaluation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SelfScore-info{   " + "    " + "name= " + name + "    " + "userId= " + userId + "    " + "selfevaluation= " + selfevaluation +
                "  selfA = " + selfA + "  selfB = " + selfB + "  selfC = " + selfC + "  selfD = " + selfD + "  selfE = " + selfE + '}';
    }
}
