import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EmailManagerService {

  //Provide your Mailthis.to email to build the api
  private api = 'https://mailthis.to/jldroid25@protonmail.com'

  constructor(private http: HttpClient) { }

  postMEssage(input : any){
    return this.http.post(this.api, input, {responseType: 'text'})
    .pipe(map((response : any)=> {
        if(response){
           //for testing remove later 
          console.log(response);
          return response;
        }
      }, (error: any)=> {
        //for testing remove later 
        console.log(error);
        return error;
      })
    )
  }
}
