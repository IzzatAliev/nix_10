import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {appConts} from "../app.conts";
import {ApiService} from "./api.service";
import {CourseRequestDto} from "../model/request/course-request-dto";
import {CourseResponseDto} from "../model/response/course-response-dto";
import {Observable} from "rxjs";
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CourseApiService {

  private _apiUrl = environment.apiUrl + appConts.coursesPath;

  constructor(private _apiService: ApiService<CourseRequestDto, CourseResponseDto>) { }

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

  loadAllByParams(page:number | undefined, size:number| undefined, sort:string| undefined, order:string| undefined): Observable<CourseResponseDto[]> {
    const params: HttpParams = new HttpParams();
    params.set('page',page!)
    params.set('size',size!)
    params.set('sort', sort!)
    params.set('order',order!)
    return this._apiService.loadAllByParams(this._apiUrl, params);
  }

  // loadAllByParams(page:number | undefined, size:number| undefined, sort:string| undefined, order:string| undefined): Observable<CourseResponseDto[]> {
  //   let params = new HttpParams();
  //   params = params.set('page',page!)
  //   params = params.set('size',size!)
  //   params = params.set('sort', sort!)
  //   params = params.set('order',order!)
  //   return this._apiService.loadAllByParams(this._apiUrl, params);
  // }
}
