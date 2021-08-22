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
  getUserById(id:string):Observable<User>
  {
    const searchUrl=`${API_URL}/getbyid/${id}`;
    return this.http.get<User>(searchUrl);
  }

  update(id:string,formValues:User):Observable<User>
  {
    console.log(id+" "+formValues);
    return this.http.put<User>(`${API_URL}/update/${id}`,formValues);
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
