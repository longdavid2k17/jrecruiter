import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class NewsletterService
{
  baseURL = "http://localhost:8080/api/newsletter/save";

  constructor(private http: HttpClient) { }

  saveMailingAddress(emailAddress:string):Observable<any>
  {
    return this.http.post(this.baseURL, emailAddress, httpOptions);
  }
}
