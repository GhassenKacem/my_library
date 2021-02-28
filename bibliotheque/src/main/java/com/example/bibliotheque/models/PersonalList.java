package com.example.bibliotheque.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "personallist")
public class PersonalList {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorites")
    List< Book > favorites = new ArrayList< >();
    @OneToMany(cascade = CascadeType.ALL)

    @JoinColumn(name = "to_read")
    List< Book > to_read = new ArrayList< >();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "readed")
    List< Book > readed = new ArrayList< >();

    public PersonalList(User user ) {
        this.user = user;
        this.favorites =  Collections.emptyList();
        this.to_read =  Collections.emptyList();
        this.readed =  Collections.emptyList();
    }

    public PersonalList() {
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Book> favorites) {
        this.favorites = favorites;
    }

    public List<Book> getTo_read() {
        return to_read;
    }

    public void setTo_read(List<Book> to_read) {
        this.to_read = to_read;
    }

    public List<Book> getReaded() {
        return readed;
    }

    public void setReaded(List<Book> readed) {
        this.readed = readed;
    }

    @Override
    public String toString() {
        return "PersonalList{" +
                "id=" + id +
                ", user=" + user +
                ", favorites=" + favorites +
                ", to_read=" + to_read +
                ", readed=" + readed +
                '}';
    }
}
