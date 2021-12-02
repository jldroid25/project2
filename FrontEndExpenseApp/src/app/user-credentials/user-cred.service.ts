import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import  {UserCred } from './user-cred.model';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {map} from 'rxjs/operators';
import { Observable } from 'rxjs';


 //providerln: is for specify the provider of the decorated service 
  //class with the 'root' as a default,
@Injectable({
  providedIn: 'root'
})
export class UserCredService {
  //Create the loginForm property to hold the data
  public loginForm !: FormGroup

  constructor(private http : HttpClient) { }

     
  //HTTP  post method
  postUser(data : UserCred ){
    return this.http.post<UserCred >("http://localhost:4041/api/registerusers", data)
    // map it to the HttpClient call on line #4
    .pipe(map( (res: UserCred) =>{
      return res;
    }))
  }
    //HTTP get method
  getUser(){
    return this.http.get<UserCred >("http://localhost:4041/api/registerusers")
    .pipe(map((res:UserCred)=>{
      return res;
    }))
  }

   //HTTP  Put/update method
   updateUser(data : UserCred ,userId: number){
    return this.http.put<UserCred>("http://localhost:4041/api/registerusers/"+userId,data)
    .pipe(map((res:UserCred)=>{
      return res;
    }))
  }

  /*
  //HTTP  delete method
  deleteUser(userId : number){
    return this.http.delete<UserCred>("http://localhost:4041/api/registerusers/"+userId)
    .pipe(map((res:UserCred)=>{
      return res;
    }))
  }
  */
 //HTTP  delete method
 deleteUser(userId : number) : Observable<UserCred>{
  return this.http.delete<UserCred>("http://localhost:4041/api/registerusers/"+userId);
}

getAUserService(userId: number): Observable<UserCred>{
  return this.http.get<UserCred>("http://localhost:4041/api/registerusers/"+userId);
}
  validateUser(user : UserCred) {
    this.http.get<any>("http://localhost:4041/api/registerusers")
  .subscribe((res: any[])=>{
    // using find() array method to find, &
    // store the matching user email & password
    const user : UserCred = res.find((a:any)=>{
      //if user' email ,  password, AccessLevel match 
      return a.email === this.loginForm.value.email &&
      a.password === this.loginForm.value.password &&
      a.accessLevel === this.loginForm.value.accessLevel;
    } );
    return UserCred;
  });
}

}
