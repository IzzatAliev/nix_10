import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CategoryRoutingModule } from './category-routing.module';
import { CategoryDetailsComponent } from './category-details/category-details.component';
import { CategoryNewComponent } from './category-new/category-new.component';
import { CategoryItemsComponent } from './category-items/category-items.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    CategoryDetailsComponent,
    CategoryNewComponent,
    CategoryItemsComponent
  ],
  imports: [
    CommonModule,
    CategoryRoutingModule,
    ReactiveFormsModule
  ]
})
export class CategoryModule { }
