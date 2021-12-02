import { Component, OnInit } from '@angular/core';
import {Router } from '@angular/router';
import { ReimbursService } from '../reimburs.service';
import { Reimbursement } from '../reimbursement.model';
import { AuthCredService } from '../../user-credentials/auth-cred.service';

@Component({
  selector: 'app-reimb-pending-empl',
  templateUrl: './reimb-pending-empl.component.html',
  styleUrls: ['./reimb-pending-empl.component.css']
})
export class ReimbPendingEmplComponent implements OnInit {

  allReimbursements : Reimbursement[] = [];
  reimbusementObj : Reimbursement = new Reimbursement();
  errorReimbMsg : string = '';

  newReimbursement : Reimbursement = {
    reimbId      : 0,
    reimbDate    : " ",
    reimbReason  : " ",
    reimbAmount  : 0,
    reimbStatus  : " ",
    reimbRemoved : false,
   }
  
   constructor(private reimbusementService : ReimbursService, 
    private router: Router, 
    private authCredService: AuthCredService) {}

  ngOnInit(): void {
    //auto loading the user's pending reimbursement(s)
    this.loadPendingUSerReimbersements(this.authCredService.retrieveUserId());
  }

  //access a function  retrieve Reimmb
  loadPendingUSerReimbersements(userId : number){
    //connect to the function in service layer
   this.reimbusementService.getUserPendingReimbursementService(this.authCredService.retrieveUserId())
   .subscribe((response: any)=> {
     this.allReimbursements = response;
   }, (error: any)=>{
    this. errorReimbMsg = 'There was some internal error! Please try again later!';
    console.log(this. errorReimbMsg);
   })
  }
}