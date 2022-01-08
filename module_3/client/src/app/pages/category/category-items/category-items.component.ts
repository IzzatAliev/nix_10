import { Component, OnInit } from '@angular/core';
import {CategoryApiService} from "../../../service/category-api.service";
import {AccountApiService} from "../../../service/account-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountResponseDto} from "../../../model/response/account-response-dto";
import {appConst} from "../../../app.const";
import {CategoryResponseDto} from "../../../model/response/category-response-dto";

@Component({
  selector: 'app-category-items',
  templateUrl: './category-items.component.html',
  styleUrls: ['./category-items.component.scss']
})
export class CategoryItemsComponent implements OnInit {

  categories: CategoryResponseDto[] | undefined;
  account: AccountResponseDto | undefined;
  dateFormat = appConst.dateFormat;

  constructor(
    private _categoryApiService: CategoryApiService,
    private _accountApiService: AccountApiService,
    private _router: Router,
    private _route: ActivatedRoute) { }

  ngOnInit(): void {
    const accountId: string | null = this._route.snapshot.queryParamMap.get('accountId');
    if (accountId) {
      this._accountApiService.loadById(Number(accountId)).subscribe(account => {
        this.account = account;
      });
      this._loadAllByAccountId(accountId);
    } else {
      this._loadAll();
    }
  }

  loadById(id: number): void {
    this._router.navigate([id], { relativeTo: this._route });
  }

  deleteById(id: number): void {
    this._categoryApiService.deleteById(id).subscribe(() => {
      window.location.reload();
    });
  }

  addCategory(): void {
    this._router.navigate(['new'], { relativeTo: this._route });
  }

  private _loadAll(): void {
    this._categoryApiService.loadAll().subscribe(categories => {
      this.categories = categories;
    });
  }

  private _loadAllByAccountId(accountId: string): void {
    this._categoryApiService.loadAllByAccountId(accountId).subscribe(categories => {
      this.categories = categories;
    });
  }

}
