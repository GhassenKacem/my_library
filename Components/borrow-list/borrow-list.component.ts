import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/Services/book.service';
import { BorrowService } from 'src/app/Services/borrow.service';
import { TokenStorageService } from 'src/app/Services/token-storage.service';

@Component({
  selector: 'app-borrow-list',
  templateUrl: './borrow-list.component.html',
  styleUrls: ['./borrow-list.component.css']
})
export class BorrowListComponent implements OnInit {

  // tslint:disable-next-line:variable-name
  user_id?: any;
  borrows?: any;

  // tslint:disable-next-line:no-shadowed-variable
  constructor(private BorrowService: BorrowService, private TokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.user_id = this.TokenStorageService.Get_User_ID();
    this.BorrowService.GetBorrowRequest(this.user_id).subscribe(
      (data) => {  console.log(data); this.borrows = data; },
      (err: Error) => {console.error(err.message); }
    );
  }


  DeleteBorrowRequest(id: any): void  {
    this.BorrowService.DeleteBorrowRequest(id).subscribe(
      (data) => {  alert(data); window.location.reload(); },
      (err: Error) => {console.error(err.message); }
    );
  }

}
