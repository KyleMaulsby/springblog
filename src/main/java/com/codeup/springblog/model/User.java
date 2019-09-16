package com.codeup.springblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue
    private long id;

    @Column(name= "Username", nullable = false, length = 50)
    private String username;

    @Column(name= "Email", nullable = false, unique = true)
    private String email;

    @Column(name= "Password", nullable = false, length = 200)
    @JsonIgnore
    private String password;

    @Column(nullable = false, name = "Photo", length = 1000)
    private String photo;

    @Column(name = "Bio", length = 1000)
    private String bio;



    public User() {};

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
        photo = copy.photo;
        bio = copy.bio;
    }

    public User(long id, String username, String email, String password, String photo, String bio){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.bio = bio;
    }
    public User(String username, String email, String password, String photo, String bio){
        this.username = username;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.bio = bio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
