import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class UploadImageService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient,private token: TokenStorageService) { }

  upload(file: File): Observable<HttpEvent<any>> {
    /*const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', `${this.baseUrl}/users/upload_image`, formData, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);*/
    const data: FormData = new FormData();
    let tokenInBrowser = this.token.getToken();

    data.append('file', file);

    const newRequest = new HttpRequest('POST', `${this.baseUrl}/users/upload_image`, data,
      {
        responseType: "text", reportProgress: true, headers: new HttpHeaders(
          { "Authorization": "Bearer "+tokenInBrowser },
        )
      });

    return this.http.request(newRequest);
  }
}
