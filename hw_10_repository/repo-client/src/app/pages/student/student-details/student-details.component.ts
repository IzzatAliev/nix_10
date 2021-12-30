import { Component, OnInit } from '@angular/core';
import {StudentApiService} from "../../../service/student-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StudentResponseDto} from "../../../model/response/student-response-dto";
import {appConts} from "../../../app.conts";

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.scss']
})
export class StudentDetailsComponent implements OnInit {

  private _id: number | undefined;
  student: StudentResponseDto | undefined;
  dateFormat = appConts.dateFormat;

  constructor(private _route: ActivatedRoute,
              private _router: Router,
              private _studentApiService: StudentApiService) { }

  ngOnInit(): void {
    this._id = Number(this._route.snapshot.paramMap.get('id'));
    this._studentApiService.loadById(this._id).subscribe(student => {
      this.student = student;
    });
  }

  goBack() {
    window.history.back();
  }

}
