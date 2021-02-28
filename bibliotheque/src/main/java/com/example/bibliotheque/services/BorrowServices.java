package com.example.bibliotheque.services;


import com.example.bibliotheque.models.Book;
import com.example.bibliotheque.models.Borrow;
import com.example.bibliotheque.payload.Responses.MessageResponse;
import com.example.bibliotheque.repository.BookRepository;
import com.example.bibliotheque.repository.BorrowRepository;
import com.example.bibliotheque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServices {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;



    //******************************************************************************************************

    // GET ALL  Borrows

    public List<Borrow> GetAllBorrows(){
        return borrowRepository.findAll();
    }

    //******************************************************************************************************

    // GET ALL  Borrowed Books

    public List<Book> GetAllBorrowedBooks(){
        return borrowRepository.findAll().stream().map(borrow -> borrow.getBook()).collect(Collectors.toList());
    }

    //******************************************************************************************************

    // GET   Borrow BY ID

    public Borrow getBorrowByID(Long id){
        return borrowRepository.findById(id).get();
    }

    //******************************************************************************************************


    // GET   Borrow BY USER ID

    public Borrow getBorrowByUSERID(Long user_id){

        return borrowRepository.findAll().stream()
                .filter(borrow -> borrow.getUser().getId() == user_id)
                .collect(Collectors.toList())
                .get(0);
    }

    //******************************************************************************************************



    // GET  NOT ACCEPTED Borrow Requests

    public Borrow NotAcceptedBorrowRequests(){

        return borrowRepository.findAll().stream()
                .filter(borrow -> !borrow.isBorrow_request())
                .collect(Collectors.toList())
                .get(0);
    }

    //******************************************************************************************************




    // ACCEPT BORROW REQUEST

    public MessageResponse AccepteBorrowRequest (Borrow borrowtoaccept){

        borrowtoaccept.setBorrow_request(Boolean.TRUE);
        borrowRepository.save(borrowtoaccept);
        return new MessageResponse("This Borrow Request has been accepted");
    }

    //******************************************************************************************************

    // ADD Borrowed Book

    public Boolean SaveBorrow(Borrow borrow, Long user_id) {

        if(!CheckBorrow(user_id)) {
            System.out.println("This User has not returned the last book he borrow");
            return Boolean.FALSE;
         }
        else {
            borrow.setUser(userRepository.findById(user_id).get());
            Book bookToBorrow = bookRepository.findById(borrow.getBook().getIDBook()).get();
            if (bookToBorrow.getAvailability()) {
                borrowRepository.save(borrow);
                bookToBorrow.setAvailability(Boolean.FALSE);
                bookRepository.save(bookToBorrow);
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
    }

    //******************************************************************************************************


    // Check IF the user haven't return Last book he borrowed

    public Boolean CheckBorrow(Long user_id) {

        List<Borrow> borrowList = borrowRepository.findAll().stream()
                .filter(borrow -> borrow.getUser().getId() == user_id && !borrow.getReturned())
                .collect(Collectors.toList())
                 ;
        if (borrowList.isEmpty()) {
            return  Boolean.TRUE;
        } else {
            return  Boolean.FALSE;
        }

    }

    //******************************************************************************************************

    // UPDATE Borrow

    public Boolean UpdateBorrow(Borrow borrow) {
        if(borrowRepository.findById(borrow.getId()).isPresent()) {
            borrowRepository.saveAndFlush(borrow);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    //******************************************************************************************************

    // Delete Borrow

    public void deleteBorrow(Long id) {
        Book bookToBorrow = bookRepository.findById(borrowRepository.findById(id).get().getBook().getIDBook()).get();

            borrowRepository.deleteById(id);
            bookToBorrow.setAvailability(Boolean.TRUE);
            bookRepository.save(bookToBorrow);

    }


    //******************************************************************************************************

}
