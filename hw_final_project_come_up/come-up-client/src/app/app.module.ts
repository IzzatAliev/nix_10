import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {AdminModule} from "./pages/admin/admin.module";
import {StudentModule} from "./pages/student/student.module";
import {TeacherModule} from "./pages/teacher/teacher.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AdminModule,
    StudentModule,
    TeacherModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
