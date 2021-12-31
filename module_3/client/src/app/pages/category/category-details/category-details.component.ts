import { Component, OnInit } from '@angular/core';
import {appConst} from "../../../app.const";
import {CategoryResponseDto} from "../../../model/response/category-response-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {CategoryApiService} from "../../../service/category-api.service";

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.scss']
})
export class CategoryDetailsComponent implements OnInit {

  private _id: number | undefined;
  category: CategoryResponseDto | undefined;
  dateFormat = appConst.dateFormat;

  constructor(private _categoryApiService: CategoryApiService,
    private _route: ActivatedRoute,
    private _router: Router) { }

  ngOnInit(): void {
    this._id = Number(this._route.snapshot.paramMap.get('id'));
    this._categoryApiService.loadById(this._id).subscribe(category => {
      this.category = category;
    });
  }

  // onClick() {
  //   this.location.back();
  // }

}
