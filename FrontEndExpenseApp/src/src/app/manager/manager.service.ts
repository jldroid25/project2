import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
//import { Observable } from 'rxjs';
import { Manager} from './manager.model';
import {map} from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})

export class ManagerService {
  //
  constructor(private http : HttpClient) {}

   
  //HTTP  post method
  postManager(data : any){
    return this.http.post<Manager>("http://localhost:4041/api/managers", data)
    // map it to the HttpClient call on line #4
    .pipe(map( (res: Manager) =>{
      return res;
    }))
  }
    //HTTP get method
  getManager(){
    //api to retrieve managers based on where clause on DaoIMPL
    return this.http.get<Manager>("http://localhost:4041/api/registerusers")
    .pipe(map((res:Manager)=>{
      return res;
    }))
  }

   //HTTP  Put/update method
   updateManager(data : Manager,id: number){
    return this.http.put<Manager>("http://localhost:4041/api/managers/"+id,data)
    .pipe(map((res:Manager)=>{
      return res;
    }))
  }

  //HTTP  delete method
  deleteManager(id : number){
    return this.http.delete<Manager>("http://localhost:4041/api/managers/"+id)
    .pipe(map((res:Manager)=>{
      return res;
    }))
  }

  /*

  mngUrl = " http://localhost:4041/api/managers";

  constructor(private http: HttpClient) { }

  //get Request To consume the managers endpoints
  getAllManagerService() : Observable<Manager[]> {
    return this.http.get<Manager[]>(this.mngUrl);
  }
  //post Request endpoints
  addManagerService(newMng : Manager) : Observable<Manager> {
    return this.http.post<Manager>(this.mngUrl, newMng);
  }
  //delete Request endpoints
  removeManagerService(mng_id : number) : Observable<Manager> {
    return this.http.delete<Manager>(this.mngUrl+"/"+mng_id);
  }
  //To get One Request endpoint
  getAManagerService(mng_id : number): Observable<Manager> {
    return this.http.get<Manager>(this.mngUrl+"/"+mng_id);
  }
  
  // update Request endpoint
  updateManagertService(updateMng : Manager, id: number) : Observable<Manager>{
    return this.http.put<Manager>(this.mngUrl+"/"+id, updateMng);
  }
*/
}