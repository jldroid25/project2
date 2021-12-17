import { Component, OnInit, Sanitizer, Type } from '@angular/core';
import {Router } from '@angular/router';
import { ReimbursService } from '../reimburs.service';
import { Reimbursement } from '../reimbursement.model';
import {FormBuilder, FormGroup} from '@angular/forms';
import { AuthCredService } from '../../user-credentials/auth-cred.service';
import {HttpClient, HttpResponse,  HttpEventType, HttpEvent, HttpErrorResponse} from '@angular/common/http';
import { Observable} from 'rxjs';
import {FileUploadService} from '../../../file-upload.service';
import { ReimbImage } from '../reimb-image.model';
import { DomSanitizer } from '@angular/platform-browser';


@Component({
  selector: 'app-specific-reimb',
  templateUrl: './specific-reimb.component.html',
  styleUrls: ['./specific-reimb.component.css']
})
export class SpecificReimbComponent implements OnInit {

  allReimbursements : ReimbImage[] = [];
  reimbusementObj : Reimbursement = new Reimbursement();

  //for modal form
  formValue !: FormGroup;
  errorReimbMsg : string = '';

  newReimbursement : Reimbursement= {
    reimbId      : 0,
    reimbDate    : " ",
    reimbReason  : " ",
    reimbAmount  : 0,
    reimbStatus  : "Pending",
    reimbRemoved : false,
    userId       : this.authCredService.retrieveUserId(),
    rbReceipt    : ""
   }

   //Variables for file uploads
   selectedFiles !: FileList;
    currentFile !: File;
    progress = 0;
    message = '';
    fileInfos !: Observable<any>;
    
    //new upload method 
   filenames: string[] = [];
  fileStatus  = {status : '', requestType: '', percent : 0};
  
   constructor(private reimbusementService : ReimbursService, 
    private router: Router,
    private http: HttpClient,
    private uploadService : FileUploadService,
    private authCredService: AuthCredService,
    private formbuilder: FormBuilder,
    private sanitizer: DomSanitizer) {}

  ngOnInit(): void {
     
     //for the modal input type form value
     this.formValue = this.formbuilder.group({
      reimb_reason  :  [''],
      reimb_amount  :  [''],
      reimb_status  :  [''] 
    })
    
    // for the file upload progress
    //this.fileInfos = this.uploadService.getFiles(); 

    //For getting the userId & send it to the reimb_info table user_id column
    this.loadThisUSerReimbersements(this.authCredService.retrieveUserId());

  }


  //access a function  retrieve Reimmb
  loadThisUSerReimbersements(userId : number){
    //connect to the function in service layer
   
    this.reimbusementService.getASpecificUserReimbursementService(this.authCredService.retrieveUserId())
   .subscribe((response: any)=> {
    this.allReimbursements = response;
     // iterating through the response which is an array
     // in each iteration we take the rid and fecth the image for that rid
     response.forEach((reim: ReimbImage) => {
      this.uploadService.getFile(reim.reimbId).subscribe((resp: any) => {
        console.log(resp);
        let objectURL = URL.createObjectURL(resp);       
          reim.fileUrl = this.sanitizer.bypassSecurityTrustUrl(objectURL);
          //reim.fileUrl = resp.url;
      })
     })
     
   }, (error: any)=>{
    this. errorReimbMsg = 'There was some internal error! Please try again later!';
  
   })
   
  }
  

    //Method to set the new values on to the modal table rows
  onEditRow(row : any){
   this.newReimbursement.reimbId = row.reimbId;
   //SLQ set up to only allow status to be updated by mng
   this.formValue.controls["reimb_status"].setValue(row.reimbstatus);
      //add more when needed
  }

  // to Add a reimbursement
  addReimbursement(){
    //add more fields later if needed
    this.newReimbursement.reimbReason = this.formValue.value.reimb_reason;
    this.newReimbursement.reimbAmount = this.formValue.value.reimb_amount;
    //this.newReimbursement.reimbAmount = this.formValue.value.reimb_status;
  
    // Let's post the data through the post request in service
    this.reimbusementService.addReimbursementService(this.newReimbursement)
    .subscribe((response: Reimbursement) => {
      this.upload(response.reimbId);

      },
      (error: any) => {
        console.log(error);
      })

    //Close the Form Automatically
    let ref = document.getElementById("cancel");
    ref?.click();
    this.formValue.reset();
  
}//end addreimbursement()

//---------Upload Section --------------

//Define the select method to helps us get the files
selectFile(event: any): void {
  this.selectedFiles = event.target.files;
}

//Upload method for the file upload to db
upload(rid : number): void {
  this.progress = 0;

  if (this.selectedFiles) {
    const file: File | null = this.selectedFiles.item(0);

    if (file) {
      this.currentFile = file;

      this.uploadService.upload(this.currentFile, rid).subscribe(
        (event: any) => {
          if (event.type === HttpEventType.UploadProgress) {
            this.progress = Math.round(100 * event.loaded / event.total);
          } else if (event instanceof HttpResponse) {
            this.message = event.body.message;
            this.fileInfos = this.uploadService.getFiles();
          }
            // To reload the page with new user Reimbursement just added
        this.loadThisUSerReimbersements(this.authCredService.retrieveUserId());
        },
        (err: any) => {
          console.log(err);
          this.progress = 0;

          if (err.error && err.error.message) {
            this.message = err.error.message;
          } else {
            this.message = 'Could not upload the file!';
          }

          this.currentFile;
        });

    }

    this.selectedFiles;
  }
}

} //class