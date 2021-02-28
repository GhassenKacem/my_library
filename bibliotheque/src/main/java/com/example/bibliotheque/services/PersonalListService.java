package com.example.bibliotheque.services;


import com.example.bibliotheque.models.Book;
import com.example.bibliotheque.models.Borrow;
import com.example.bibliotheque.models.PersonalList;
 import com.example.bibliotheque.models.User;
import com.example.bibliotheque.payload.Responses.MessageResponse;
import com.example.bibliotheque.repository.PersonalListRepository;
 import com.example.bibliotheque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonalListService {

    @Autowired
    private PersonalListRepository personnalListRepository;

    @Autowired
    private UserRepository userRepository;



    //******************************************************************************************************

    // GET ALL  Personnal Lists

    public List<PersonalList> GetAllPersonnalLists(){
        return personnalListRepository.findAll();
    }

    //******************************************************************************************************

    //******************************************************************************************************

    // GET   Personnal List BY USER ID

    public PersonalList getPersonnalListByID(Long user_id){

        return  personnalListRepository.findAll()
                .stream()
                .filter(personnalList -> personnalList.getUser().getId() == user_id)
                .collect(Collectors.toList())
                .get(0);

     }

    //******************************************************************************************************
    //******************************************************************************************************

    // ADD Personnal List

    public boolean SavePersonnalList(PersonalList personnalList) {

         if(!personnalListRepository.findAll().stream()
                 .filter(personalList -> personalList.getUser().getId() ==personalList.getUser().getId())
                 .collect(Collectors.toList())
                 .isEmpty()) {
             System.out.println("no");
             return Boolean.TRUE ;

         } else {
             System.out.println(personnalList.toString());
             personnalListRepository.save(personnalList);
             return Boolean.FALSE;

         }
    }

    //******************************************************************************************************
    //******************************************************************************************************

    // UPDATE Personnal List

    public Boolean UpdatePersonnalList(PersonalList personnalList) {
        if(personnalListRepository.findAll().stream()
                .filter(personalList -> personalList.getUser().getId() ==personalList.getUser().getId())
                .collect(Collectors.toList())
                .isEmpty()) {
            personnalListRepository.saveAndFlush(personnalList);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    //******************************************************************************************************
    //******************************************************************************************************

    // Delete Personnal List

    public void DeletePersonnalList(Long user_id) {
        PersonalList personnalList = personnalListRepository.findAll().stream()
                .filter(personalList -> personalList.getUser().getId() == user_id)
                .collect(Collectors.toList())
                .get(0);
        personnalListRepository.delete(personnalList);


    }


    //******************************************************************************************************

    //******************************************************************************************************

        //Favorites

    //******************************************************************************************************

    //******************************************************************************************************


    // GET   Favorites Books List BY USER ID

    public List<Book> getFavoritesBooksListByUserID(Long user_id){

        return  personnalListRepository.findAll()
                .stream()
                .filter(personnalList -> personnalList.getUser().getId() == user_id)
                .map(personnalList -> personnalList.getFavorites())
                .collect(Collectors.toList())
                .get(0);

    }

    //******************************************************************************************************
    //******************************************************************************************************

    // ADD a book to Favorites List

    public MessageResponse SaveFavoritesBook(Book favoritebook, Long user_id) {

        PersonalList personnalList = personnalListRepository
                                     .findAll().stream()
                                     .filter(personalList -> personalList.getUser().getId() == user_id)
                                     .collect(Collectors.toList())
                                     .get(0)
                                    ;
        personnalList.getFavorites().add(favoritebook);
        personnalListRepository.save(personnalList);
        return new MessageResponse(favoritebook.getTitle()+" Has Been Added To you Favorites Book List");
    }

    //******************************************************************************************************
    //******************************************************************************************************

    //******************************************************************************************************
    //******************************************************************************************************

    // Delete a book from Favorites List

    public MessageResponse DeleteFavoritesBook(Book favoritebook, Long user_id) {

        PersonalList personnalList = personnalListRepository
                .findAll().stream()
                .filter(personalList -> personalList.getUser().getId() == user_id)
                .collect(Collectors.toList())
                .get(0)
                ;
        System.out.println(personnalList.getFavorites().removeIf(book -> book.getIDBook() == favoritebook.getIDBook()));
        personnalListRepository.saveAndFlush(personnalList);
        return new MessageResponse(favoritebook.getTitle()+" Has Been Deleted From your Favorites Book List");
    }


    //******************************************************************************************************

    //******************************************************************************************************

    //TO_READ

    //******************************************************************************************************

    //******************************************************************************************************


    // GET   TO_READ Books List BY USER ID

    public List<Book> getToReadBooksListByUserID(Long user_id){

        return  personnalListRepository.findAll()
                .stream()
                .filter(personnalList -> personnalList.getUser().getId() == user_id)
                .map(personnalList -> personnalList.getTo_read())
                .collect(Collectors.toList())
                .get(0);

    }

    //******************************************************************************************************
    //******************************************************************************************************

    // ADD a book to TO_READ List

    public MessageResponse SaveToReadBook(Book to_read_book, Long user_id) {

        PersonalList personnalList = personnalListRepository
                .findAll().stream()
                .filter(personalList -> personalList.getUser().getId() == user_id)
                .collect(Collectors.toList())
                .get(0)
                ;
        personnalList.getTo_read().add(to_read_book);
        personnalListRepository.save(personnalList);
        return new MessageResponse(to_read_book.getTitle()+" Has Been Added To yours To Read Book List");
    }

    //******************************************************************************************************
    //******************************************************************************************************

    //******************************************************************************************************
    //******************************************************************************************************

    // Delete a book from TO_READ List

    public MessageResponse DeleteToReadBook(Book to_read_book, Long user_id) {

        PersonalList personnalList = personnalListRepository
                .findAll().stream()
                .filter(personalList -> personalList.getUser().getId() == user_id)
                .collect(Collectors.toList())
                .get(0)
                ;
        personnalList.getTo_read().removeIf(book -> book.getIDBook() == to_read_book.getIDBook());
        personnalListRepository.save(personnalList);
        return new MessageResponse(to_read_book.getTitle()+" Has Been Deleted From yours To Read Book List");
    }

    //******************************************************************************************************
    //******************************************************************************************************

    // READED

    //******************************************************************************************************

    //******************************************************************************************************


    // GET   READED Books List BY USER ID

    public List<Book> getToReadedBooksListByUserID(Long user_id){

        return  personnalListRepository.findAll()
                .stream()
                .filter(personnalList -> personnalList.getUser().getId() == user_id)
                .map(personnalList -> personnalList.getReaded())
                .collect(Collectors.toList())
                .get(0);

    }

    //******************************************************************************************************
    //******************************************************************************************************

    // ADD a book to READED List

    public MessageResponse SaveReadedBook(Book readed_book, Long user_id) {

        PersonalList personnalList = personnalListRepository
                .findAll().stream()
                .filter(personalList -> personalList.getUser().getId() == user_id)
                .collect(Collectors.toList())
                .get(0)
                ;
        personnalList.getReaded().add(readed_book);
        personnalListRepository.save(personnalList);
        return new MessageResponse(readed_book.getTitle()+" Has Been Added To yours  Readed Book List");
    }

    //******************************************************************************************************
    //******************************************************************************************************

    //******************************************************************************************************
    //******************************************************************************************************

    // Delete a book from READED List

    public MessageResponse DeleteReadedBook(Book readed_book, Long user_id) {

        PersonalList personnalList = personnalListRepository
                .findAll().stream()
                .filter(personalList -> personalList.getUser().getId() == user_id)
                .collect(Collectors.toList())
                .get(0)
                ;
        personnalList.getReaded().removeIf(book -> book.getIDBook() == readed_book.getIDBook());
        personnalListRepository.save(personnalList);
        return new MessageResponse(readed_book.getTitle()+" Has Been Deleted From yours Readed Book List");
    }

    //******************************************************************************************************
    //******************************************************************************************************



}
