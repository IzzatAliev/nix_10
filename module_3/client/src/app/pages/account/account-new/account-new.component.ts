import { Component, OnInit } from '@angular/core';
import {AccountRequestDto} from "../../../model/request/account-request-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {AccountApiService} from "../../../service/account-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-account-new',
  templateUrl: './account-new.component.html',
  styleUrls: ['./account-new.component.scss']
})
export class AccountNewComponent implements OnInit {

  private _userId: number | undefined;

  account: AccountRequestDto | undefined;

  accountForm = new FormGroup({
    name: new FormControl(""),
    balance: new FormControl("")
  })

  constructor(
  private _accountApiService: AccountApiService,
  private _route: ActivatedRoute,
  private _router: Router) { }

  ngOnInit(): void {
    const userId: string | null = this._route.snapshot.queryParamMap.get('userId');
    this._userId = Number(userId);
  }

  create(): void {
    let account = this.accountForm.value as AccountRequestDto;
    if (this._userId != null) {
      account.userId = this._userId;
    }
    this._accountApiService.create(account).subscribe(() => {
      this._router.navigateByUrl('users/' + this._userId);
    });
  }
}
