import { Component, OnInit } from '@angular/core';
import {CourseApiService} from "../../../service/course-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseResponseDto} from "../../../model/response/course-response-dto";
import {StudentResponseDto} from "../../../model/response/student-response-dto";
import {StudentApiService} from "../../../service/student-api.service";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-course-items',
  templateUrl: './course-items.component.html',
  styleUrls: ['./course-items.component.scss']
})
export class CourseItemsComponent implements OnInit {

  currentPage: number = 1;
  itemsSize: number = 0;
  totalPageSize: number = 0;
  pageSize: number = 10;
  pageSizeItems: number[] = [5, 10, 25, 50, 100];
  sort: string = "id";
  order: string = "desc";
  statement: boolean = false;

  courses: CourseResponseDto[] | undefined;
  student: StudentResponseDto | undefined;

  constructor(private _router: Router,
              private _route: ActivatedRoute,
              private _courseApiService: CourseApiService,
              private _studentApiService: StudentApiService) {
  }

  ngOnInit(): void {
    const studentId: string | null = this._route.snapshot.queryParamMap.get('studentId');
    if (studentId) {
      this._studentApiService.loadById(Number(studentId)).subscribe(student => {
        this.student = student;
      });
      this._loadByStudentId(studentId);
    }
       else {
      this.paginationCourse();
    }
  }

  loadById(id: number): void {
    this._router.navigate([id], {relativeTo: this._route});
  }

  deleteById(id: number): void {
    this._courseApiService.deleteById(id).subscribe(() => {
      window.location.reload();
    });
  }

  addCourse(): void {
    this._router.navigate(['new'], {relativeTo: this._route});
  }

  _loadAll(): void {
    this._courseApiService.loadAll().subscribe(courses => {
      this.courses = courses;
      this.paginationCourse();
    });
  }

  private _loadByStudentId(studentId: string): void {
    this._courseApiService.loadByStudentId(studentId).subscribe(courses => {
      this.courses = courses;
    });
  }

  sorting(various: string) {
    this.statement = !this.statement;
    if (!this.statement){
      this._courseApiService.loadAllByParams(this.currentPage, this.pageSize, various, 'desc').subscribe( courses => {
        this.courses = courses;
      })
    }
    else {
      this._courseApiService.loadAllByParams(this.currentPage, this.pageSize, various, 'asc').subscribe( courses => {
        this.courses = courses;
      })
    }
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.paginationCourse();
  }

  changePageSize(pageSize: number): void {
    this.pageSize = pageSize;
    this.paginationCourse();
  }

  paginationCourse(): void {
    this._courseApiService.loadAllByParams(this.currentPage, this.pageSize, this.sort, this.order).subscribe(courses => {
      if (this.itemsSize % this.pageSize == 0) {
        this.totalPageSize = this.itemsSize / this.pageSize;
      } else {
        this.totalPageSize = Math.floor(this.itemsSize / this.pageSize) + 1;
      }
      this.courses = courses;
    })
  }

  httpParamsHelper(): any {
    return new HttpParams()
      .set('page', this.currentPage)
      .set('size', this.pageSize)
      .set('sort', this.sort)
      .set('order', this.order)
      ;
  }
}
