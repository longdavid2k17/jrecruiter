import { Injectable } from '@angular/core';
import {JobOffer} from "../models/job-offer";
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders, HttpRequest} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class JobOfferService
{
  private homeSelector:boolean = false;
  private baseUrl='http://localhost:8080/api/offers';

  constructor(private http:HttpClient,private token: TokenStorageService) { }

  getOffersHomeComponent():Observable<JobOffer[]>
  {
    this.homeSelector=true;
    return this.getOffers(this.baseUrl);
  }

  getAllOffers():Observable<JobOffer[]>
  {this.homeSelector=false;
    return this.getOffers(this.baseUrl);
  }

  getOffer(offerId: number) :Observable<JobOffer>
  {
    const offerUrl = `${this.baseUrl}/id/${offerId}`;
    return this.http.get<JobOffer>(offerUrl);
  }

  getContractTypes() :Observable<string[]>
  {
    const contractsUrl = `${this.baseUrl}/contract-types`;
    return this.http.get<string[]>(contractsUrl);
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

  save(value: JobOffer)
  {
    let tokenInBrowser = this.token.getToken();
    const newRequest = new HttpRequest('POST', `${this.baseUrl}/save`, value,
      {
        responseType: "text", reportProgress: true, headers: new HttpHeaders(
          { "Authorization": "Bearer "+tokenInBrowser },
        )
      });

    return this.http.request(newRequest);
  }
}

interface GetResponseJobOffer
{
  _embedded:
    {
      jobOffers: JobOffer[];
    }
}
