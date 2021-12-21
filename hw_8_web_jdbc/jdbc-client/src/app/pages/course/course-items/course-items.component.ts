import { Component, OnInit } from '@angular/core';
import {CourseApiService} from "../../../service/course-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseResponseDto} from "../../../model/response/course-response-dto";
import {ApiService} from "../../../service/api.service";
import {CourseRequestDto} from "../../../model/request/course-request-dto";
import {PageData} from "../../../model/response/page-data";

@Component({
  selector: 'app-course-items',
  templateUrl: './course-items.component.html',
  styleUrls: ['./course-items.component.scss']
})
export class CourseItemsComponent implements OnInit {

  courses: CourseResponseDto[] | undefined;
  pageData: PageData | undefined;
  page: ApiService<CourseRequestDto, CourseResponseDto> | undefined;

  constructor(private _router: Router,
              private _route: ActivatedRoute,
              private _courseApiService: CourseApiService) { }

  ngOnInit(): void {
    this._loadAll();
  }

  loadById(id: number): void {
    this._router.navigate([id], { relativeTo: this._route });
  }

  deleteById(id: number): void {
    this._courseApiService.deleteById(id).subscribe(() => {
      window.location.reload();
    });
  }

  addCourse(): void {
    this._router.navigate(['new'], { relativeTo: this._route });
  }

  private _loadAll(): void {
    this._courseApiService.loadAll().subscribe(courses => {
      this.courses = courses;
    });
  }

  loadCourseByParams(page: ApiService<CourseRequestDto, CourseResponseDto>): void {
    this._courseApiService.loadAllByParams(
      page.sorting?.page,
      page.sorting?.size,
      page.sorting?.sort,
      page.sorting?.order).subscribe(courses => {
        this.courses = courses;
    });
  }
}
