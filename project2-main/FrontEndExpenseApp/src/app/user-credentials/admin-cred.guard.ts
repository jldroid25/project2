import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import {AuthCredService } from './auth-cred.service';

@Injectable({
  providedIn: 'root'
})

export class AdminCredGuard implements CanActivate {
  constructor(private authCredService : AuthCredService, 
    private router : Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    return this.authCredService.isLoggedIn;
  }
  
  
}
