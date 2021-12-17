import { Component, OnInit } from '@angular/core';
import {Router } from '@angular/router';
import { ReimbursService } from '../reimburs.service';
import { Reimbursement } from '../reimbursement.model';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-reimbursment-dashboard',
  templateUrl: './reimbursment-dashboard.component.html',
  styleUrls: ['./reimbursment-dashboard.component.css']
})
export class ReimbursmentDashboardComponent implements OnInit {

  //reimbFlag : boolean = false;
  allReimbursements : Reimbursement[] = [];
  reimbusementObj : Reimbursement = new Reimbursement();
  //for modal form
  formValue !: FormGroup;
  errorReimbMsg : string = '';

  newReimbursement : Reimbursement = {
   reimbId      : 0,
   reimbDate    : "",
   reimbReason  : " ",
   reimbAmount  : 0,
   reimbStatus  : "Pending",
   reimbRemoved : false,
   rbReceipt  : "" ,
   userId       : 0
  }

  constructor(private reimbusementService : ReimbursService, 
    private router: Router, 
    private formbuilder: FormBuilder) {}

  ngOnInit(): void {
    
    //for the modal input type form value
    this.formValue = this.formbuilder.group({
      reimb_status : ['']
    })
    
    //TO load all reimbursement on page load
    this.loadReimbursements();
  }
  //To Load all Reimbursements
  loadReimbursements(){
    this.reimbusementService.getAllReimbursementService().subscribe(
      (response)=>{
        this.allReimbursements = response;
      }, 
      (error)=> {
        this.errorReimbMsg = "Unable to retrieve all Reveimbursemnets - Retry later";
        console.log(this.errorReimbMsg);
      });
  }

  //To Removed a Rebursement based on its id
  removeReimbursement(rbId : number) {
    this.reimbusementService.removeReimbursementService(rbId).subscribe(
      (response)=> {
        this.loadReimbursements();
      },
       (error) => console.log(error) 
    )
  }
  // to Add a reimbursement
  addReimbursement(){
    this.newReimbursement.reimbReason = this.formValue.value.reimb_reason;
    this.newReimbursement.reimbAmount = this.formValue.value.reimb_amount;
    //add more later if needed

     // Let's post the data through the post request in service
    this.reimbusementService.addReimbursementService(this.newReimbursement).subscribe(
      (response) => {
        this.loadReimbursements();
      },
      (error) => {
        console.log(error);
      })
      //for testing
    //alert("added Successfully");
    
    //Close the Form Automatically
    let ref = document.getElementById("cancel");
    ref?.click();
    this.formValue.reset();
    this.router.navigate(['reimb-dashboard'])
    //Reload the page 
    this.loadReimbursements();
  }

   //Method to set the new values on to the modal table rows
  onEditRow(row : any){
   this.newReimbursement.reimbId = row.reimbId;
   //SLQ set up to only allow status to be updated by mng
   this.formValue.controls["reimb_status"].setValue(row.reimb_status);
   //add more when needed
  }

  updateReimbursementDetails(){
    this.reimbusementObj.reimbStatus = this.formValue.value.reimb_status;
    //add more later if needed
    
    //call our update api method , pass it the object &  reimb id
    this.reimbusementService.updateReimbursementService(this.newReimbursement)
    .subscribe(
      (response) => {
        console.log(response);
        //To Reload the page after the update is done
        this.router.navigate(['reimb-dashboard']);
      alert("updated Successfully");
      //close the form automatically when done updating
    let ref = document.getElementById("cancel");
    ref?.click();
    this.formValue.reset();
    //Reload the page 
    this.loadReimbursements();
  })
  }

}//