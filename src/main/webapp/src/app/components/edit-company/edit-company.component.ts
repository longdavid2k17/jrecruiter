import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {CompanyService, RecruiterAccount} from "../../services/company.service";
import {first} from "rxjs/operators";
import {TokenStorageService} from "../../services/token-storage.service";
import {User, UserService} from "../../services/user.service";

@Component({
  selector: 'app-edit-company',
  templateUrl: './edit-company.component.html',
  styleUrls: ['./edit-company.component.css']
})
export class EditCompanyComponent implements OnInit {

  // @ts-ignore
  form: FormGroup;
  currentLoggedUser?:any;
  currentUser?:User;
  recruiterAccount?:RecruiterAccount;
  submitted?:boolean;

  constructor(private formBuilder: FormBuilder,private toastr: ToastrService, private companyService:CompanyService,private tokenStorage:TokenStorageService,private userService:UserService) { }

  ngOnInit(): void
  {
    this.form = this.formBuilder.group({
      company: ['', Validators.required],
      localization: ['', Validators.required],
      positionDescription: ['', Validators.required]
    });

    this.currentLoggedUser = this.tokenStorage.getUser();

    this.userService.getUserByEmail(this.currentLoggedUser.email).subscribe(
      data => {
        this.currentUser = data;
        if(this.currentUser?.recruiter_account_id!=null)
        {
          this.companyService.getCompanyByConnectedAccount(this.currentUser.recruiter_account_id).subscribe(
            data => {
              this.recruiterAccount = data;
              // @ts-ignore
              this.companyService.getCompanyById(this.recruiterAccount.connectedCompany?.id)
                .pipe(first())
                .subscribe(x => this.form.patchValue(x));
            }
          );
        }
      }
    )
  }

  onSubmit()
  {
    // @ts-ignore
    this.companyService.editCompany(this.recruiterAccount?.connectedCompany?.id,this.form.value).pipe(first()).subscribe({
      next: () => {
        this.toastr.success('Zaktualizowano dane firmy', 'Sukcess');
      },
      error: error => {
        console.log(error);
        this.toastr.error(error, 'Błąd!')
      }
    });
  }
}
