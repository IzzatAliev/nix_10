import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserItemsComponent } from './user-items/user-items.component';
import { UserNewComponent } from './user-new/user-new.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    UserItemsComponent,
    UserNewComponent,
    UserDetailsComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule
  ]
})
export class UserModule { }
