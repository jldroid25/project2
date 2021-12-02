import { Injectable } from '@angular/core';

//Import HttpClient not the HttpClientModule
import {HttpClient} from '@angular/common/http';
//import {Employee } from './employee.model';
import {UserCred } from '../user-credentials/user-cred.model'
// let's import map from RXJS operators
import {map} from 'rxjs/operators';

 //providerln: is for specify the provider of the decorated service 
  //class with the 'root' as a default,
@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
//Now the HttPClient we will work 
  constructor(private http : HttpClient) { }

   
  //HTTP  post method
  postEmployee(data : any){
    return this.http.post<any>("http://localhost:4041/api/registerusers", data)
    // map it to the HttpClient call on line #4
    .pipe(map( (res: any) =>{
      return res;
    }))
  }
    //HTTP get method
  getEmployee(){
       //api to retrieve employees based on where clause on DaoIMPL
    return this.http.get<UserCred>("http://localhost:4041/api/registerusers")
    .pipe(map((res:UserCred)=>{
      return res;
    }))
  }

   //HTTP  Put/update method
   updateEmployee(data : UserCred ,id: number){
    return this.http.put<UserCred>("http://localhost:4041/api/registerusers/"+id,data)
    .pipe(map((res:UserCred)=>{
      return res;
    }))
  }

  //HTTP  delete method
  deleteEmployee(id : number){
    return this.http.delete<UserCred>("http://localhost:4041/api/registerusers/"+id)
    .pipe(map((res:UserCred)=>{
      return res;
    }))
  }
}//class
