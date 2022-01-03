import { Injectable } from '@angular/core';
import {Manager} from './manager.model'

@Injectable({
  providedIn: 'root'
})
export class AuthManagerService {

  //isLoggedIn: boolean = false;
  isLoggedIn: boolean = true;

  storeManager(manager: Manager){
    sessionStorage.setItem("managerData", JSON.stringify(manager));
  }

  retrieveManager(): Manager{
    var data: any = sessionStorage.getItem("managerData");
    return JSON.parse(data == null?'':data);
  }

  removeManager(){
    sessionStorage.removeItem("managerData");
  }

  retrieveManagerType(){
    var data: any = sessionStorage.getItem("managerData");
    var manager: Manager = JSON.parse(data == null?'':data);
    return manager.accessLevel;
  }
  constructor() { }
}
