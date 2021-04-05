import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/Models/Book';
import { BookService } from 'src/app/Services/book.service';
import { BorrowService } from 'src/app/Services/borrow.service';
import { TokenStorageService } from 'src/app/Services/token-storage.service';

@Component({
  selector: 'app-borrow',
  templateUrl: './borrow.component.html',
  styleUrls: ['./borrow.component.css']
})
export class BorrowComponent implements OnInit {

  book?: any ;
  // tslint:disable-next-line:variable-name
  book_id?: any ;

  user?: any;

  constructor(private ActiveRouter: ActivatedRoute,
              private router: Router,
              private borrowService: BorrowService,
              private TokenStorgeService: TokenStorageService,
              // tslint:disable-next-line:no-shadowed-variable
              private BookService: BookService) { }

  ngOnInit(): void {
    this.book_id = this.ActiveRouter.snapshot.paramMap.get('BorrowBook');
    console.log('book :>> ', this.book_id);
    this.BookService.getBookById(this.book_id).subscribe(
      (data) => { this.book = data ; },
      (err) => {alert(err.body); }
    );
    this.user = this.TokenStorgeService.getUser();
  }



  getBook(): void {}


  Add_borrow(f: NgForm): void {
      if (f.value.startDate < f.value.endDate) {
      const borrow = {startDate: f.value.startDate
                                  , endDate: f.value.endDate,
                                   returned: false, book: this.book
                                  , borrow_request: false};
      console.log('borrow :>> ', this.user.id);
      this.borrowService.ADD_Borrow(borrow, this.user.id).subscribe(
        (data) => { console.log('data :>> ', data); this.router.navigate(['borrows-list']); },
        (err) => {alert(err.body); }
      ) ;
    } else {
      alert('Check Your Dates');
    }
  }
}
