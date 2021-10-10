import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Requirement} from "../models/job-offer";

@Injectable({
  providedIn: 'root'
})
export class RequirementsService {

  API_URL="http://localhost:8080/api/requirements"

  constructor(private http:HttpClient) { }

  getAllRequirements():Observable<Requirement[]>
  {
    return this.http.get<Requirement[]>(`${this.API_URL}/get-all`);
  }
}
