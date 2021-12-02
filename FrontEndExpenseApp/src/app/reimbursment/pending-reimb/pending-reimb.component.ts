import { Component, OnInit } from '@angular/core';
import {Router } from '@angular/router';
import { ReimbursService } from '../reimburs.service';
import { Reimbursement } from '../reimbursement.model';

@Component({
  selector: 'app-pending-reimb',
  templateUrl: './pending-reimb.component.html',
  styleUrls: ['./pending-reimb.component.css']
})
export class PendingReimbComponent implements OnInit {
  allReimbursements : Reimbursement[] = [];
  reimbusementObj : Reimbursement = new Reimbursement();
  errorReimbMsg : string = '';

  newReimbursement : Reimbursement = {
   reimbId      : 0,
   reimbDate    : "",
   reimbReason  : " ",
   reimbAmount  : 0,
   reimbStatus  : " ",
   reimbRemoved : false
  }

  constructor(private reimbusementService : ReimbursService, 
    private router: Router) { }

  ngOnInit(): void {
    this.loadPendingReimbursements();
  }
  loadPendingReimbursements(){
    this.reimbusementService.getPendingReimbursementService()
    .subscribe((response)=>{
      this.allReimbursements = response;
    }, 
    (error)=> {
      this.errorReimbMsg = "Unable to retrieve Pending Reveimbursemnets - Retry later";
      console.log(this.errorReimbMsg);
    });
  }
}