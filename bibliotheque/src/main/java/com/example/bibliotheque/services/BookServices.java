package com.example.bibliotheque.services;


import com.example.bibliotheque.models.Book;
import com.example.bibliotheque.models.Comment;
import com.example.bibliotheque.models.Rating;
import com.example.bibliotheque.models.User;
import com.example.bibliotheque.repository.BookRepository;
import com.example.bibliotheque.repository.CommentRepository;
import com.example.bibliotheque.repository.RatingRepository;
import com.example.bibliotheque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
public class BookServices {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RatingRepository ratingRepository;

    //******************************************************************************************************

    // GET ALL  BOOKS

    public List<Book> GetAllBooks(){
         return bookRepository.findAll();
    }

    //******************************************************************************************************

    // GET Book BY ID

    public Book getBookByID(Long id){
        return bookRepository.findById(id).get();
    }

    //******************************************************************************************************

    // ADD Book

    public void SaveBook(Book book) {
        try {
            bookRepository.save(book);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //******************************************************************************************************

    // UPDATE Book

    public void UpdateBook(Book book) {

        bookRepository.saveAndFlush(book);
    }

    //******************************************************************************************************

    // Delete Book

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }


    //******************************************************************************************************

    // Comments

    //******************************************************************************************************

    // GET ALL  Comments by book ID

    public List<Comment> GetAllComments(Long id) {

        return bookRepository.findById(id).get().getComments();

    }

    // ADD  Comment  by book ID

    public void ADDComment(Long id, Comment comment, String username) {
         User user = userRepository.findByUsername(username).get();
         comment.setUser(user);
         Book book = bookRepository.findById(id).get();
         book.getComments().add(comment);
         bookRepository.save(book);

    }

    // Delete  Comment by book ID

    public void DelteComment(Long id, long comment_id) {
        Book book = bookRepository.findById(id).get();
        book.getComments().removeIf(comment -> comment.getId() == comment_id);
        bookRepository.save(book);
        commentRepository.deleteById(comment_id);
    }

    //******************************************************************************************************

    // Rating

    //******************************************************************************************************

    // GET ALL  Comments by book ID

    public List<Rating> GetAllRatings(Long id) {

        return bookRepository.findById(id).get().getRatings();

    }

    // ADD  Comment  by book ID

    public void ADDRating(Long id, Rating rating, String username) {
        User user = userRepository.findByUsername(username).get();
        rating.setUser(user);
        Book book = bookRepository.findById(id).get();
        book.getRatings().add(rating);
        bookRepository.save(book);

    }

    // Delete  Comment by book ID

    public void DelteRating(Long id, long rating_id) {
        Book book = bookRepository.findById(id).get();
        book.getRatings().removeIf(rating -> rating.getId() == rating_id);
        bookRepository.save(book);
        ratingRepository.deleteById(rating_id);
    }

}
