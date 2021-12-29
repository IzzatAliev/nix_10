import { Component, OnInit } from '@angular/core';
import {StudentResponseDto} from "../../../model/response/student-response-dto";
import {StudentApiService} from "../../../service/student-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseResponseDto} from "../../../model/response/course-response-dto";
import {CourseApiService} from "../../../service/course-api.service";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-student-items',
  templateUrl: './student-items.component.html',
  styleUrls: ['./student-items.component.scss']
})
export class StudentItemsComponent implements OnInit {

  currentPage: number = 1;
  itemsSize: number = 0;
  totalPageSize: number = 0;
  pageSize: number = 10;
  pageSizeItems: number[] = [5, 10, 25, 50, 100];
  sort: string = "id";
  order: string = "desc";
  statement: boolean = false;

  students: StudentResponseDto[] | undefined;
  course: CourseResponseDto | undefined;

  constructor(private _router: Router,
              private _route: ActivatedRoute,
              private _studentApiService: StudentApiService,
              private _courseApiService: CourseApiService) { }

  ngOnInit(): void {
    const courseId: string | null = this._route.snapshot.queryParamMap.get('courseId');
    if (courseId) {
      this._courseApiService.loadById(Number(courseId)).subscribe(course => {
        this.course = course;
      });
      this._loadByCourseId(courseId);
    } else {
    this._loadAll();
  }
  }

  loadById(id: number): void {
    this._router.navigate([id], { relativeTo: this._route });
  }

  deleteById(id: number): void {
    this._studentApiService.deleteById(id).subscribe(() => {
      window.location.reload();
    });
  }

  addStudent(): void {
    this._router.navigate(['new'], { relativeTo: this._route });
  }

  _loadAll(): void {
    this._studentApiService.loadAll().subscribe(students => {
      this.students = students;
    });
  }

  private _loadByCourseId(courseId: string): void {
    this._studentApiService.loadByCourseId(courseId).subscribe(students => {
      this.students = students;
    });
  }

  sorting(various: string) {
    this.statement = !this.statement;
    if (!this.statement){
      this._studentApiService.loadAllByParams(this.currentPage, this.pageSize, various, 'desc').subscribe( students => {
        this.students = students;
      })
    }
    else {
      this._studentApiService.loadAllByParams(this.currentPage, this.pageSize, various, 'asc').subscribe( students => {
        this.students = students;
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
    this._studentApiService.loadAllByParams(this.currentPage, this.pageSize, this.sort, this.order).subscribe(students => {
      if (this.itemsSize % this.pageSize == 0) {
        this.totalPageSize = this.itemsSize / this.pageSize;
      } else {
        this.totalPageSize = Math.floor(this.itemsSize / this.pageSize) + 1;
      }
      this.students = students;
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
