import { Component, OnInit } from '@angular/core';
import {AccountResponseDto} from "../../../model/response/account-response-dto";
import {UserResponseDto} from "../../../model/response/user-response-dto";
import {appConst} from "../../../app.const";
import {UserApiService} from "../../../service/user-api.service";
import {AccountApiService} from "../../../service/account-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-account-items',
  templateUrl: './account-items.component.html',
  styleUrls: ['./account-items.component.scss']
})
export class AccountItemsComponent implements OnInit {

  accounts: AccountResponseDto[] | undefined;
  user: UserResponseDto | undefined;
  dateFormat = appConst.dateFormat;

  constructor(
    private _userApiService: UserApiService,
    private _accountApiService: AccountApiService,
    private _router: Router,
    private _route: ActivatedRoute) { }

  ngOnInit(): void {
    const userId: string | null = this._route.snapshot.queryParamMap.get('userId');
    if (userId) {
      this._userApiService.loadById(Number(userId)).subscribe(user => {
        this.user = user;
      });
      this._loadAllByUserId(userId);
    } else {
      this._loadAll();
    }
  }

  loadById(id: number): void {
    this._router.navigate([id], { relativeTo: this._route });
  }

  deleteById(id: number): void {
    this._accountApiService.deleteById(id).subscribe(() => {
      window.location.reload();
    });
  }

  private _loadAll(): void {
    this._accountApiService.loadAll().subscribe(accounts => {
      this.accounts = accounts;
    });
  }

  private _loadAllByUserId(userId: string): void {
    this._accountApiService.loadAllByUserId(userId).subscribe(accounts => {
      this.accounts = accounts;
    });
  }
}
