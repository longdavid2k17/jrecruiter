import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {TokenStorageService} from "../../services/token-storage.service";
import {ToastrService} from "ngx-toastr";
import {User, UserService} from "../../services/user.service";
import {RecruiterService} from "../../services/recruiter.service";
import {Company} from "../../models/company";
import {JobOfferService} from "../../services/job-offer.service";
import {JobOffer} from "../../models/job-offer";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {faEdit, faInbox, faTrashAlt} from "@fortawesome/free-solid-svg-icons";
import {Router} from "@angular/router";
import {ConfirmationDialogService} from "../confirmation-dialog/confirmation-dialog.service";

@Component({
  selector: 'app-manage-job-offers',
  templateUrl: './manage-job-offers.component.html',
  styleUrls: ['./manage-job-offers.component.css']
})
export class ManageJobOffersComponent implements OnInit,AfterViewInit
{
  displayedColumns: string[] = ['id', 'positionTitle', 'contractType', 'positionApplications','manage','actions'];

  currentLoggedUserCredential?:any;
  user?:User;
  userCompany?:Company;
  jobOffers:JobOffer[] = [];
  dataSource: MatTableDataSource<JobOffer>;

  // @ts-ignore
  @ViewChild(MatPaginator) paginator: MatPaginator;
  // @ts-ignore
  @ViewChild(MatSort) sort: MatSort;
  editIcon = faEdit;
  deleteIcon = faTrashAlt;
  manageIcon = faInbox;

  constructor(private token: TokenStorageService,
              private toastr: ToastrService,
              private userService:UserService,
              private jobOfferService:JobOfferService,
              private recruiterService:RecruiterService,
              private confirmationDialogService: ConfirmationDialogService,
              private router:Router) {
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
              if(this.userCompany)
              {
                this.jobOfferService.getCompanyOffers(this.userCompany.id).subscribe(data =>
                  {
                    this.jobOffers = data;
                    console.log(data)
                  }
                )
              }
            })
          }
          else
          {
            this.toastr.error('Twoje konto nie jest kontem rekrutera!','Błąd')
          }
        }
      )
    }

    this.dataSource = new MatTableDataSource(this.jobOffers);
  }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  edit(id:number)
  {
    console.log(`Edycja oferty ${id}`)
    this.router.navigate([`/job/${id}`]);
    //todo przekierowanie do formularza edycji oferty
  }

  delete(id:any){
    this.confirmationDialogService.confirm('Potwierdź', 'Jesteś pewien że chcesz usunąć element o ID '+id+"?")
      .then((confirmed) => {
        this.jobOfferService.delete(id).subscribe(data => {
            this.toastr.success(data,`Sukces!`);
            window.location.reload();
        },
          error => {
            this.toastr.error(error.error.message,'Błąd')
          })
      })
      .catch(() => console.log('User dismissed the dialog (e.g., by using ESC, clicking the cross icon, or clicking outside the dialog)'));
  }
}
