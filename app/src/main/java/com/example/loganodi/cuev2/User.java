package com.example.loganodi.cuev2;

public class User {
    private String email;
    private String userName;
    private String phone;
    private Boolean isPremium;
    private Boolean hasCompletedSetup;
    private String profilePicture;
    private String habit;

    public User() {

    }

    public User(String email, String userName, String phone, Boolean isPremium, Boolean hasCompletedSetup, String profilePicture, String habit) {
        this.email = email;
        this.userName = userName;
        this.phone = phone;
        this.isPremium = isPremium;
        this.hasCompletedSetup = hasCompletedSetup;
        this.profilePicture = profilePicture;
        this.habit = habit;

    }

    //SETTERS

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
    }

    public void setHasCompletedSetup(Boolean hasCompletedSetup) {
        this.hasCompletedSetup = hasCompletedSetup;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }
    //GETTERS

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public Boolean getHasCompletedSetup() {
        return hasCompletedSetup;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getHabit() {
        return habit;
    }
}