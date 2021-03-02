package com.codeup.spring.models;



import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String body;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(){};

    public Post(String title, String body){
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body, User user, Date date){
        this.title = title;
        this.body = body;
        this.user = user;
        this.date = date;
    }

    public Post(long id, String title, String body){
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post(long id, String title, String body, User user){
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
    }

        public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
