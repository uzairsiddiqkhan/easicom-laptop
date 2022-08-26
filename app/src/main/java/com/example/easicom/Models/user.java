package com.example.easicom.Models;

public class user {


    // an empty constructor
    public user(){}

    // a sign up constructor --> by using this constructor we will move the data to firebase of a user
    public user(String userName, String email, String password) {

            this.email = email;
            this.password = password;
            this.userName = userName;
        }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    String profilePic, userId, lastMessage, email, password , userName ,status;

    public user(String profilePic, String userId, String lastMessage, String email, String password, String userName, String status) {
        this.profilePic = profilePic;
        this.userId = userId;
        this.lastMessage = lastMessage;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.status = status;
    }

    public void getUserId(String key) { }
}
