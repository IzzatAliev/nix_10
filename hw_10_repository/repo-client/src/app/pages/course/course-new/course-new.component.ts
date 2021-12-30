import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {CourseApiService} from "../../../service/course-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseType} from "../../../model/request/course-request-dto";

@Component({
  selector: 'app-course-new',
  templateUrl: './course-new.component.html',
  styleUrls: ['./course-new.component.scss']
})
export class CourseNewComponent implements OnInit {

  courseForm = new FormGroup({
    courseName: new FormControl(""),
    credit: new FormControl(""),
    courseType: new FormControl(""),
    description: new FormControl("")
  })
  courseTypeItems = CourseType;

  constructor(private _router: Router,
              private _route: ActivatedRoute,
              private _courseApiService: CourseApiService) { }

  ngOnInit(): void {
  }

  create(): void {
    this._courseApiService.create(this.courseForm.value).subscribe(() => {
      this._router.navigate(['/'], { relativeTo: this._route });
    });
  }
}
