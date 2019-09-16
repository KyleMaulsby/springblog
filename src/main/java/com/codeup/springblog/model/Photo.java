package com.codeup.springblog.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "PostPhotos")
public class Photo {

    @Id @GeneratedValue
    private long id;

    @Column(name = "photo", nullable = false, length = 1000)
    private String photo;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "post_id")
    private Post post;


    public Photo (){};

    public Photo (long id, String photo, Post post){
        this.id = id;
        this.photo = photo;
        this.post = post;
    }
    public Photo (String photo, Post post){
        this.photo = photo;
        this.post = post;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
