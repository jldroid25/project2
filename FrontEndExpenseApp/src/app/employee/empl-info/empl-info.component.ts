import { Component, OnInit } from '@angular/core';
import { UserCred } from '../../user-credentials/user-cred.model';
import { UserCredService } from '../../user-credentials/user-cred.service';
import { FormBuilder, FormGroup } from '@angular/forms'
import { AuthCredService } from '../../user-credentials/auth-cred.service';

@Component({
  selector: 'app-empl-info',
  templateUrl: './empl-info.component.html',
  styleUrls: ['./empl-info.component.css']
})
export class EmplInfoComponent implements OnInit {

  //refering formGroup
  formValue !: FormGroup;

  //create an object from the employee model we created
  employeeModelObj: UserCred = new UserCred();

  //For storing the response in the get Property.
  userData !: any;

  //Our Form builder Constructor 
  constructor(private formbuilder: FormBuilder,
    private authCredService: AuthCredService,
    private api: UserCredService) { }


  ngOnInit(): void {
    /* Load this object  To get current logging user 
       Using retrieveUser()'s SessionStorage created in auth-cred.service.ts.
       In order to get data only for this user in our database.
    */
    this.employeeModelObj = this.authCredService.retrieveUser();

    //For Testing Remove Later
    console.log("Checking the emp obj -->");
    console.log(this.employeeModelObj);

    this.formValue = this.formbuilder.group({
      username    :  [''],
      password    :  [''],
      email       :  [''],
      accessLevel :  ['']
    })
  }

  //------------ Get API Call to fetch data from Db------//

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

        //close the form automatically when done updating
        let ref = document.getElementById("cancel");
        ref?.click();
        this.formValue.reset();
      })
  }
}
