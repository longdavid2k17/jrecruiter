import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const API_URL = "http://localhost:8080/api/users"

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private http: HttpClient) { }

  getUserByEmail(email:string):Observable<User>
  {
    const searchUrl=`${API_URL}/getbyemail/${email}`;
    return this.http.get<User>(searchUrl);
  }
}

export class User
{
  id:number=0;
  email:string="";
  username:string="";
  name:string="";
  surname:string="";
  profileImgUrl:string="";
  phoneNumber:string="";
  cvPath:string="";
  websiteUrl:string="";
  githubUrl:string="";
  twitterUrl:string="";
}
