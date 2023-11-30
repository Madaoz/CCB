package com.rabbiter.oes.entity;

public class SelfScore {

    String name;

    String userId;

    String selfevaluation;

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
        return "info{   " + "    " + "name= " + name + "    " + "userId= " + userId + "    " + "selfevaluation= " + selfevaluation + '}';
    }
}
