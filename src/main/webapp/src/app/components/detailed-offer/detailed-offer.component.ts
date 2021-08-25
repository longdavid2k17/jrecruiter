import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {JobOfferService} from "../../services/job-offer.service";
import {JobOffer} from "../../models/job-offer";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-detailed-offer',
  templateUrl: './detailed-offer.component.html',
  styleUrls: ['./detailed-offer.component.css']
})
export class DetailedOfferComponent implements OnInit {

  offer!:JobOffer;

  constructor(private jobOfferService: JobOfferService,private route:ActivatedRoute,private titleService:Title) { }

  ngOnInit(): void
  {
    this.route.paramMap.subscribe(() => {
      this.handleOfferDetails();
    })
  }

  handleOfferDetails()
  {
    let offerId: number;
    // @ts-ignore
    offerId = +this.route.snapshot.paramMap.get('id');
    this.jobOfferService.getOffer(offerId).subscribe(
      data => {
        this.offer = data;
        this.titleService.setTitle(`${this.offer.positionTitle} - ${this.offer.company.companyName}`);
      }
    )
  }

}