import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TeacherRoutingModule } from './teacher-routing.module';
import { BoardTeacherComponent } from './board-teacher/board-teacher.component';


@NgModule({
  declarations: [
    BoardTeacherComponent
  ],
  imports: [
    CommonModule,
    TeacherRoutingModule
  ]
})
export class TeacherModule { }
