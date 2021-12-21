import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {appConst} from "../app.const";
import {ApiService} from "./api.service";
import {UserRequestDto} from "../model/request/user-request-dto";
import {UserResponseDto} from "../model/response/user-response-dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserApiService {

  private _apiUrl = environment.apiUrl + appConst.userPath;

  constructor(private _apiService: ApiService<UserRequestDto, UserResponseDto>) { }

  create(dto: UserRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, dto);
  }

  update(id: number, dto: UserRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, dto);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }

  loadById(id: number): Observable<UserResponseDto> {
    return this._apiService.loadById(this._apiUrl, id);
  }

  loadAll(): Observable<UserResponseDto[]> {
    return this._apiService.loadAll(this._apiUrl);
  }
}
