import { Component } from '@angular/core';
import {TokenStorageService} from "./services/token-storage.service";
import {Observable} from "rxjs";
import {UtilsService} from "./services/utils.service";
import {NewsletterService} from "./services/newsletter.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AlertsService} from "angular-alert-module";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  mailForm = new FormGroup({
    emailAddress: new FormControl('',[
      Validators.required,
      Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")])
  });

  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;
  version?:string;
  errorMessage = '';
  successMessage = '';

  constructor(private tokenStorageService: TokenStorageService,private utilsService:UtilsService, private newsletterService:NewsletterService,private toastr:ToastrService) { }

  ngOnInit(): void {
    this.utilsService.getSoftwareVersion().subscribe(data => {
      this.version = data.version;
    });
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');

      this.username = user.username;
    }
  }

  saveMailingAddress()
  {
    if(this.mailForm.value==="" || !this.mailForm.value)
    {
     this.toastr.error('Błędny adres email!','Błąd');
    }
    else
    {
      this.newsletterService.saveMailingAddress(this.mailForm.value.emailAddress).subscribe(
        response => {
          this.toastr.success(response,'Sukces');
        },
        error => {
          this.errorMessage = error.error.message;
          this.toastr.error(error.error.message,'Błąd')
        }
      );
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
