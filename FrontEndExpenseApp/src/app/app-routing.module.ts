import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './user-credentials/login/login.component';
import {SignupComponent} from './user-credentials/signup/signup.component';
import {ReimbursmentDashboardComponent} from './reimbursment/reimbursment-dashboard/reimbursment-dashboard.component';
import {SpecificReimbComponent} from './reimbursment/specific-reimb/specific-reimb.component';
import {PendingReimbComponent} from './reimbursment/pending-reimb/pending-reimb.component';
import { EmployeeDashboardComponent } from './employee/employee-dashboard/employee-dashboard.component';
import { ManagerDashboardComponent } from './manager/manager-dashboard/manager-dashboard.component';
import {EmplInfoComponent} from './employee/empl-info/empl-info.component';
import { EmployeeGuardGuard } from './employee/employee-guard.guard';
import { ManagerGuard } from './manager/manager.guard';
import {ResolvedReimbComponent} from './reimbursment/resolved-reimb/resolved-reimb.component';
import {ReimbPendingEmplComponent} from './reimbursment/reimb-pending-empl/reimb-pending-empl.component' ;
import {ReimbResolvedEmplComponent} from './reimbursment/reimb-resolved-empl/reimb-resolved-empl.component';
import {ViewEmployeesComponent} from './manager/view-employees/view-employees.component';


const routes: Routes = [  
  { path:'', component: LoginComponent },
  { path:'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent},
  { path: 'reimb-dashboard', component : ReimbursmentDashboardComponent, canActivate : [EmployeeGuardGuard]},
  { path: 'specific-reimb', component : SpecificReimbComponent, canActivate :  [EmployeeGuardGuard]},
  { path: 'resolved-reimb', component : ResolvedReimbComponent, canActivate: [ManagerGuard]},
  { path: 'pending-reimb', component : PendingReimbComponent,  canActivate: [ManagerGuard]},
  { path: 'employee-dashboard', component : EmployeeDashboardComponent, canActivate : [EmployeeGuardGuard]},
  { path: 'manager-dashboard', component : ManagerDashboardComponent, canActivate : [ManagerGuard]},
  { path: 'employee-info', component : EmplInfoComponent, canActivate :[EmployeeGuardGuard]},
  { path: 'reimb-pending-emp', component : ReimbPendingEmplComponent, canActivate : [EmployeeGuardGuard]},
  { path: 'reimb-resolved-emp', component : ReimbResolvedEmplComponent, canActivate : [EmployeeGuardGuard]},
  { path: 'view-employees', component : ViewEmployeesComponent, canActivate : [ManagerGuard]},
  //If the path is blank redirect user to login page
   {path: '', redirectTo: 'login', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
