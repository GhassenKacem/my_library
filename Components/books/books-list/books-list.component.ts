import { query } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/Models/Book';
import { BookService } from 'src/app/Services/book.service';
import { PersonallistService } from 'src/app/Services/personallist.service';
import { TokenStorageService } from 'src/app/Services/token-storage.service';


@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css']
})
export class BooksListComponent implements OnInit {

  Books: any[] = [];
  Rating = 0;
  userCheck?: boolean;


  currentTutorial?: Book;
  currentIndex = -1;
  searchParam = '';

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];

  constructor(private bookService: BookService,
              private router: Router,
              private tokenStorgeService: TokenStorageService,
              // tslint:disable-next-line:variable-name
              private Personal_List_Service: PersonallistService) { }

  ngOnInit(): void {
    this.getListOfBooks();
    this.userCheck = this.tokenStorgeService.Check_If_User_Is_Admin();
  }

  getRequestParams(searchParam: string, page: number, pageSize: number): any {
    // tslint:disable-next-line:prefer-const
    let params: any = {};

    if (searchParam) {
      params[`searchParam`] = this.searchParam;
    }

    if (page) {
      params[`page`] = page - 1;
    }

    if (pageSize) {
      params[`size`] = pageSize;
    }

    return params;
  }





  getListOfBooks(): void {

    const params = this.getRequestParams(this.searchParam, this.page, this.pageSize);

    this.bookService.GetAllBooks(params).subscribe(
      (data) => { const { books, totalItems } = data;
                  this.Books = books;
                  this.count = totalItems;
                  console.log(data);
                  console.log(this.Books); },
      (err: Error) => {console.error(err.message); }
    );
  }



  handlePageChange(event: number): void {
    this.page = event;
    this.getListOfBooks();
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.getListOfBooks();
  }









   Delete(idbook: number): void {
     this.bookService.DeleteBook(idbook).subscribe(
      (data) => { console.log(data); this.getListOfBooks(); },
      (err: Error) => {console.error(err.message); }
     )
     ;

  }

  // tslint:disable-next-line:typedef
  Add_Favorite_Book(bookid: number) {
    console.log('book_id :>> ', bookid);
    this.Personal_List_Service.Add_Favorite_Book_List(this.tokenStorgeService.Get_User_ID(), bookid).subscribe(
      (data) => { alert(data); },
      (err: Error) => {alert(err.message); }
    );
  }

  // tslint:disable-next-line:typedef
  Add_To_Read_Book(bookid: number) {
    console.log('book_id :>> ', bookid);
    this.Personal_List_Service.Add_To_Read_Book_List(this.tokenStorgeService.Get_User_ID(), Number(bookid)).subscribe(
      (data) => { alert(data); },
      (err) => {alert(err.body); }
    );
  }



  Add_borrow(book: any): void {
    console.log('book :>> ', book);
    this.router.navigate(['borrow', {BorrowBook: String(book)}]);
  }

}
