import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TransactionItemsComponent} from "./transaction-items/transaction-items.component";
import {TransactionNewComponent} from "./transaction-new/transaction-new.component";
import {TransactionDetailsComponent} from "./transaction-details/transaction-details.component";

const routes: Routes = [
  {
    path: '',
    component: TransactionItemsComponent
  },
  {
    path: 'new',
    component: TransactionNewComponent
  },
  {
    path: ':id',
    component: TransactionDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransactionRoutingModule { }
