package com.codeup.spring.dao;

class Config {
    public String getUrl() {
        return "jdbc:mysql://localhost/spring_blog_db?serverTimezone=UTC&useSSL=false";
    }
    public String getUser() {
        return "root";
    }
    public String getPassword() {
        return "codeup";
    }


}
