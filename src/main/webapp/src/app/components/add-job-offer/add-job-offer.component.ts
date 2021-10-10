import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {Company} from "../../models/company";
import {CompanyService} from "../../services/company.service";
import {JobOfferService} from "../../services/job-offer.service";
import {RequirementsService} from "../../services/requirements.service";
import {Requirement} from "../../models/job-offer";
import {first} from "rxjs/operators";
import {TokenStorageService} from "../../services/token-storage.service";
import {User, UserService} from "../../services/user.service";
import {RecruiterService} from "../../services/recruiter.service";

@Component({
  selector: 'app-add-job-offer',
  templateUrl: './add-job-offer.component.html',
  styleUrls: ['./add-job-offer.component.css']
})
export class AddJobOfferComponent implements OnInit {

  // @ts-ignore
  jobOfferForm: FormGroup;
  userCompany?:Company;
  submitted = false;
  companies:Company[] = [];
  selectedCompany?:Company;
  dropdownList:Requirement[] = [];
  selectedItems = [];
  dropdownSettings = {};
  selectedContractType?:string;
  contractTypes:string[] = [];
  isSaveFailed = false;
  errorMessage = '';
  currentLoggedUserCredential?:any;
  user?:User;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private token: TokenStorageService,
              private router: Router,
              private toastr: ToastrService,
              private companyService:CompanyService,
              private jobOfferService:JobOfferService,
              private requiremetnsService:RequirementsService,
              private recruiterService:RecruiterService,
              private userService:UserService) { }

  ngOnInit(): void {
    this.jobOfferForm = this.formBuilder.group({
      company: ['', Validators.required],
      positionTitle: ['', Validators.required,Validators.minLength(4) ],
      positionDescription: ['', Validators.required,Validators.minLength(4)],
      lowEndPaymentRange: ['', Validators.required,Validators.minLength(3)],
      highEndPaymentRange: ['', Validators.required,Validators.minLength(3)],
      contractType: ['', Validators.required],
      requirements: ['', Validators.required]
    });
    this.companyService.getAllCompanies().subscribe(data => {
      this.companies = data;
    })
    this.requiremetnsService.getAllRequirements().subscribe(data => {
      this.dropdownList = data;
    })
    this.jobOfferService.getContractTypes().subscribe(data => {
      this.contractTypes = data;
    })
    this.currentLoggedUserCredential = this.token.getUser();

    if(this.currentLoggedUserCredential)
    {
      this.userService.getUserByEmail(this.currentLoggedUserCredential.email).subscribe(data =>
        {
          this.user = data;
          if(this.user.recruiter_account_id)
          {
            this.recruiterService.getCompany(this.user.recruiter_account_id).subscribe(data =>
            {
              this.userCompany = data.connectedCompany;
            })
          }
          else
          {
            this.toastr.error('Twoje konto nie jest kontem rekrutera!','Błąd')
          }
        }
      )
    }


    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'name',
      selectAllText: 'Wybierz wszystkie',
      searchPlaceholderText:'Szukaj',
      unSelectAllText: 'Odznacz wszystkie',
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
  }

  onSubmit(): void {
    this.jobOfferService.save(this.jobOfferForm.value).pipe(first())
      .subscribe({
        next: () => {
          this.toastr.success('Utworzono ofertę', 'Sukces');
          this.router.navigate(['/profile']);
        },
        error: error => {
          console.log(error.error());
          this.toastr.error(error, 'Błąd!')
          this.errorMessage = error.message;
          this.isSaveFailed = true;
        }
      });
  }

  onItemSelect(event: any) {
    this.selectedItems = event.target.value;
  }
  onSelectAll(event: any) {
    this.selectedItems = event.target.value;
  }

  changeCompany(event: any)
  {
    this.selectedCompany = event.target.value;
  }

  changeContractType(event: any)
  {
    this.selectedContractType = event.target.value;
  }
}

