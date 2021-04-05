import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/Models/Book';
import { BookService } from 'src/app/Services/book.service';
import { TokenStorageService } from 'src/app/Services/token-storage.service';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {


  selectedOption!: string;
  printedOption!: string;

  options = [
    { name: '1', value: 1 },
    { name: '2', value: 2 },
    { name: '3', value: 3 },
    { name: '4', value: 4 },
    { name: '5', value: 5 }
  ];


  // tslint:disable-next-line:variable-name
  book_id!: string | null;
  book!: Book;
  rate: any;
  rateNumber: any;

  constructor(private router: Router,
              private  route: ActivatedRoute,
              private bookService: BookService,
              private tokenstorgeService: TokenStorageService) { }

  ngOnInit(): void {
    this.book_id = this.route.snapshot.paramMap.get('book_id');
    this.bookService.getBookById(this.book_id).subscribe(
      data => {this.book = data , console.log(data);
               this.bookService.Get_Rating_Average(data.idbook).subscribe(
                info => {console.log(info); this.rate = info; },
         (err: Error) => {console.error(err.message); }
        );
      },
     (err: Error) => {console.error(err.message); }
    );

  }


  // tslint:disable-next-line:typedef
  Add_Rating() {
    this.printedOption = this.selectedOption;
    console.log('My input: ', this.selectedOption);
    const rating = {user: null, rate: this.selectedOption };
    this.bookService.ADD_Rating(rating, this.book_id, this.tokenstorgeService.getUser().username).subscribe(
      (data) => {window.location.reload(); },
      (err: Error) => {console.error(err.message); }
    );
  }


  Add_comment(f: NgForm, idbook: number): void {
    this.bookService.ADD_Comments(f.value, idbook, this.tokenstorgeService.getUser().username).subscribe(
           (data) => {this.ngOnInit(); },
           (err: Error) => {console.error(err.message); }
    );

  }

  reloadPage(): void {
    window.location.reload();
  }

}
