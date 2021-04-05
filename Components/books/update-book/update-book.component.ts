import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/Models/Book';
import { BookService } from 'src/app/Services/book.service';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {

  book!: Book;
  successMessage!: string;
  // tslint:disable-next-line:variable-name
  book_id!: string | null;

  constructor(private router: Router,
              private bookService: BookService,
              private route: ActivatedRoute ) { }

  ngOnInit(): void {
    this.book_id = this.route.snapshot.paramMap.get('book_id');
    this.bookService.getBookById(this.book_id).subscribe(
      data => {this.book = data; console.log('book :>> ', this.book); },
     (err: Error) => {console.error(err.message); }
    );
  }


  checkValue(isChecked: any): void {
      console.log('isChecked :>> ', isChecked.currentTarget.checked);
  }


  Update_book(f: NgForm): void  {

    // tslint:disable-next-line:max-line-length
    const updatedbook: Book = new Book(this.book.idbook, f.value.title, f.value.printLength, f.value.price, f.value.author, f.value.publisher, f.value.publicationDate, f.value.language, f.value.description, f.value.availability, this.book.comments,  this.book.ratings);
    console.log('f.value :>> ', updatedbook );

    this.bookService.UPDATE_Book(updatedbook).subscribe(
      data => {console.log('Data :>> ', data);
               this.router.navigate(['books-list']); },
      (err: Error) => {console.error(err.message); }
    );
  }
}
