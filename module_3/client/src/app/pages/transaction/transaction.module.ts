import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransactionRoutingModule } from './transaction-routing.module';
import { TransactionItemsComponent } from './transaction-items/transaction-items.component';
import { TransactionNewComponent } from './transaction-new/transaction-new.component';
import { TransactionDetailsComponent } from './transaction-details/transaction-details.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    TransactionItemsComponent,
    TransactionNewComponent,
    TransactionDetailsComponent
  ],
  imports: [
    CommonModule,
    TransactionRoutingModule,
    ReactiveFormsModule
  ]
})
export class TransactionModule { }
