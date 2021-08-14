import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {VersionResponse} from "../models/version-response";

@Injectable({
  providedIn: 'root'
})
export class UtilsService
{
  API_URL = 'http://localhost:8080/api/utils/version';

  constructor(private http: HttpClient) { }

  getSoftwareVersion():Observable<VersionResponse>
  {
    return this.http.get<VersionResponse>(this.API_URL);
  }
}
