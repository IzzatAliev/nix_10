import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {appConst} from "../app.const";
import {ApiService} from "./api.service";
import {AccountRequestDto} from "../model/request/account-request-dto";
import {AccountResponseDto} from "../model/response/account-response-dto";
import {Observable} from "rxjs";
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AccountApiService {

  private _apiUrl = environment.apiUrl + appConst.accountPath;

  constructor(private _apiService: ApiService<AccountRequestDto, AccountResponseDto>) { }

  create(dto: AccountRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, dto);
  }

  update(id: number, dto: AccountRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, dto);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }

  loadById(id: number): Observable<AccountResponseDto> {
    return this._apiService.loadById(this._apiUrl, id);
  }

  loadAll(): Observable<AccountResponseDto[]> {
    return this._apiService.loadAll(this._apiUrl);
  }

  loadAllByUserId(userId: string): Observable<AccountResponseDto[]> {
    let httpParams = new HttpParams();
    httpParams = httpParams.append('userId', userId);
    return this._apiService.loadAllByParams(this._apiUrl, httpParams);
  }
}
