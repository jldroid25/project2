import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from '@angular/router';
import { UserCred } from '../user-cred.model';
import { AuthCredService } from '../auth-cred.service';
import { UserCredService } from '../user-cred.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  //Create the loginForm property to hold the data
  public loginForm !: FormGroup
  newUser: UserCred = new UserCred();

  //inject formBuilder
  constructor(private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private userCredService: UserCredService,
    private authCredService: AuthCredService) { }

  ngOnInit(): void {
    //initialize the form
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    })
  }
  //Login the user to the site
  login() {
    this.http.get<any>("http://localhost:4041/api/registerusers/")
      .subscribe((res: any[]) => {
        // using find() array method to find, &
        // store the matching user email & password
        const user: UserCred = res.find((a: any) => {
          //if user' email & password matches 
          return a.email === this.loginForm.value.email &&
            a.password === this.loginForm.value.password;
        });
        //check if user exist
        if (user && user.accessLevel === "employee") {
          //for testing remove later
          alert("Login SuccessFully !");
          //Access Session storage we setup in auth-cred.service.ts
          this.authCredService.storeUser(user);
          this.loginForm.reset();
          //if login is succesful navigate user to employee's dashboard
          this.router.navigate(['employee-dashboard']);
          //if user doesn't exist

        } else if (user) {
          //for testing remove later
          alert("Login SuccessFully !");
          // Access Session storage we setup in auth-cred.service.ts
          this.authCredService.storeUser(user);
          this.loginForm.reset();
          //if login is succesful navigate user to employee's dashboard
          this.router.navigate(['manager-dashboard']);
          //if user doesn't exist

        } else {
          alert("Sorry user not found !"); //for testing remove later
          this.router.navigate(['login']);
        }
        //If any error in the api call
      }, err => {
        alert("We are having some database issues try later !")
        this.router.navigate(['login']);
      });
  }//login

  //To redirect the user to appropriate dashboard based on their accessLevel
  validateLogin() {
    let validatedUser: any = this.userCredService.validateUser(this.newUser);
    if (validatedUser.userId != null) {
      this.authCredService.storeUser(validatedUser);
      this.authCredService.isLoggedIn = true;
    }
    if (validatedUser.accessLevel == "employee") {
      this.router.navigate(['employee-dashboard']);
    } else if (validatedUser.accessLevel == "manager") {
      this.router.navigate(['manager-dashboard']);
    }
  }
}