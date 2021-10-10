import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {JobOffer} from "../models/job-offer";

@Injectable({
  providedIn: 'root'
})
export class RecruiterService {

  private baseUrl='http://localhost:8080/api/recruiter';

  constructor(private http:HttpClient) { }

  getCompany(accountId: number) :Observable<any>
  {
    const url = `${this.baseUrl}/id/${accountId}`;
    return this.http.get<any>(url);
  }
}
