import { Component, OnInit, Type } from '@angular/core';
import {Router } from '@angular/router';
import { ReimbursService } from '../reimburs.service';
import { Reimbursement } from '../reimbursement.model';
import {FormBuilder, FormGroup} from '@angular/forms';
import { AuthCredService } from '../../user-credentials/auth-cred.service';
import {HttpClient, HttpResponse,  HttpEventType, HttpEvent, HttpErrorResponse} from '@angular/common/http';
import { Observable} from 'rxjs';
import {saveAs} from 'file-saver';


@Component({
  selector: 'app-specific-reimb',
  templateUrl: './specific-reimb.component.html',
  styleUrls: ['./specific-reimb.component.css']
})
export class SpecificReimbComponent implements OnInit {

  allReimbursements : Reimbursement[] = [];
  reimbusementObj : Reimbursement = new Reimbursement();

  //for modal form
  formValue !: FormGroup;
  errorReimbMsg : string = '';

  newReimbursement : Reimbursement = {
    reimbId      : 0,
    reimbDate    : " ",
    reimbReason  : " ",
    reimbAmount  : 0,
    reimbStatus  : " ",
    reimbRemoved : false,
    userId       : this.authCredService.retrieveUserId()
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
    private reimbursService : ReimbursService,
    private authCredService: AuthCredService,
    private formbuilder: FormBuilder) {}

  ngOnInit(): void {
     
    //for file upload - option 1
    // this.fileInfos = this.reimbursService.getFiles();
    
     //for the modal input type form value
     this.formValue = this.formbuilder.group({
      reimb_reason  :  [''],
      reimb_amount  :  [''],
    })
    //For loading the page after a reimbursement was added.
    this.loadThisUSerReimbersements(this.reimbusementObj.userId);
    
    //For getting the userId & send it to the reimb_info table user_id column
    this.loadThisUSerReimbersements(this.authCredService.retrieveUserId());

  }

  //access a function  retrieve Reimmb
  loadThisUSerReimbersements(userId : number){
    //connect to the function in service layer
   this.reimbusementService.getASpecificUserReimbursementService(this.authCredService.retrieveUserId())
   .subscribe((response: any)=> {
    console.log(response);
     this.allReimbursements = response;
   }, (error: any)=>{
    this. errorReimbMsg = 'There was some internal error! Please try again later!';
    console.log(this. errorReimbMsg);
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
    
    //for sending image to backend
    this.onUploadFiles
    
    // Let's post the data through the post request in service
    this.reimbusementService.addReimbursementService(this.newReimbursement).subscribe(
      (response: any) => {
        // To reload the page with new user Reimbursement just added
        this.loadThisUSerReimbersements(this.reimbusementObj.userId);
      },
      (error: any) => {
        console.log(error);
      })
    //Close the Form Automatically
    let ref = document.getElementById("cancel");
    ref?.click();
    this.formValue.reset();
  }
  
  //--------- upload file new option to consider ----//
  //Function to upload 
  onUploadFiles(files : File[]): void {
    //put the files inside the formData & send them back to the service
    const formData = new FormData();
    for (const file of files) {formData.append('files', file, file.name); }
    this.reimbursService.uploadFile(formData).subscribe(
      event => {
        console.log(event);
        this.reportProgress(event);
    },
    (error: HttpErrorResponse)=> {
      console.log(error);
     }
    );
  }

   //Function to download 
  onDownloadFiles(filename : string): void {
    this.reimbursService.downLoadFile(filename).subscribe(
      event => {
        console.log(event);
        this.reportProgress(event);
    },
    (error: HttpErrorResponse)=> {
      console.log(error);
     }
    );
  }

private  reportProgress(httpEvent : HttpEvent<string[] | Blob>) : void {
  switch(httpEvent.type) {
    //case for upload Progress
    case HttpEventType.UploadProgress:
      this.updateStatus(httpEvent.loaded, httpEvent.total!, "Uploading... ");
      break;
      //case for download Progress
      case HttpEventType.DownloadProgress:
        this.updateStatus(httpEvent.loaded, httpEvent.total!, "Downloading... ");
        break;

        case HttpEventType.ResponseHeader:
          console.log('Header returned', httpEvent);
          break;

          case HttpEventType.Response:
            //For upload logic
            if (httpEvent.body instanceof Array){
              //once finish loading set status to done
              this.fileStatus.status = 'done';
              for (const filename of httpEvent.body){
                //using unshift to add the file top/beginnong 
                this.filenames.unshift(filename);
              }
            } else {
              // download Loic - 
              //saveFileAs is from npm file-saver module download 
              saveAs(new File([httpEvent.body!], httpEvent.headers.get('File-Name')!,
              {type: `${httpEvent.headers.get('Content-Type')}; charset=utf-8`}));
            }
            this.fileStatus.status = 'done';
            break;
            default:
              console.log(httpEvent);
              break;
  }
}
  private updateStatus(loaded: number, total: number, requestType: string) {
   this.fileStatus.status = 'progess';
   this.fileStatus.requestType = requestType;
   this.fileStatus.percent = Math.round(100 * loaded / total);
  }

  //----------------------------
  /*
  // Don't delete - We might need to use it
  updateReimbursementDetails(){
    this.reimbusementObj.reimbStatus = this.formValue.value.reimb_status;
    //add more later if needed
    //call our update api method , pass it the object &  reimb id
    this.reimbusementService.updateReimbursementService(this.newReimbursement)
    .subscribe((res: any) => {
      alert("updated Successfully");
      //close the form automatically when done updating
    let ref = document.getElementById("cancel");
    ref?.click();
    this.formValue.reset();
  })
  }
  */

}//class