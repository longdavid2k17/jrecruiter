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
import {NgMultiSelectDropDownModule} from "ng-multiselect-dropdown";
import {MatTableModule} from "@angular/material/table";
import {CdkTableModule} from "@angular/cdk/table";
import {MatSelectModule} from "@angular/material/select";
import {MatInputModule} from "@angular/material/input";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatButtonModule} from "@angular/material/button";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
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
    ManageJobOffersComponent,
    ConfirmationDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    AlertsModule,
    ReactiveFormsModule,
    NgMultiSelectDropDownModule.forRoot(),
    NgbModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
        timeOut: 5000,
        progressBar: true,
        progressAnimation: 'increasing',
        preventDuplicates: true,
        positionClass: 'toast-top-right'
      }
    ),
    MatTableModule,
    CdkTableModule,
    MatSelectModule,
    MatInputModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
