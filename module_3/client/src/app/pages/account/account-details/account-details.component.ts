import { Component, OnInit } from '@angular/core';
import {AccountResponseDto} from "../../../model/response/account-response-dto";
import {appConst} from "../../../app.const";
import {AccountApiService} from "../../../service/account-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.scss']
})
export class AccountDetailsComponent implements OnInit {

  private _id: number | undefined;
  account: AccountResponseDto | undefined;
  dateFormat = appConst.dateFormat;

  constructor(
    private _accountApiService: AccountApiService,
    private _route: ActivatedRoute,
    private _router: Router) { }

  ngOnInit(): void {
    this._id = Number(this._route.snapshot.paramMap.get('id'));
    this._accountApiService.loadById(this._id).subscribe(account => {
      this.account = account;
    });
  }

  makeTransaction() {
    this._router.navigateByUrl('transactions/new?accountId=' + this.account?.id);
  }

  // onClick() {
  //   this.location.back();
  // }
}
