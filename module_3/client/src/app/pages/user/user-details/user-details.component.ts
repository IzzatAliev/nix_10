import { Component, OnInit } from '@angular/core';
import {UserResponseDto} from "../../../model/response/user-response-dto";
import {appConst} from "../../../app.const";
import {UserApiService} from "../../../service/user-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {

  private _id: number | undefined;
  user: UserResponseDto | undefined;
  dateFormat = appConst.dateFormat;

  constructor(
  private _userApiService: UserApiService,
  private _route: ActivatedRoute,
  private _router: Router) { }

  ngOnInit(): void {
    this._id = Number(this._route.snapshot.paramMap.get('id'));
    this._userApiService.loadById(this._id).subscribe(user => {
      this.user = user;
    });
  }

  showAllAccounts() {
    this._router.navigateByUrl('accounts?userId=' + this.user?.id);
  }

  addNewAccounts() {
    this._router.navigateByUrl('accounts/new?userId=' + this.user?.id);
  }
}
