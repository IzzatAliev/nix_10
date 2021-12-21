import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {appConst} from "../app.const";
import {ApiService} from "./api.service";
import {CategoryRequestDto} from "../model/request/category-request-dto";
import {CategoryResponseDto} from "../model/response/category-response-dto";
import {Observable} from "rxjs";

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
}
