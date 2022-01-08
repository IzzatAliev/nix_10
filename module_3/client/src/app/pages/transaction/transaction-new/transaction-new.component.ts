import { Component, OnInit } from '@angular/core';
import {Amount, TransactionRequestDto} from "../../../model/request/transaction-request-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {TransactionApiService} from "../../../service/transaction-api.service";
import {CategoryResponseDto} from "../../../model/response/category-response-dto";
import {CategoryName} from "../../../model/request/category-request-dto";

@Component({
  selector: 'app-transaction-new',
  templateUrl: './transaction-new.component.html',
  styleUrls: ['./transaction-new.component.scss']
})
export class TransactionNewComponent implements OnInit {

  category: CategoryResponseDto[] | undefined;
  private _accountId: number | undefined;
  categoryName = CategoryName;
  amount = Amount;

  transaction: TransactionRequestDto | undefined;

  transactionForm = new FormGroup({
    amount: new FormControl(""),
    categoryName: new FormControl("")
  })

  constructor(
    private _transactionApiService: TransactionApiService,
    private _route: ActivatedRoute,
    private _router: Router) { }

  ngOnInit(): void {
    const accountId: string | null = this._route.snapshot.queryParamMap.get('accountId');
    this._accountId = Number(accountId);
  }

  create(): void {
    let transaction = this.transactionForm.value as TransactionRequestDto;
    if (this._accountId != null) {
      transaction.accountId = this._accountId;
    }
    this._transactionApiService.create(transaction).subscribe(() => {
      this._router.navigateByUrl('accounts/' + this._accountId);
    });
  }

}
