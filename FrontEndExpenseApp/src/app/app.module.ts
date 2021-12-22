import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './user-credentials/login/login.component';

import { EmployeeDashboardComponent } from './employee/employee-dashboard/employee-dashboard.component';
import { ManagerDashboardComponent } from './manager/manager-dashboard/manager-dashboard.component';
import { ReimbursmentDashboardComponent } from './reimbursment/reimbursment-dashboard/reimbursment-dashboard.component';
import { SignupComponent } from './user-credentials/signup/signup.component';
import { EmplInfoComponent } from './employee/empl-info/empl-info.component';
import { SpecificReimbComponent } from './reimbursment/specific-reimb/specific-reimb.component';
import { ResolvedReimbComponent } from './reimbursment/resolved-reimb/resolved-reimb.component';
import { PendingReimbComponent } from './reimbursment/pending-reimb/pending-reimb.component';
import { ReimbPendingEmplComponent } from './reimbursment/reimb-pending-empl/reimb-pending-empl.component';
import { ReimbResolvedEmplComponent } from './reimbursment/reimb-resolved-empl/reimb-resolved-empl.component';
import { ViewEmployeesComponent } from './manager/view-employees/view-employees.component';
import { Header1Component } from './header/header1/header1.component';
import { Header2Component } from './header/header2/header2.component';
import { FooterComponent } from './footer/footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
  

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    
    EmployeeDashboardComponent,
    ManagerDashboardComponent,
    ReimbursmentDashboardComponent,
    SignupComponent,
    EmplInfoComponent,
    SpecificReimbComponent,
    ResolvedReimbComponent,
    PendingReimbComponent,
    ReimbPendingEmplComponent,
    ReimbResolvedEmplComponent,
    ViewEmployeesComponent,
    Header1Component,
    Header2Component,
    FooterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
   
   
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

