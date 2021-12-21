import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {StudentNewComponent} from "./student-new/student-new.component";
import {StudentItemsComponent} from "./student-items/student-items.component";
import {StudentDetailsComponent} from "./student-details/student-details.component";

const routes: Routes = [
  {
    path: '',
    component: StudentItemsComponent
  },
  {
    path: 'new',
    component: StudentNewComponent
  },
  {
    path: ':id',
    component: StudentDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
