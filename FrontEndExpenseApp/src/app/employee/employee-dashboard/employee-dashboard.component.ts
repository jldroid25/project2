import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms'
import { AuthCredService } from 'src/app/user-credentials/auth-cred.service';
import { UserCred } from '../../user-credentials/user-cred.model';
import { UserCredService } from '../../user-credentials/user-cred.service'


@Component({
  selector: 'app-employee-dashboard',
  templateUrl: './employee-dashboard.component.html',
  styleUrls: ['./employee-dashboard.component.css']
})
export class EmployeeDashboardComponent implements OnInit {

  //refering formGroup
  formValue !: FormGroup;

  //create an object from the employee model we created
  employeeModelObj: UserCred = new UserCred();
  //For storing the response in the get Property.
  employeeData !: any;


  //Our Form builder Constructor 
  constructor(private formbuilder: FormBuilder,
    // private api : EmployeeService,
    private authCredService: AuthCredService,
    private api: UserCredService
  ) { }

  ngOnInit(): void {

    this.formValue = this.formbuilder.group({
      username: [''],
      password: [''],
      email: [''],
      accessLevel: ['']
    })
    //Calling the getAllEmployee() for it to there when
    // the api call runs
    this.getAllEmployees();
  }
  //------------ Post API Call---------------//
  // posting method to send data from our EmployeeModelOjb 
  //object
  // Method to pass all the value from the form to the server.
  postEmployeeDetails() {
    this.employeeModelObj.username = this.formValue.value.username;
    this.employeeModelObj.password = this.formValue.value.password;
    this.employeeModelObj.email = this.formValue.value.email;
    this.employeeModelObj.accessLevel = this.formValue.value.accessLevel;

    // Let's post the data, passing the obj as param
    this.api.postUser(this.employeeModelObj)
      /* subscribe the Employee Api 
       if successful post the data to the server
      */

      //Remove the "any" datatye for Project-1 codes
      .subscribe((res: any) => {
        console.log(res);// for testing remove later

        //Close the form automatically --> get this element by id
        let ref = document.getElementById("cancel")
        //now close the reference to the modal form
        //once data is added to db sucessfully
        ref?.click();

        // then reset the form
        this.formValue.reset();
        alert("Employee was added successfully");
        //Display Employee data from the form 
        // as soon as it was successfully posted.
        this.getAllEmployees();

      }, //else throw this error msg
        (err: any) => {
          alert("something went wrong");//for testing
        })
  }

  //------------ Get API Call to fetch data from Db------//
  getAllEmployees() {
    this.api.getUser()
      //Remove the "any" datatype for Project-1 codes
      .subscribe((res: any) => {
        //use the employeeData property above to store the data
        this.employeeData = res;
      })
  }

  //------------ Delete API Call to fetch data from Db------//
  //deleteEmployee(row: any){} //for degugging til bug fix below

  deleteUser(row: any) {
    this.api.deleteUser(row.id)
      //Remove the "any" datatye for Project-1 codes
      .subscribe((res: any) => {
        // for testing
        alert("Employee was deleted Successfully !");
        //reload the page
        this.getAllEmployees();
      })
  }


  //------------ Update API Call to fetch data from Db------//

  //Method to set the new values on to the modal table rows
  //Remove the "any" datatype for Project-1 codes
  onEdit(row: any) {
    /* */
    this.employeeModelObj.userId = row.userId;
    this.formValue.controls["username"].setValue(row.username);
    this.formValue.controls["password"].setValue(row.password);
    this.formValue.controls["email"].setValue(row.email);
    this.formValue.controls["accessLevel"].setValue(row.accessLevel);
  }
  // to perform actual update
  updateEmployeeDetails() {
    this.employeeModelObj.username = this.formValue.value.username;
    this.employeeModelObj.lastname = this.formValue.value.lastname;
    this.employeeModelObj.email = this.formValue.value.email;
    this.employeeModelObj.accessLevel = this.formValue.value.accessLevel;
    //call our update api method , pass it the object &  eemployee id
    this.api.updateUser(this.employeeModelObj, this.employeeModelObj.userId)
      .subscribe((res: any) => {
        //for testing
        alert("Updated Successfully");
        //close the form automatically when done updating
        let ref = document.getElementById("cancel");
        ref?.click();
        this.formValue.reset();
        //Reload the page 
        this.getAllEmployees();
      })
  }

}//class
