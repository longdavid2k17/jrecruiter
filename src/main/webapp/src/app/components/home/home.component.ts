import { Component, OnInit } from '@angular/core';
import {JobOffer} from "../../models/job-offer";
import {JobOfferService} from "../../services/job-offer.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  jobOffers: JobOffer[]=[];

  constructor(private jobOfferService:JobOfferService,private toastr:ToastrService) { }

  ngOnInit(): void
  {
    this.handleList();
  }

  handleList()
  {
    this.jobOfferService.getOffersHomeComponent().subscribe(
      data =>
      {
        this.jobOffers = data;
      },
      err =>
      {
        this.toastr.error(err,'Błąd!')
      }
    );
  }
}
