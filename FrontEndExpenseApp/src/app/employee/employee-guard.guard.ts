import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import {AuthEmployeeServiceService} from './auth-employee-service.service'

@Injectable({
  providedIn: 'root'
})
export class EmployeeGuardGuard implements CanActivate {

  constructor(private authEmpService: AuthEmployeeServiceService) {

  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot) : boolean {
    return true;
  }
}
