import { Component, OnInit } from '@angular/core';
import { BorrowService } from 'src/app/Services/borrow.service';
import { TokenStorageService } from 'src/app/Services/token-storage.service';

@Component({
  selector: 'app-borrow-request-list',
  templateUrl: './borrow-request-list.component.html',
  styleUrls: ['./borrow-request-list.component.css']
})
export class BorrowRequestListComponent implements OnInit {
  borrows?: any;
  // tslint:disable-next-line:no-shadowed-variable
  constructor(private BorrowService: BorrowService, private TokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.BorrowService.GetAllBorrowRequest().subscribe(
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

  AcceptBorrowRequest(borrow: any): void  {
    this.BorrowService.AcceptBorrowRequest(borrow).subscribe(
      (data) => { alert(data); window.location.reload(); },
      (err: Error) => {console.error(err.message); }
    );
  }

}
