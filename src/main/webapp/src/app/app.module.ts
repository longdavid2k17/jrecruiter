import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchJobComponent } from './components/search-job/search-job.component';
import { LoginComponent } from './components/login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RegisterComponent } from './components/register/register.component';
import {HttpClientModule} from "@angular/common/http";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    SearchJobComponent,
    LoginComponent,
    RegisterComponent
  ],
    imports: [
      BrowserModule,
      AppRoutingModule,
      FormsModule,
      HttpClientModule,
      ReactiveFormsModule,
      NgbModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
