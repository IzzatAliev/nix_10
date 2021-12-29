import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {appConts} from "../app.conts";
import {ApiService} from "./api.service";
import {StudentRequestDto} from "../model/request/student-request-dto";
import {StudentResponseDto} from "../model/response/student-response-dto";
import {Observable} from "rxjs";
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class StudentApiService {

  private _apiUrl = environment.apiUrl + appConts.studentsPath;

  constructor(private _apiService: ApiService<StudentRequestDto, StudentResponseDto>) { }

  create(dto: StudentRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, dto);
  }

  update(id: number, dto: StudentRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, dto);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }

  loadById(id: number): Observable<StudentResponseDto> {
    return this._apiService.loadById(this._apiUrl, id);
  }

  loadAll(): Observable<StudentResponseDto[]> {
    return this._apiService.loadAll(this._apiUrl);
  }

  loadByCourseId(courseId: string): Observable<StudentResponseDto[]> {
    let httpParams = new HttpParams();
    httpParams = httpParams.append('courseId', courseId);
    return this._apiService.loadAllByParams(this._apiUrl, httpParams);
  }

  loadAllByParams(page:number, size:number, sort:string, order:string): Observable<StudentResponseDto[]> {
    let httpParams = new HttpParams();
    httpParams = httpParams.
    set('page',page).
    set('size',size).
    set('sort', sort).
    set('order',order);
    return this._apiService.loadAllByParams(this._apiUrl, httpParams);
  }
}
