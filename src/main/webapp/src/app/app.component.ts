import { Component } from '@angular/core';
import {TokenStorageService} from "./services/token-storage.service";
import {Observable} from "rxjs";
import {UtilsService} from "./services/utils.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;
  version?:string;

  constructor(private tokenStorageService: TokenStorageService,private utilsService:UtilsService) { }

  ngOnInit(): void {
    this.utilsService.getSoftwareVersion().subscribe(data => {
      this.version = data.version;
    });
    console.log(this.version);
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');

      this.username = user.username;
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
