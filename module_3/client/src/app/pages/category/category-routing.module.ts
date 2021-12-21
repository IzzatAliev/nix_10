import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CategoryNewComponent} from "./category-new/category-new.component";
import {CategoryDetailsComponent} from "./category-details/category-details.component";
import {CategoryItemsComponent} from "./category-items/category-items.component";

const routes: Routes = [
  {
    path: '',
    component: CategoryItemsComponent
  },
  {
    path: 'new',
    component: CategoryNewComponent
  },
  {
    path: ':id',
    component: CategoryDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoryRoutingModule { }
