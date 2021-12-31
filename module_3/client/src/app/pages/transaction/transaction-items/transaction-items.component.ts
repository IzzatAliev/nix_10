import { Component, OnInit } from '@angular/core';
import {TransactionResponseDto} from "../../../model/response/transaction-response-dto";
import {CategoryResponseDto} from "../../../model/response/category-response-dto";
import {appConst} from "../../../app.const";
import {ActivatedRoute, Router} from "@angular/router";
import {CategoryApiService} from "../../../service/category-api.service";
import {TransactionApiService} from "../../../service/transaction-api.service";

@Component({
  selector: 'app-transaction-items',
  templateUrl: './transaction-items.component.html',
  styleUrls: ['./transaction-items.component.scss']
})
export class TransactionItemsComponent implements OnInit {

  transactions: TransactionResponseDto[] | undefined;
  category: CategoryResponseDto | undefined;
  dateFormat = appConst.dateFormat;

  constructor(
    private _transactionApiService: TransactionApiService,
    private _categoryApiService: CategoryApiService,
    private _router: Router,
    private _route: ActivatedRoute) { }

  ngOnInit(): void {
    const categoryId: string | null = this._route.snapshot.queryParamMap.get('categoryId');
    if (categoryId) {
      this._categoryApiService.loadById(Number(categoryId)).subscribe(category => {
        this.category = category;
      });
      this._loadAllByCategoryId(categoryId);
    } else {
      this._loadAll();
    }
  }

  loadById(id: number): void {
    this._router.navigate([id], { relativeTo: this._route });
  }

  deleteById(id: number): void {
    this._transactionApiService.deleteById(id).subscribe(() => {
      window.location.reload();
    });
  }

  private _loadAll(): void {
    this._transactionApiService.loadAll().subscribe(transactions => {
      this.transactions = transactions;
    });
  }

  private _loadAllByCategoryId(categoryId: string): void {
    this._transactionApiService.loadAllByCategoryId(categoryId).subscribe(transactions => {
      this.transactions = transactions;
    });
  }

}
