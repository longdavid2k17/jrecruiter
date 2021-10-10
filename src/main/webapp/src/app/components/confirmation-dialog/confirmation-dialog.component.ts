import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
  styleUrls: ['./confirmation-dialog.component.css']
})
export class ConfirmationDialogComponent implements OnInit {

  // @ts-ignore
  @Input() title: string;
  // @ts-ignore
  @Input() message: string;
  // @ts-ignore
  @Input() btnOkText: string;
  // @ts-ignore
  @Input() btnCancelText: string;

  constructor(private activeModal: NgbActiveModal) { }

  ngOnInit() {
  }

  public decline() {
    this.activeModal.close(false);
  }

  public accept() {
    this.activeModal.close(true);
  }

  public dismiss() {
    this.activeModal.dismiss();
  }

}
