import { Component, OnInit } from '@angular/core';
import {TransactionResponseDto} from "../../../model/response/transaction-response-dto";
import {appConst} from "../../../app.const";
import {TransactionApiService} from "../../../service/transaction-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-transaction-details',
  templateUrl: './transaction-details.component.html',
  styleUrls: ['./transaction-details.component.scss']
})
export class TransactionDetailsComponent implements OnInit {

  private _id: number | undefined;
  transaction: TransactionResponseDto | undefined;
  dateFormat = appConst.dateFormat;

  constructor(
  private _transactionApiService: TransactionApiService,
  private _route: ActivatedRoute,
  private _router: Router) { }

  ngOnInit(): void {
    this._id = Number(this._route.snapshot.paramMap.get('id'));
    this._transactionApiService.loadById(this._id).subscribe(transaction => {
      this.transaction = transaction;
    });
  }

  // onClick() {
  //   this.location.back();
  // }

}
