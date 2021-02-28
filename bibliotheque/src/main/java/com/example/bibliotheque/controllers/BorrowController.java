package com.example.bibliotheque.controllers;


import com.example.bibliotheque.models.Book;
import com.example.bibliotheque.models.Borrow;
import com.example.bibliotheque.payload.Responses.MessageResponse;
import com.example.bibliotheque.services.BorrowServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Library")
@CrossOrigin(origins = "http://localhost:4200")
public class BorrowController {


    @Autowired
    private BorrowServices borrowServices;

    //******************************************************************************************************

    // GET ALL  Borrows

    @RequestMapping(value="/borrows",method= RequestMethod.GET)

    public List<Borrow> Get_All_Borrows(){
        return borrowServices.GetAllBorrows();
    }

    //******************************************************************************************************

    // GET ALL  Borrowed Books

    @RequestMapping(value="/borrowedbooks",method= RequestMethod.GET)

    public List<Book> Get_All_Borrowed_Books(){
        return borrowServices.GetAllBorrowedBooks();
    }

    //******************************************************************************************************

    // GET   Borrow BY ID
    @RequestMapping(value="/borrow/{id}",method= RequestMethod.GET)

    public Borrow Get_Borrow_By_ID(@PathVariable Long id){
        return borrowServices.getBorrowByID(id);
    }

    //******************************************************************************************************


    // GET   Borrow BY USER ID

    @RequestMapping(value="/user_borrow",method= RequestMethod.GET)
    public ResponseEntity<?> get_Borrow_By_USER_ID(@RequestParam("user_id") Long user_id){

         return ResponseEntity.ok(borrowServices.getBorrowByUSERID(user_id));
    }

    //******************************************************************************************************

    // GET  NOT ACCEPTED Borrow Requests
    @RequestMapping(value="/borrows/not_accepted",method= RequestMethod.GET)

    public ResponseEntity<?>  Not_Accepted_Borrow_Requests(){

        return ResponseEntity.ok(borrowServices.NotAcceptedBorrowRequests());
    }

    //******************************************************************************************************

    // ACCEPT BORROW REQUEST


    @RequestMapping(value="/accept_borrow",method= RequestMethod.PUT)

    public ResponseEntity<?> AccepteBorrowRequest (@RequestBody Borrow borrow_to_accept){

        return ResponseEntity.ok(borrowServices.AccepteBorrowRequest(borrow_to_accept));


    }



    //******************************************************************************************************
    // ADD Borrowed Book

    @RequestMapping(value="/borrow",method=RequestMethod.POST)

    public ResponseEntity<?> SaveBorrow(@RequestBody Borrow borrow, @RequestParam("user_id") Long user_id) {
        if (borrowServices.SaveBorrow(borrow, user_id)) {
            return ResponseEntity
                    .ok(new MessageResponse("Borrowed Book have been registered successfully!"));
        } else {
            return ResponseEntity.
                    status(HttpStatus.IM_USED)
                    .body(new MessageResponse("This Book is Already Borrowed , Try Borrow another One"));
        }
    }

    //******************************************************************************************************

    // UPDATE Borrow

    @RequestMapping(value="/borrow",method=RequestMethod.PUT)

    public ResponseEntity<?> UpdateBorrow(@RequestBody Borrow borrow) {
        if (borrowServices.UpdateBorrow(borrow)) {
            return ResponseEntity
                    .ok(new MessageResponse("The Borrowed Book have been Updated successfully!"));
        } else {
            return ResponseEntity.
                    status(HttpStatus.IM_USED)
                    .body(new MessageResponse("This Borrowed Book Doesn't exist in our Library"));
        }
    }

    //******************************************************************************************************

    // Delete Borrow

    @RequestMapping(value="/borrow/{id}",method=RequestMethod.DELETE)

    public ResponseEntity<?> deleteBorrow(@PathVariable Long id) {

          borrowServices.deleteBorrow(id) ;
            return ResponseEntity
                    .ok(new MessageResponse("Borrowed Book have been Returned successfully!"));

    }


    //******************************************************************************************************

}
