import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AccountItemsComponent} from "./account-items/account-items.component";
import {AccountNewComponent} from "./account-new/account-new.component";
import {AccountDetailsComponent} from "./account-details/account-details.component";

const routes: Routes = [
  {
    path: '',
    component: AccountItemsComponent
  },
  {
    path: 'new',
    component: AccountNewComponent
  },
  {
    path: ':id',
    component: AccountDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountRoutingModule { }
