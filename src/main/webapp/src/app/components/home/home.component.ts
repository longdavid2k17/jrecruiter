import { Component, OnInit } from '@angular/core';
import {JobOffer} from "../../models/job-offer";
import {JobOfferService} from "../../services/job-offer.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  jobOffers: JobOffer[]=[];

  constructor(private jobOfferService:JobOfferService) { }

  ngOnInit(): void
  {
    this.handleList();
  }

  handleList()
  {
    this.jobOfferService.getAllOffers().subscribe(
      data => {
        this.jobOffers = data;
      },
      err =>
      {
        //this.content = JSON.parse(err.error).message;
      }
    );
    /*setTimeout(()=> {
      this.jobOffers.forEach( (element) =>
      {
        this.companyService.getCompanyByJobOfferId(element.id).subscribe(data =>{
          element.company=data;
        })
      });
    },500)*/
  }
}
