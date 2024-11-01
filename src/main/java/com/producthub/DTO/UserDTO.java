package com.producthub.DTO;

import com.producthub.enums.Profile;

import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private boolean active;
    private List<Profile> profiles;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }
    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }
}
