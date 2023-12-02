package com.rabbiter.oes.entity;

public class User {
    //用户姓名
    private String userName;
    //8位员工编号
    private String userId;
    //密码
    private String passWord;
    //UASS编号
    private String userUass;
    //所属机构号
    private String userInstName;
    //角色标识（0：管理员，2：用户）
    private String role;
    //职等
    private String grade;

    private String score;

    private String pjId;

    private String pjName;

    private String pjInstname;

    private String pjUass;

    public String getPjId() {
        return pjId;
    }

    public void setPjId(String pjId) {
        this.pjId = pjId;
    }

    public String getPjName() {
        return pjName;
    }

    public void setPjName(String pjName) {
        this.pjName = pjName;
    }

    public String getPjInstname() {
        return pjInstname;
    }

    public void setPjInstname(String pjInstname) {
        this.pjInstname = pjInstname;
    }

    public String getPjUass() {
        return pjUass;
    }

    public void setPjUass(String pjUass) {
        this.pjUass = pjUass;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserUass() {
        return userUass;
    }

    public void setUserUass(String userUass) {
        this.userUass = userUass;
    }

    public String getUserInstName() {
        return userInstName;
    }

    public void setUserInstName(String userInstName) {
        this.userInstName = userInstName;
    }

    @Override
    public String toString(){
        return "info{   "+"userName= "+userName+"    "+"userId= "+userId+"    "+"userUass= "+userUass+"    "+
                "userInstName= "+userInstName+"    "+"role= "+role+"    "+"pjId= "+pjId+"    "+"pjName= "+pjName+
                "    "+"pjUass= "+pjUass+"    "+"pjInstname= "+pjInstname+'}';
    }
}
