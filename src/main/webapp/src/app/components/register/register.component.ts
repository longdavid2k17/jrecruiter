import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: any = {
    username: null,
    email: null,
    password: null,
    name: null,
    surname: null,
    phoneNumber: null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService,private toastr:ToastrService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const { username, email, password, name, surname,phoneNumber } = this.form;

    this.authService.register(username, email, password, name, surname,phoneNumber).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.toastr.success('Zarejestrowano!');
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

}
