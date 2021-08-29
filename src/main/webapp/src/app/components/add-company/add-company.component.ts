import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {Company} from "../../models/company";
import {CompanyService} from "../../services/company.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css']
})
export class AddCompanyComponent implements OnInit
{

  submitted = false;
  // @ts-ignore
  savetoExistingForm: FormGroup;
  // @ts-ignore
  savetoNewOneForm: FormGroup;
  companies:Company[] = [];
  selectedValue?:Company;
  accountId!:string;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router, private toastr: ToastrService,private companyService:CompanyService) {
  }

  ngOnInit(): void {
    this.accountId = this.route.snapshot.params['id'];

    this.savetoExistingForm = this.formBuilder.group({
      company: ['', Validators.required]
    });
    this.savetoNewOneForm = this.formBuilder.group({
      companyName: ['', Validators.required],
      localization: ['', Validators.required],
      description: ['', Validators.required]
    });
    this.companyService.getAllCompanies().subscribe(data => {
      this.companies = data;
    })
  }

  changeCompany(event: any)
  {
    this.selectedValue = event.target.value;
  }

  onSubmit() {
    console.log(JSON.stringify(this.savetoNewOneForm.value));
    this.companyService.saveWithRecruiterAccount(this.accountId,this.savetoNewOneForm.value).pipe(first())
      .subscribe({
        next: () => {
          this.toastr.success('Utworzono firmę', 'Sukcess');
          this.router.navigate(['/profile']);
        },
        error: error => {
          console.log(error.error());
          this.toastr.error(error, 'Błąd!')
        }
      });
  }
}
