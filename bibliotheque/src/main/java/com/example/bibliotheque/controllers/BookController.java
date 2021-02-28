package com.example.bibliotheque.controllers;

import com.example.bibliotheque.models.Book;
import com.example.bibliotheque.models.Comment;
import com.example.bibliotheque.models.Rating;
import com.example.bibliotheque.models.User;
import com.example.bibliotheque.repository.UserRepository;
import com.example.bibliotheque.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/Library")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {
    @Autowired
    private BookServices bookServices;

    @Autowired
    private UserRepository userRepository;


    //******************************************************************************************************

    // GET ALL  Books

    @RequestMapping(value="/books",method= RequestMethod.GET)
    public List<Book> GetAllBooks() {

        return bookServices.GetAllBooks();

    }

    //******************************************************************************************************

    //GET ONE Books BY CIN

    @RequestMapping(value="/book/{id}",method=RequestMethod.GET)

    public ResponseEntity<Book> GetBookByID(@PathVariable Long id) {

        return ResponseEntity.ok().body(bookServices.getBookByID(id));

    }

    //******************************************************************************************************

    // ADD Book

    @RequestMapping(value="/book",method=RequestMethod.POST)
     public void addBook(@RequestBody Book book) {

        try {
        bookServices.SaveBook(book);
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    //******************************************************************************************************

    // UPDATE Book

    @RequestMapping(value="/book",method=RequestMethod.PUT)

    public void UpdateBook(@RequestBody Book book) {

        bookServices.UpdateBook(book);

    }

    //******************************************************************************************************

    // Delete  Book

    @RequestMapping(value="/book/{id}",method=RequestMethod.DELETE)

    public void DeleteBook(@PathVariable Long id) {

        bookServices.deleteBook(id);
    }

    //******************************************************************************************************

    // Comments

    //******************************************************************************************************

    // GET ALL  Comments by book ID

    @RequestMapping(value="/book/{id}/comments",method= RequestMethod.GET)
    public ResponseEntity<List<Comment>> Get_All_Comments(@PathVariable Long id) {

        return ResponseEntity.ok().body(bookServices.GetAllComments(id));

    }


    // ADD  Comments by book ID

    @RequestMapping(value="/book/{id}/comments",method= RequestMethod.POST)
    public void ADD_Comments(@PathVariable Long id,@RequestBody Comment comment, @RequestParam("username") String username) {
        bookServices.ADDComment(id, comment, username);
    }

    // Delete  Comment by book ID

    @RequestMapping(value="/book/{id}/comment/{comment_id}",method= RequestMethod.DELETE)
    public void Delete_Comment(@PathVariable Long id, @PathVariable Long comment_id) {
         bookServices.DelteComment(id, comment_id);
    }

    //******************************************************************************************************

    // Ratings

    //******************************************************************************************************

    // GET ALL  Ratings by book ID

    @RequestMapping(value="/book/{id}/ratings",method= RequestMethod.GET)
    public ResponseEntity<List<Rating>> Get_All_Rating(@PathVariable Long id) {

        return ResponseEntity.ok().body(bookServices.GetAllRatings(id));

    }

    // GET   Rating Average by book ID

    @RequestMapping(value="/book/{id}/rating",method= RequestMethod.GET)
    public ResponseEntity<Double> Get_All_Rating_Average(@PathVariable Long id) {

        Long RatingSUM = bookServices.GetAllRatings(id).stream()
                                      .mapToLong(rating -> rating.getRate())
                                      .sum();
        Long RatingCOUNT = bookServices.GetAllRatings(id).stream().count();


        return ResponseEntity.ok().body(Double.valueOf(RatingSUM)/RatingCOUNT);

    }


    // ADD  Ratings by book ID

    @RequestMapping(value="/book/{id}/ratings",method= RequestMethod.POST)
    public void ADD_Ratings(@PathVariable Long id,@RequestBody Rating rating, @RequestParam("username") String username) {
        bookServices.ADDRating(id, rating, username);
    }

    // Delete  Rating by book ID

    @RequestMapping(value="/book/{id}/rating/{rating_id}",method= RequestMethod.DELETE)
    public void Delete_Rating(@PathVariable Long id, @PathVariable Long rating_id) {
        bookServices.DelteRating(id, rating_id);
    }








}
