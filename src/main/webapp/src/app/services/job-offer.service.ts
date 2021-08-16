import { Injectable } from '@angular/core';
import {JobOffer} from "../models/job-offer";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class JobOfferService
{
  private baseUrl='http://localhost:8080/api/offers';

  constructor(private http:HttpClient) { }

  getAllOffers():Observable<JobOffer[]>
  {
    return this.getOffers(this.baseUrl);
  }

  private getOffers(searchUrl: string): Observable<JobOffer[]>
  {
    return this.http.get<GetResponseJobOffer>(`${searchUrl}/all`).pipe(map(response => response._embedded.joboffers));
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
      joboffers: JobOffer[];
    }
}
