import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const API_URL = 'http://localhost:8080/api/utils';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor(private http: HttpClient) { }

  getSoftwareVersion():Observable<string>
  {
    return this.http.get<string>(`${API_URL}/version`);
  }
}
