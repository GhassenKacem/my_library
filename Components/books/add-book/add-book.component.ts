import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { BookService } from 'src/app/Services/book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {


  successMessage!: string;

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
  }


  Add_book(f: NgForm): void {
      console.log(f.value);
      this.bookService.ADD_Book(f.value).subscribe(
           (data) => { this.successMessage = 'The book have been Added Successfully' ; },
           (err: Error) => {console.error(err.message); }
      );
  }
}
