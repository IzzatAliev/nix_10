import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {PageAndSizeData} from "../model/request/page-and-size-data";

@Injectable({
  providedIn: 'root'
})
export class ApiService<REQUEST_DTO, RESPONSE_DTO> {

  // page!: PageAndSizeData;
  // size!: PageAndSizeData;
  // sort!: SortData;
  // order!: SortData;
  sorting: PageAndSizeData | undefined;

  constructor(private _http: HttpClient) { }

  create(apiUrl: string, dto: REQUEST_DTO): Observable<boolean> {
    return this._http.post(apiUrl, dto, this._getOptions()).pipe(
      map((res: any) => {
        return res.data
      })
    );
  }

  update(apiUrl: string, id: number, dto: REQUEST_DTO): Observable<boolean> {
    return this._http.post(apiUrl + '/' + id, dto, this._getOptions()).pipe(
      map((res: any) => {
        return res.data
      })
    );
  }

  deleteById(apiUrl: string, id: number): Observable<boolean> {
    return this._http.delete(apiUrl + '/' + id, this._getOptions()).pipe(
      map((res: any) => {
        return res.data
      })
    );
  }

  loadById(apiUrl: string, id: number): Observable<RESPONSE_DTO> {
    return this._http.get(apiUrl + '/' + id, this._getOptions()).pipe(
      map((res: any) => {
        return res.data
      })
    );
  }

  loadAll(apiUrl: string): Observable<RESPONSE_DTO[]> {
    return this._http.get(apiUrl, this._getOptions()).pipe(
      map((res: any) => {
        return res.items
      })
    );
  }

  loadAllByParams(apiUrl: string, params: HttpParams): Observable<RESPONSE_DTO[]> {
    const options = this._getOptions();
    options.params = params;
    return this._http.get(apiUrl, options).pipe(
      map((res: any) => {
        return res.data
      })
    );
  }

  private _getOptions(): any {
    return {
      headers: new HttpHeaders({})
    };
  }
}
