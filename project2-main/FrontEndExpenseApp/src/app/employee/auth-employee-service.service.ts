import { Injectable } from '@angular/core';
import {Employee } from './employee.model'
import {UserCred }  from '../user-credentials/user-cred.model'

@Injectable({
  providedIn: 'root'
})

// this AuthService was created to keep track of 
// 1) whether a user has logged in or not
// 2) the user who has logged in and manipulate it in the session storage
export class AuthEmployeeServiceService {

   isLoggedIn: boolean = true;

   // this AuthService was created to keep track of 
      // 1) whether a user has logged in or not
      // 2) the user who has logged in and manipulate it in the session storage
    //isLoggedIn: boolean = false;

      //-------USer or Emplyoeee ---//
      storeUser(user: UserCred ){
        sessionStorage.setItem("userData", JSON.stringify(user));
      }
    
      retrieveUser(): UserCred {
        var data: any = sessionStorage.getItem("userData");
        return JSON.parse(data == null?'':data);
      }
    
      removeUser(){
        sessionStorage.removeItem("userData");
      }
    
      retrieveUserType(){
        var data: any = sessionStorage.getItem("userData");
        var user: UserCred  = JSON.parse(data == null?'':data);
        return user.accessLevel;
      }
    
   //isLoggedIn: boolean = true;

   /*

   storeEmployee(employee: Employee){//double check
     sessionStorage.setItem("employeeData", JSON.stringify(employee));
   }
 
   retrieveEmployee(): Employee{
     var data: any = sessionStorage.getItem("employeeData");
     return JSON.parse(data == null?'':data);
   }
 
   removeEmployee(){
     sessionStorage.removeItem("employeeData");
   }
 
   retrieveEmployeeType(){
     var data: any = sessionStorage.getItem("employeeData");
     var employee: Employee = JSON.parse(data == null?'':data);
     return employee.accessLevel;
   }
   */

  constructor() { }
}
