import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccountRoutingModule } from './account-routing.module';
import { AccountNewComponent } from './account-new/account-new.component';
import { AccountItemsComponent } from './account-items/account-items.component';
import { AccountDetailsComponent } from './account-details/account-details.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    AccountNewComponent,
    AccountItemsComponent,
    AccountDetailsComponent
  ],
  imports: [
    CommonModule,
    AccountRoutingModule,
    ReactiveFormsModule
  ]
})
export class AccountModule { }
