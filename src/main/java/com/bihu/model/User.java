package com.bihu.model;

/**
 * Created by wanganyu on 2017/12/17.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String salt;
    private String headUrl;
    private int activationStatus;
    private String activationCode;
    private Long createdTime;
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", salt='" + salt + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", activationStatus=" + activationStatus +
                ", activationCode='" + activationCode + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }


    public User() {
    }

    public User(int id, String username, String password, String email, String salt, String headUrl, int activationStatus, String activationCode, Long createdTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.salt = salt;
        this.headUrl = headUrl;
        this.activationStatus = activationStatus;
        this.activationCode = activationCode;
        this.createdTime = createdTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(int activationStatus) {
        this.activationStatus = activationStatus;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }



}
