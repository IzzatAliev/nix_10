import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserNewComponent} from "./user-new/user-new.component";
import {UserDetailsComponent} from "./user-details/user-details.component";
import {UserItemsComponent} from "./user-items/user-items.component";

const routes: Routes = [
  {
    path: '',
    component: UserItemsComponent
  },
  {
    path: 'new',
    component: UserNewComponent
  },
  {
    path: ':id',
    component: UserDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
