import { Component, OnInit } from '@angular/core';
import {CourseResponseDto} from "../../../model/response/course-response-dto";
import {appConts} from "../../../app.conts";
import {CourseApiService} from "../../../service/course-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.scss']
})
export class CourseDetailsComponent implements OnInit {

  private _id: number | undefined;
  course: CourseResponseDto | undefined;
  dateFormat = appConts.dateFormat;

  constructor(private _route: ActivatedRoute,
              private _router: Router,
              private _courseApiService: CourseApiService) { }

  ngOnInit(): void {
    this._id = Number(this._route.snapshot.paramMap.get('id'));
    this._courseApiService.loadById(this._id).subscribe(course => {
      this.course = course;
    });
  }

  showAllStudents() {
    this._router.navigateByUrl('students?courseId=' + this.course?.id);
  }

  addNewStudents() {
    this._router.navigateByUrl('students/new?courseId=' + this.course?.id);
  }

  goBack():void{
    window.history.back();
  }
}
