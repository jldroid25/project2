import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms'
import { Manager} from '../manager.model';
import { ManagerService } from '../manager.service';
import {Router } from '@angular/router';

@Component({
  selector: 'app-manager-dashboard',
  templateUrl: './manager-dashboard.component.html',
  styleUrls: ['./manager-dashboard.component.css']
})
export class ManagerDashboardComponent implements OnInit {

  //refering formGroup
 formValue      !: FormGroup;

//create an object from the manager model we created
 managerObj      : Manager = new Manager();
//For storing the response in the get Property.
 managerData !: any;
 errorManagerMsg : string = '';

newManager : Manager = {
  id          : 0,
  firstname   : " ",
  lastname    : " ",
  email       : " ",
  accessLevel : " ",
}
 constructor(private formbuilder: FormBuilder,
  private router: Router, 
  private managerService : ManagerService) {}

  ngOnInit(): void {
    this.formValue = this.formbuilder.group({
      firstname    : [''],
      lastname     : [''],
      email        : [''],
      accessLevel  : ['']
    })
    //Calling the getAllEmployee() for it to there when
    // the api call runs
    this.getAllManagers();
  }
  // To Load all managers
  getAllManagers(){
    this.managerService.getManager().subscribe(
      (response)=> {
        this.managerData = response;
      }, (error)=> {
        this.errorManagerMsg = "Unable to retrieve all managers - Retry later";
        console.log(this.errorManagerMsg);
      });
  }
  //To Removed a Manager based on its id
  deleteManager(row : any) {
    this.managerService.deleteManager(row.id).subscribe(
      (response)=> {
        alert("Employee was deleted Successfully !");
        // Reload the page
        this.getAllManagers();
      },
       (error) => console.log(error) 
    )
  }
  // to Add a reimbursement
  postManagerDetails(){
    this.managerObj.firstname = this.formValue.value.firstname;
    this.managerObj.lastname = this.formValue.value.lastname;
    this.managerObj.email = this.formValue.value.email;
    this.managerObj.accessLevel = this.formValue.value.accessLevel;
    //add more later if needed

     // Let's post the data through the post request in service
    this.managerService.postManager(this.managerObj).subscribe(
      (response) => {

     //Close the Form Automatically
    let ref = document.getElementById("cancel");
    ref?.click();
    this.formValue.reset();

     //for testing
     alert("added Successfully");
     this.getAllManagers();
      },
      (error) => {
        console.log(error);
      alert("something went wrong");//for testing
      })

    //this.router.navigate(['reimb-update'])
    //Reload the page 
    this.getAllManagers();
  }
   //Method to set the new values on to the modal table rows
   onEditRow(row : any){
    this.managerObj.id = row.id;
    //SLQ set up to only allow status to be updated by mng
    this.formValue.controls["firstname"].setValue(row.firstname);
    this.formValue.controls["lastname"].setValue(row.lastname);
    this.formValue.controls["email"].setValue(row.email);
    this.formValue.controls["accessLevel"].setValue(row.accessLevel);
    //add more when needed
   }

   updateManagerDetails(){
    this.managerObj.firstname  = this.formValue.value.firstname;
    this.managerObj.lastname  = this.formValue.value.lastname ;
    this.managerObj.email = this.formValue.value.email;
    this.managerObj.accessLevel  = this.formValue.value.accessLevel;
    //add more later if needed
    
    //call our update api method , pass it the object &  mng id
    this.managerService.updateManager(this.managerObj, this.managerObj.id)
    .subscribe((res: any) => {
      alert("updated Successfully");
      //close the form automatically when done updating
    let ref = document.getElementById("cancel");
    ref?.click();
    this.formValue.reset();
    //Reload the page 
    this.getAllManagers();
  })
  }
  
// navigate to Edit/Update Component form
navToEditComponent(mng_id : any) {
  console.log("logged : " + mng_id);
  this.router.navigate(['manager-update',mng_id])
}

}//class
