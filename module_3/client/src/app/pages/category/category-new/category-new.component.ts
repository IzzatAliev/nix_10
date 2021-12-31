import { Component, OnInit } from '@angular/core';
import {CategoryRequestDto} from "../../../model/request/category-request-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {CategoryApiService} from "../../../service/category-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-category-new',
  templateUrl: './category-new.component.html',
  styleUrls: ['./category-new.component.scss']
})
export class CategoryNewComponent implements OnInit {

  private _accountId: number | undefined;

  category: CategoryRequestDto | undefined;

  categoryForm = new FormGroup({
    name: new FormControl(""),
    price: new FormControl(""),
    income: new FormControl("")
  })

  constructor(
    private _categoryApiService: CategoryApiService,
    private _route: ActivatedRoute,
    private _router: Router) { }

  ngOnInit(): void {
  }

  create(): void {
    let category = this.categoryForm.value as CategoryRequestDto;
    if (this._accountId != null) {
      category.accountId = this._accountId;
    }
    this._categoryApiService.create(category).subscribe(() => {
      this._router.navigateByUrl('accounts/' + this._accountId);
    });
  }

}
