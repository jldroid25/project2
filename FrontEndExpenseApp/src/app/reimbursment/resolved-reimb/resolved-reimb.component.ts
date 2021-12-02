import { Component, OnInit } from '@angular/core';
import {Router } from '@angular/router';
import { ReimbursService } from '../reimburs.service';
import { Reimbursement } from '../reimbursement.model';


@Component({
  selector: 'app-resolved-reimb',
  templateUrl: './resolved-reimb.component.html',
  styleUrls: ['./resolved-reimb.component.css']
})
export class ResolvedReimbComponent implements OnInit {

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
    this.loadResolvedReimbursements();
  }
  loadResolvedReimbursements(){
    this.reimbusementService.getResolvedReimbursementService()
    .subscribe((response)=>{
      this.allReimbursements = response;
    }, 
    (error)=> {
      this.errorReimbMsg = "Unable to retrieve Resolved Reveimbursemnets - Retry later";
      console.log(this.errorReimbMsg);
    });
  }

}
