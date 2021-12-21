import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {UserApiService} from "../../../service/user-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-user-new',
  templateUrl: './user-new.component.html',
  styleUrls: ['./user-new.component.scss']
})
export class UserNewComponent implements OnInit {

  userForm = new FormGroup({
    firstName: new FormControl(""),
    lastName: new FormControl("")
  })

  constructor(
  private _userApiService: UserApiService,
  private _route: ActivatedRoute,
  private _router: Router) { }

  ngOnInit(): void {
  }
  create(): void {
    this._userApiService.create(this.userForm.value).subscribe(() => {
      this._router.navigate(['/'], { relativeTo: this._route });
    });
  }
}
