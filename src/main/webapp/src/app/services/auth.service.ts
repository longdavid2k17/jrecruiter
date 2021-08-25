import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username,
      password
    }, httpOptions);
  }

  register(username: string, email: string, password: string,name: string,surname: string,phoneNumber:string): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username,
      email,
      password,
      name,
      surname,
      phoneNumber
    }, httpOptions);
  }

  registerModerator(username: string, email: string, password: string,name: string,surname: string,phoneNumber:string): Observable<any>
  {
    return this.http.post(AUTH_API + 'signup_moderator', {
      username,
      email,
      password,
      name,
      surname,
      phoneNumber
    }, httpOptions);
  }
}
