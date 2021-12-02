import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reimbursement} from './reimbursement.model';


@Injectable({
  providedIn: 'root'
})

export class ReimbursService {

  reimbUrl = " http://localhost:4041/api/reimbursements";

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
  
}