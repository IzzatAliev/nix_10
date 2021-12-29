import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {appConts} from "../app.conts";
import {ApiService} from "./api.service";
import {CourseRequestDto} from "../model/request/course-request-dto";
import {CourseResponseDto} from "../model/response/course-response-dto";
import {Observable} from "rxjs";
import {HttpParams} from "@angular/common/http";
import {PageData} from "../model/response/page-data";
import {PageAndSizeData} from "../model/request/page-and-size-data";

@Injectable({
  providedIn: 'root'
})
export class CourseApiService {

  private _apiUrl = environment.apiUrl + appConts.coursesPath;

  constructor(private _apiService: ApiService<CourseRequestDto, CourseResponseDto>,
              private _apiServices: ApiService<PageAndSizeData, PageData>) { }

  create(dto: CourseRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, dto);
  }

  update(id: number, dto: CourseRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, dto);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }

  loadById(id: number): Observable<CourseResponseDto> {
    return this._apiService.loadById(this._apiUrl, id);
  }

  loadAll(): Observable<CourseResponseDto[]> {
    return this._apiService.loadAll(this._apiUrl);
  }

  loadByStudentId(studentId: string): Observable<CourseResponseDto[]> {
    let httpParams = new HttpParams();
    httpParams = httpParams.append('studentId', studentId);
    return this._apiService.loadAllByParams(this._apiUrl, httpParams);
  }

  loadAllByParams(page:number, size:number, sort:string, order:string): Observable<CourseResponseDto[]> {
    let httpParams = new HttpParams();
    httpParams = httpParams.
    set('page',page).
    set('size',size).
    set('sort', sort).
    set('order',order);
    return this._apiService.loadAllByParams(this._apiUrl, httpParams);
  }
}
