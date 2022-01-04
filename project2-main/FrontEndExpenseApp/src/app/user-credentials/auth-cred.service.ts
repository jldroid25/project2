import { Injectable } from '@angular/core';
import {UserCred }  from './user-cred.model';
import { Reimbursement } from '../reimbursment/reimbursement.model';

@Injectable({
  providedIn: 'root'
})
export class AuthCredService {

  
  // this AuthService was created to keep track of 
      // 1) whether a user has logged in or not
      // 2) the user who has logged in and manipulate it in the session storage
      isLoggedIn: boolean = false;

      /* The SessionStorage for login Users 
      & track them accross components ---
      */

      //A method to store the user session
      storeUser(user: UserCred ){
        //For testing remove Later
        console.log("From StoreUSer  -->");
        console.log(user);
        sessionStorage.setItem("userData", JSON.stringify(user));
      }
    
      //call this line in any components
      // retrieve user info we store above in storeUser()
      retrieveUser(): UserCred {
        var data: any = sessionStorage.getItem("userData");
        return JSON.parse(data == null?'{}': data);
      }
    
      //All Removed your session afterwards
      removeUser(){
        sessionStorage.removeItem("userData");
      }

      //For Reteiving User_id
      retrieveUserId(){
        var data: any = sessionStorage.getItem("userData");
        if(!data){
          return 0;
        }
        var user: UserCred  = JSON.parse(data);
        return user.userId;
      }
       //For Reteiving user type or accessLevel
       retrieveUserType(){
        var data: any = sessionStorage.getItem("userData");
        var user: UserCred  = JSON.parse(data == null?'':data);
        return user.accessLevel;
      }

  constructor() { }
}