import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../services/token-storage.service";
import {User, UserService} from "../../services/user.service";
import {ToastrService} from "ngx-toastr";
import {RecruitmentProcess} from "../../models/recruitment-process";
import { DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';
import {Company} from "../../models/company";
import {CompanyService, RecruiterAccount} from "../../services/company.service";

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
  recruiterCompany?:Company;

  constructor(private token: TokenStorageService,private userService: UserService,private toastr:ToastrService,private _DomSanitizationService: DomSanitizer,private companyService:CompanyService) { }

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
        if(this.currentUserFromRepo.recruiter && this.currentUserFromRepo.recruiter_account_id!=null)
        {
          this.companyService.getCompanyByConnectedAccount(this.currentUserFromRepo.recruiter_account_id).subscribe(
            data => {
              this.recruiterCompany = data.connectedCompany;

            }, error => {
              this.toastr.error(error,'Wystąpił błąd!');
            }
          )
        }
        else if(this.currentUserFromRepo.recruiter && !this.currentUserFromRepo.recruiter_account_id)
        {
          this.toastr.error('Uzupełnij swoje konto o dane firmy aby w pełni skorzystać z konta!','Brak danych');
        }
      },error => {
      this.toastr.error(error,'Wystąpił błąd!');
      }
    );

  }

  photoURL() {
    return this._DomSanitizationService.bypassSecurityTrustUrl(this.currentUserFromRepo.profileImgUrl);
  }

}
