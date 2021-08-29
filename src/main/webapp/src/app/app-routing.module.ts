import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {HomeComponent} from "./components/home/home.component";
import {DetailedOfferComponent} from "./components/detailed-offer/detailed-offer.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {EditProfileComponent} from "./components/edit-profile/edit-profile.component";
import {AddCompanyComponent} from "./components/add-company/add-company.component";
import {EditCompanyComponent} from "./components/edit-company/edit-company.component";
import {AddJobOfferComponent} from "./components/add-job-offer/add-job-offer.component";
import {ManageJobOffersComponent} from "./components/manage-job-offers/manage-job-offers.component";

const routes: Routes = [
  { path: 'job/:id', component: DetailedOfferComponent},
  { path: 'editprofile/:id', component: EditProfileComponent},
  { path: 'add_company/:id', component: AddCompanyComponent},
  { path: 'edit-company', component: EditCompanyComponent},
  { path: 'add-job-offer', component: AddJobOfferComponent},
  { path: 'manage-offers', component: ManageJobOffersComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'profile', component: ProfileComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
