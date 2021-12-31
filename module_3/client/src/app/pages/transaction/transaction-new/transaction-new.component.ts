import { Component, OnInit } from '@angular/core';
import {TransactionRequestDto} from "../../../model/request/transaction-request-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {TransactionApiService} from "../../../service/transaction-api.service";

@Component({
  selector: 'app-transaction-new',
  templateUrl: './transaction-new.component.html',
  styleUrls: ['./transaction-new.component.scss']
})
export class TransactionNewComponent implements OnInit {

  private _categoryId: number | undefined;

  transaction: TransactionRequestDto | undefined;

  transactionForm = new FormGroup({
    amount: new FormControl("")
  })

  constructor(
    private _transactionApiService: TransactionApiService,
    private _route: ActivatedRoute,
    private _router: Router) { }

  ngOnInit(): void {
  }

  create(): void {
    let transaction = this.transactionForm.value as TransactionRequestDto;
    if (this._categoryId != null) {
      transaction.categoryId = this._categoryId;
    }
    this._transactionApiService.create(transaction).subscribe(() => {
      this._router.navigateByUrl('categories/' + this._categoryId);
    });
  }

}
