import { Component, OnInit } from '@angular/core';
import {StudentRequestDto} from "../../../model/request/student-request-dto";
import {FormControl, FormGroup} from "@angular/forms";
import {StudentApiService} from "../../../service/student-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-student-new',
  templateUrl: './student-new.component.html',
  styleUrls: ['./student-new.component.scss']
})
export class StudentNewComponent implements OnInit {

  // private _courseId: number | undefined;
  student: StudentRequestDto | undefined;

  studentForm = new FormGroup({
    firstName: new FormControl(""),
    lastName: new FormControl(""),
    age: new FormControl("")
  })

  constructor(private _route: ActivatedRoute,
              private _router: Router,
              private _studentApiService: StudentApiService) { }

  ngOnInit(): void {
  }

  create(): void {
    this._studentApiService.create(this.studentForm.value).subscribe(() => {
      this._router.navigate(['/'], { relativeTo: this._route });
    });
  }
}
