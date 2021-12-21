import { Component, OnInit } from '@angular/core';
import {UserResponseDto} from "../../../model/response/user-response-dto";
import {UserApiService} from "../../../service/user-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-user-items',
  templateUrl: './user-items.component.html',
  styleUrls: ['./user-items.component.scss']
})
export class UserItemsComponent implements OnInit {

  users: UserResponseDto[] | undefined;

  constructor(
    private _userApiService: UserApiService,
    private _route: ActivatedRoute,
    private _router: Router) { }

  ngOnInit(): void {
    this._loadAll();
  }

  loadById(id: number): void {
    this._router.navigate([id], { relativeTo: this._route });
  }

  deleteById(id: number): void {
    this._userApiService.deleteById(id).subscribe(() => {
      window.location.reload();
    });
  }

  addUser(): void {
    this._router.navigate(['new'], { relativeTo: this._route });
  }

  private _loadAll(): void {
    this._userApiService.loadAll().subscribe(users => {
      this.users = users;
    });
  }
}
