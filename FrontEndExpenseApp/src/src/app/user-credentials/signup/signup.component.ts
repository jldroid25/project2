import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from "@angular/forms"
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  //Property to hold the formGroup
  public signupForm !: FormGroup;

  //Inject  formBuilder inside the formGroup, & the
  // httpClient, also import router for navigation
  constructor(private formBuilder: FormBuilder,
    private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    //Initialize the form
    this.signupForm = this.formBuilder.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', Validators.required, Validators.email],
      username: ['', Validators.required,Validators.minLength(6)],
      password: ['', Validators.required, Validators.minLength(8)],
      accessLevel: ['', Validators.required],
    })
  }
  //the signUp method
  signUp() {
    //Make a post http call to store thevalue
    this.http.post<any>("http://localhost:7777/api/users", this.signupForm.value)
      .subscribe((res: any) => {
        //for testing remove later
        this.signupForm.reset();
        //if signup is successful navigate the user
        // to login 
        this.router.navigate(['login'])
      }, (err: any) => {
        //for testing remove later
        alert("something went wrong");
      });
  }//signup

}//class