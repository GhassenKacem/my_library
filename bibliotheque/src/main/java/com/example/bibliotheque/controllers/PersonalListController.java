package com.example.bibliotheque.controllers;


import com.example.bibliotheque.models.Book;
import com.example.bibliotheque.models.PersonalList;
import com.example.bibliotheque.models.User;
import com.example.bibliotheque.payload.Responses.MessageResponse;
import com.example.bibliotheque.services.PersonalListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Library")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonalListController {


    @Autowired
    private PersonalListService personalListService;


    //******************************************************************************************************

    // GET ALL  Personnal Lists

    @RequestMapping(value="/all_personal_list",method= RequestMethod.GET)
    public List<PersonalList> Get_All_Personnal_Lists(){
        return personalListService.GetAllPersonnalLists();
    }

    //******************************************************************************************************

    //******************************************************************************************************

    // GET   Personnal List BY USER ID

    @RequestMapping(value="/personal_list",method= RequestMethod.GET)
    public ResponseEntity<PersonalList> get_Personnal_List_By_ID(@RequestParam("user_id") Long user_id){

        return  ResponseEntity.ok(personalListService.getPersonnalListByID(user_id));
    }

    //******************************************************************************************************
    //******************************************************************************************************

    // ADD Personnal List

    @RequestMapping(value="/personal_list",method= RequestMethod.POST)
    public ResponseEntity<?> Save_Personnal_List(@RequestBody User user) {

        PersonalList personnalList = new PersonalList(user);


        if(personalListService.SavePersonnalList(personnalList)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body( new MessageResponse("This User already Have a Personal List"));

        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body( new MessageResponse("Personal List have been Created For the User "
                            + personnalList.getUser().getUsername()));

        }
    }

    //******************************************************************************************************
    //******************************************************************************************************

    // UPDATE Personnal List
    @RequestMapping(value="/personal_list",method= RequestMethod.PUT)
    public ResponseEntity<?> Update_Personnal_List(@RequestBody PersonalList personnalList) {
            if(personalListService.UpdatePersonnalList(personnalList)) {
                return  ResponseEntity.ok("Your Personal List has Been Updated successfully");

            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body( new MessageResponse("User doesn't exist"));        }
    }

    //******************************************************************************************************
    //******************************************************************************************************

    // Delete Personnal List by user id

    @RequestMapping(value="/personal_list",method= RequestMethod.DELETE)
    public ResponseEntity<?> Delete_Personnal_List(@RequestParam("user_id") Long user_id) {
        personalListService.DeletePersonnalList(user_id);
        return  ResponseEntity.ok("This Personal List has Been Deleted successfully");

    }


    //******************************************************************************************************

    //******************************************************************************************************

    //Favorites

    //******************************************************************************************************

    //******************************************************************************************************


    // GET   Favorites Books List BY USER ID


    @RequestMapping(value="/personal_list/favorites",method= RequestMethod.GET)
    public List<Book> get_Favorites_Books_List_By_UserID(@RequestParam("user_id") Long user_id){

        return  personalListService.getFavoritesBooksListByUserID(user_id);

    }

    //******************************************************************************************************
    //******************************************************************************************************

    // ADD a book to Favorites List
    @RequestMapping(value="/personal_list/favorites",method= RequestMethod.POST)
    public ResponseEntity<MessageResponse> Save_Favorites_Book(@RequestBody Book favoritebook,@RequestParam("user_id")  Long user_id) {

        return ResponseEntity.ok(personalListService.SaveFavoritesBook(favoritebook, user_id));
    }

    //******************************************************************************************************
    //******************************************************************************************************

    //******************************************************************************************************
    //******************************************************************************************************

    // Delete a book from Favorites List

    @RequestMapping(value="/personal_list/favorites",method= RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> Delete_Favorites_Book(@RequestBody Book favoritebook,@RequestParam("user_id")  Long user_id) {

        return ResponseEntity.ok(personalListService.DeleteFavoritesBook(favoritebook, user_id));

    }


    //******************************************************************************************************

    //******************************************************************************************************

    //TO_READ

    //******************************************************************************************************

    //******************************************************************************************************


    // GET   TO_READ Books List BY USER ID

    @RequestMapping(value="/personal_list/to_read",method= RequestMethod.GET)
    public List<Book> get_ToRead_Books_List_By_User_ID(@RequestParam("user_id") Long user_id){

        return  personalListService.getToReadBooksListByUserID(user_id);

    }

    //******************************************************************************************************
    //******************************************************************************************************

    // ADD a book to TO_READ List

    @RequestMapping(value="/personal_list/to_read",method= RequestMethod.POST)
    public ResponseEntity<MessageResponse> Save_To_Read_Book(@RequestBody Book to_read_book,@RequestParam("user_id")  Long user_id) {

        return ResponseEntity.ok(personalListService.SaveToReadBook(to_read_book, user_id));
    }

    //******************************************************************************************************
    //******************************************************************************************************

    //******************************************************************************************************
    //******************************************************************************************************

    // Delete a book from TO_READ List

    @RequestMapping(value="/personal_list/to_read",method= RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> Delete_To_Read_Book(@RequestBody Book to_read_book,@RequestParam("user_id")  Long user_id) {

        return   ResponseEntity.ok(personalListService.DeleteToReadBook(to_read_book, user_id));
    }

    //******************************************************************************************************
    //******************************************************************************************************

    // READED

    //******************************************************************************************************

    //******************************************************************************************************


    // GET   READED Books List BY USER ID

    @RequestMapping(value="/personal_list/readed",method= RequestMethod.GET)
    public List<Book> get_To_Readed_Books_List_By_User_ID(@RequestParam("user_id")  Long user_id){

        return  personalListService.getToReadedBooksListByUserID(user_id);

    }

    //******************************************************************************************************
    //******************************************************************************************************

    // ADD a book to READED List

    @RequestMapping(value="/personal_list/readed",method= RequestMethod.POST)
    public ResponseEntity<MessageResponse> Save_Readed_Book(@RequestBody Book readed_book,@RequestParam("user_id")  Long user_id) {

        return ResponseEntity.ok(personalListService.SaveFavoritesBook(readed_book, user_id));
    }

    //******************************************************************************************************
    //******************************************************************************************************

    //******************************************************************************************************
    //******************************************************************************************************

    // Delete a book from READED List

    @RequestMapping(value="/personal_list/readed",method= RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> Delete_Readed_Book(@RequestBody Book readed_book,@RequestParam("user_id")  Long user_id) {

        return ResponseEntity.ok(personalListService.DeleteToReadBook(readed_book, user_id));
    }

    //******************************************************************************************************
    //******************************************************************************************************


}
