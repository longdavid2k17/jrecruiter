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
import {AlertsModule} from "angular-alert-module";
import {ToastrModule} from "ngx-toastr";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {timeout} from "rxjs/operators";
import {HomeComponent} from "./components/home/home.component";
import { DetailedOfferComponent } from './components/detailed-offer/detailed-offer.component';
import { ProfileComponent } from './components/profile/profile.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { AddCompanyComponent } from './components/add-company/add-company.component';
import { EditCompanyComponent } from './components/edit-company/edit-company.component';
import { AddJobOfferComponent } from './components/add-job-offer/add-job-offer.component';
import { ManageJobOffersComponent } from './components/manage-job-offers/manage-job-offers.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchJobComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    DetailedOfferComponent,
    ProfileComponent,
    EditProfileComponent,
    AddCompanyComponent,
    EditCompanyComponent,
    AddJobOfferComponent,
    ManageJobOffersComponent
  ],
    imports: [
      BrowserModule,
      AppRoutingModule,
      FormsModule,
      HttpClientModule,
      AlertsModule,
      ReactiveFormsModule,
      NgbModule,
      BrowserAnimationsModule,
      ToastrModule.forRoot({
        timeOut:5000,
        progressBar:true,
        progressAnimation:'increasing',
        preventDuplicates: true,
        positionClass: 'toast-top-right'
        }
      )
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
