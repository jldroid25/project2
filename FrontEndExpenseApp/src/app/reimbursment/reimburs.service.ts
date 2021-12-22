import { Injectable } from '@angular/core';
import { Reimbursement} from './reimbursement.model';
import {HttpClient, HttpRequest, HttpHeaders, HttpEvent} from '@angular/common/http';
import { Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})

export class ReimbursService {

  reimbUrl = "http://localhost:7777/api/reimbursements";
  
  //For the file upload/download testing
  server = "http://localhost:7777"

  constructor(private http: HttpClient) { }

  //get Request To consume the reimbursments endpoints
  getAllReimbursementService() : Observable<Reimbursement[]> {
    return this.http.get<Reimbursement[]>(this.reimbUrl);
  }
  //post Request endpoints
  addReimbursementService(newReimb : Reimbursement) : Observable<Reimbursement> {
    return this.http.post<Reimbursement>(this.reimbUrl, newReimb);
  }
  //delete Request endpoints
  removeReimbursementService(reimb_id : number) : Observable<Reimbursement> {
    return this.http.delete<Reimbursement>(this.reimbUrl+"/"+reimb_id);
  }
  //To get One Request endpoint
  getAReimbursementService(reimb_id : number): Observable<Reimbursement> {
    return this.http.get<Reimbursement>(this.reimbUrl+"/"+reimb_id);
  }
   // For updating Request endpoint
   updateReimbursementService(updateReimb : Reimbursement) : Observable<Reimbursement>{
    return this.http.put<Reimbursement>(this.reimbUrl+"/"+updateReimb.reimbId, updateReimb);
  }
  
  //create a new endpoint that take a user_id & return all
  // reimbursement for that user_id.
  getASpecificUserReimbursementService(user_id : number): Observable<Reimbursement> {
    return this.http.get<Reimbursement>(this.reimbUrl+"user/"+user_id);
  }

  //For loading Specific USer Pending Reimbursements
  getUserPendingReimbursementService(user_id : number): Observable<Reimbursement> {
    return this.http.get<Reimbursement>(this.reimbUrl+"pending/"+user_id);
  }

   //For loading Specific USer Resolved Reimbursements
   getUserResolvedReimbursementService(user_id : number): Observable<Reimbursement> {
    return this.http.get<Reimbursement>(this.reimbUrl+"resolved/"+user_id);
  }

  // Get All pending reimb - for Manager Dashboard
  getPendingReimbursementService() : Observable<Reimbursement[]> {
    return this.http.get<Reimbursement[]>(this.reimbUrl+"pending/");
  } 

  // for All resolved reimb - For Manager - DashBoard
  getResolvedReimbursementService() : Observable<Reimbursement[]> {
    return this.http.get<Reimbursement[]>(this.reimbUrl+"resolved/");
  } 

  // ---- File upload Option 1

  //The opload file function
  uploadFile(file : File, rid: number): Observable<any> { 
    const formData = new FormData(); 
    formData.append('files', file);
    
    // for testing -- removed later
    console.log("James--> Ferom service layer ");
    console.log(formData.get('files')); 

    return this.http.put<any>(`${this.server}/api2/upload/`+rid, formData
     );
  }

   //The download file function
   downLoadFile(imgId : number): Observable<HttpEvent<Blob>> {
    return this.http.get(`${this.server}/api/download/${imgId}`, {
      //passing some options , progress & events
      reportProgress: true,
      observe : 'events',
      //Tell Http the response type will be of Blog
      responseType : 'blob'
    });
  }

}