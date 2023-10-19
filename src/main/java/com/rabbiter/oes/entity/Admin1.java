package com.rabbiter.oes.entity;

public class Admin1 {
        private Integer adminId;

        private String adminName;

        private String pwd;

        private String cardId;

        private String role;

        public Integer getAdminId() {
            return adminId;
        }

        public void setAdminId(Integer adminId) {
            this.adminId = adminId;
        }

        public String getAdminName() {
            return adminName;
        }

        public void setAdminName(String adminName) {
            this.adminName = adminName == null ? null : adminName.trim();
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd == null ? null : pwd.trim();
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId == null ? null : cardId.trim();
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role == null ? null : role.trim();
        }

        @Override
        public String toString(){
            return "Admin{" +"name:"+adminName+",adminId:"+adminId+"}" ;
        }
}
