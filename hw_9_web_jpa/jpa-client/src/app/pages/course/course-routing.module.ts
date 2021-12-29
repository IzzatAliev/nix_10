import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CourseItemsComponent} from "./course-items/course-items.component";
import {CourseNewComponent} from "./course-new/course-new.component";
import {CourseDetailsComponent} from "./course-details/course-details.component";

const routes: Routes = [
  {
    path: '',
    component: CourseItemsComponent
  },
  {
    path: 'new',
    component: CourseNewComponent
  },
  {
    path: ':id',
    component: CourseDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CourseRoutingModule { }
