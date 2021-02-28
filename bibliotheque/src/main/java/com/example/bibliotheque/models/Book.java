package com.example.bibliotheque.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IDBook;
    private String Title;
    private int PrintLength ;
    private int Price;
    private String Author;
    private String Publisher ;
    private Date PublicationDate ;
    private String Language  ;
    private String Description;
    private Boolean Availability;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comments")
    List< Comment > comments = new ArrayList< >();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ratings")
    List< Rating > ratings = new ArrayList< >();



    public Book(String title, int printLength, int price, String author, String publisher, Date publicationDate, String language, String description, Boolean availability) {
        Title = title;
        PrintLength = printLength;
        Price = price;
        Author = author;
        Publisher = publisher;
        PublicationDate = publicationDate;
        Language = language;
        Description = description;
        Availability = availability;
    }


    public Book() {
    }


    public Long getIDBook() {
        return IDBook;
    }

    public void setIDBook(Long IDBook) {
        this.IDBook = IDBook;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getPrintLength() {
        return PrintLength;
    }

    public void setPrintLength(int printLength) {
        PrintLength = printLength;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public Date getPublicationDate() {
        return PublicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        PublicationDate = publicationDate;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Boolean getAvailability() {
        return Availability;
    }

    public void setAvailability(Boolean availability) {
        Availability = availability;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "Book{" +
                "IDBook=" + IDBook +
                ", Title='" + Title + '\'' +
                ", PrintLength=" + PrintLength +
                ", Price=" + Price +
                ", Author='" + Author + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", PublicationDate=" + PublicationDate +
                ", Language='" + Language + '\'' +
                ", Description='" + Description + '\'' +
                ", comments=" + comments +
                ", ratings=" + ratings +
                '}';
    }
}
