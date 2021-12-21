import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {appConst} from "../app.const";
import {ApiService} from "./api.service";
import {TransactionRequestDto} from "../model/request/transaction-request-dto";
import {TransactionResponseDto} from "../model/response/transaction-response-dto";
import {Observable} from "rxjs";
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TransactionApiService {

  private _apiUrl = environment.apiUrl + appConst.transactionPath;

  constructor(private _apiService: ApiService<TransactionRequestDto, TransactionResponseDto>) { }

  create(dto: TransactionRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, dto);
  }

  update(id: number, dto: TransactionRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, dto);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }

  loadById(id: number): Observable<TransactionResponseDto> {
    return this._apiService.loadById(this._apiUrl, id);
  }

  loadAll(): Observable<TransactionResponseDto[]> {
    return this._apiService.loadAll(this._apiUrl);
  }

  loadAllByAccountId(accountId: string): Observable<TransactionResponseDto[]> {
    let httpParams = new HttpParams();
    httpParams = httpParams.append('accountId', accountId);
    return this._apiService.loadAllByParams(this._apiUrl, httpParams);
  }

  loadAllByCategoryId(categoryId: string): Observable<TransactionResponseDto[]> {
    let httpParams = new HttpParams();
    httpParams = httpParams.append('categoryId', categoryId);
    return this._apiService.loadAllByParams(this._apiUrl, httpParams);
  }
}
