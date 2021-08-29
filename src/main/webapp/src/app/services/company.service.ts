import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Company} from "../models/company";
import {User} from "./user.service";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  COMPANY_API_URL="http://localhost:8080/api/company"
  RECRUITER_ACCOUNTS_API_URL="http://localhost:8080/api/recruiter"

  constructor(private http:HttpClient) { }

  getAllCompanies():Observable<Company[]>
  {
    return this.http.get<Company[]>(`${this.COMPANY_API_URL}/all`);
  }

  getCompanyById(id:string):Observable<Company>
  {
    return this.http.get<Company>(`${this.COMPANY_API_URL}/id/${id}`);
  }

  getCompanyByConnectedAccount(id:number):Observable<RecruiterAccount>
  {
    return this.http.get<RecruiterAccount>(`${this.RECRUITER_ACCOUNTS_API_URL}/id/${id}`);
  }

    saveWithRecruiterAccount(accountId: string, value: Company)
    {
      return this.http.post<Company>(`${this.COMPANY_API_URL}/save/forId/${accountId}`,value);
    }

  editCompany(id:string,value: Company)
  {
    return this.http.post<Company>(`${this.COMPANY_API_URL}/edit/${id}`,value);
  }
}

export interface RecruiterAccount
{
  id:number;
  connectedCompany?:Company;
}
