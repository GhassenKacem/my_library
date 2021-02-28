package com.example.bibliotheque.models;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "borrow")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date endDate;
    private Boolean returned;
    @OneToOne(cascade = CascadeType.MERGE)
    private Book book;
    @OneToOne(cascade = CascadeType.MERGE)
    private User user;
    private boolean borrow_request;


    public Borrow(Long id, Date startDate, Date finDate, Boolean returned, Book book, User user) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = finDate;
        this.returned = returned;
        this.book = book;
        this.user = user;
        this.borrow_request = Boolean.FALSE;
    }

    public Borrow() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isBorrow_request() {
        return borrow_request;
    }

    public void setBorrow_request(boolean borrow_request) {
        this.borrow_request = borrow_request;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", returned=" + returned +
                ", book=" + book +
                ", user=" + user +
                ", borrow_request=" + borrow_request +
                '}';
    }
}
