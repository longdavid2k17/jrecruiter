import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../services/token-storage.service";
import {User, UserService} from "../../services/user.service";
import {ToastrService} from "ngx-toastr";
import {RecruitmentProcess} from "../../models/recruitment-process";
import { DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUserFromToken: any
  currentUserFromRepo:User = new User();
  userRecruitmentProcesses:RecruitmentProcess[]=[];
  imgSrcProperty:any;

  constructor(private token: TokenStorageService,private userService: UserService,private toastr:ToastrService,private _DomSanitizationService: DomSanitizer) { }

  ngOnInit(): void
  {
    this.currentUserFromToken = this.token.getUser();
    this.getProfileData();
    this.imgSrcProperty=this._DomSanitizationService.bypassSecurityTrustUrl(this.currentUserFromRepo.profileImgUrl);
  }

  getProfileData()
  {
    this.userService.getUserByEmail(this.currentUserFromToken.email).subscribe(data=>
      {
        this.currentUserFromRepo = data;
      },error => {
      this.toastr.error(error,'Wystąpił błąd!');
      }
    );
  }

  photoURL() {
    return this._DomSanitizationService.bypassSecurityTrustUrl(this.currentUserFromRepo.profileImgUrl);
  }

}
