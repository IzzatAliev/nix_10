import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {appConst} from "../app.const";
import {ApiService} from "./api.service";
import {CategoryRequestDto} from "../model/request/category-request-dto";
import {CategoryResponseDto} from "../model/response/category-response-dto";
import {Observable} from "rxjs";
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CategoryApiService {

  private _apiUrl = environment.apiUrl + appConst.categoryPath;

  constructor(private _apiService: ApiService<CategoryRequestDto, CategoryResponseDto>) { }

  create(dto: CategoryRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, dto);
  }

  update(id: number, dto: CategoryRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, dto);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }

  loadById(id: number): Observable<CategoryResponseDto> {
    return this._apiService.loadById(this._apiUrl, id);
  }

  loadAll(): Observable<CategoryResponseDto[]> {
    return this._apiService.loadAll(this._apiUrl);
  }

  loadAllByAccountId(accountId: string): Observable<CategoryResponseDto[]> {
    let httpParams = new HttpParams();
    httpParams = httpParams.append('accountId', accountId);
    return this._apiService.loadAllByParams(this._apiUrl, httpParams);
  }
}
