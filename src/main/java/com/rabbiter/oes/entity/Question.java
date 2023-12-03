package com.rabbiter.oes.entity;

public class Question {
    private String id;
    private String qu;
    private String role;
    private String quMark;
    private String quMarkName;

    public String getQuMarkName() {
        return quMarkName;
    }

    public void setQuMarkName(String quMarkName) {
        this.quMarkName = quMarkName;
    }

    public String getQuMark() {
        return quMark;
    }

    public void setQuMark(String quMark) {
        this.quMark = quMark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQu() {
        return qu;
    }

    public void setQu(String qu) {
        this.qu = qu;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "info{   " + "    " + "id= " + id + "    " + "qu= " + qu + "    " + "role= " + role + "  quMark = " + quMark + '}';
    }
}
