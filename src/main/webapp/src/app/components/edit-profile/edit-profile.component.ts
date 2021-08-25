import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {first} from "rxjs/operators";
import {UserService} from "../../services/user.service";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {UploadImageService} from "../../services/upload-image.service";

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  // @ts-ignore
  form: FormGroup;
  imageForm!: FormGroup;
  selectedFiles!: FileList;
  currentFile?:File;
  currentFileUpload!: File;
  id?: string;
  loading = false;
  submitted = false;
  progress: { percentage: number } = { percentage: 0 };

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router, private toastr: ToastrService, private userService: UserService,
              private uploadService:UploadImageService) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      websiteUrl: [''],
      githubUrl: [''],
      twitterUrl: [''],
      bio: [''],
      email: ['', [Validators.required, Validators.email]],
    });

    this.imageForm = this.formBuilder.group({
      imageFile: ['', Validators.required]
    });

    // @ts-ignore
    this.userService.getUserById(this.id)
      .pipe(first())
      .subscribe(x => this.form.patchValue(x));
  }

  get f() { // @ts-ignore
    return this.form.controls;
  }

  onSubmit() {
    this.submitted = true;
    // @ts-ignore
    if (this.form.invalid) {
      return;
    }
    this.loading = true;
    this.updateUser();
  }

  private updateUser() {
    // @ts-ignore
    this.userService.update(this.id, this.form.value).pipe(first())
      .subscribe({
        next: () => {
          this.toastr.success('Zaktualizowano profil', 'Sukcess');
          this.router.navigate(['/profile']);
        },
        error: error => {
          this.toastr.error(error, 'Błąd!')
          this.loading = false;
        }
      });
  }

  upload() {
    this.progress.percentage = 0;

    // @ts-ignore
    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.upload(this.currentFileUpload).subscribe(event => {
        if (event.type === HttpEventType.UploadProgress) {
          // @ts-ignore
          this.progress.percentage = Math.round(100 * event.loaded / event.total);
        }
      }
    );
  }

  selectFile(event:any) {
    this.selectedFiles = event.target.files;
  }

  onFileChange(event:any) {
    this.currentFile = event.target.files;
    console.log(this.currentFile)
  }
}
