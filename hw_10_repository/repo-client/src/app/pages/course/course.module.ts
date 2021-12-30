import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CourseRoutingModule } from './course-routing.module';
import { CourseNewComponent } from './course-new/course-new.component';
import { CourseItemsComponent } from './course-items/course-items.component';
import { CourseDetailsComponent } from './course-details/course-details.component';
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    CourseNewComponent,
    CourseItemsComponent,
    CourseDetailsComponent
  ],
  imports: [
    CommonModule,
    CourseRoutingModule,
    ReactiveFormsModule
  ]
})
export class CourseModule { }
