package com.codeup.springblog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Posts")
public class Post {
    @Id @GeneratedValue
    private long id;

    @Column(nullable = false, name = "Title")
    private String title;

    @Column(nullable = false, name = "Body", length = 5000)
    private String body;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    @JsonBackReference
    private List<Photo> photos;

    @OneToOne
    private User user;

    public Post (){};

    public Post (Post copy){
        id = copy.id;
        title = copy.title;
        body = copy.body;
        user = copy.user;
        photos = copy.photos;
    };

    public Post (long id, String title, String body, User user, List<Photo> photos){
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
        this.photos = photos;
    };
    public Post (String title, String body, User user, List<Photo> photos){
        this.title = title;
        this.body = body;
        this.user = user;
        this.photos = photos;
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
