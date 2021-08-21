import { Injectable } from '@angular/core';
import {JobOffer} from "../models/job-offer";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class JobOfferService
{
  private homeSelector:boolean = false;
  private baseUrl='http://localhost:8080/api/offers';

  constructor(private http:HttpClient) { }

  getOffersHomeComponent():Observable<JobOffer[]>
  {
    this.homeSelector=true;
    return this.getOffers(this.baseUrl);
  }

  getAllOffers():Observable<JobOffer[]>
  {this.homeSelector=false;
    return this.getOffers(this.baseUrl);
  }

  private getOffers(searchUrl: string): Observable<JobOffer[]>
  {
    if(!this.homeSelector)
    {
      return this.http.get<JobOffer[]>(`${searchUrl}/all`);
    }
    else
      return this.http.get<JobOffer[]>(`${searchUrl}/home`);

  }

  getJobOffersPaginate(thePage: number,
                       thePageSize: number): Observable<GetResponseJobOffer> {

    // need to build URL based on category id, page and size
    const searchUrl = `${this.baseUrl}/all/pageable?page=${thePage}&size=${thePageSize}`;

    return this.http.get<GetResponseJobOffer>(searchUrl);
  }
}

interface GetResponseJobOffer
{
  _embedded:
    {
      jobOffers: JobOffer[];
    }
}
