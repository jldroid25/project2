import { Component, OnInit } from '@angular/core';
import {AuthCredService} from '../../user-credentials/auth-cred.service'

@Component({
  selector: 'app-header2',
  templateUrl: './header2.component.html',
  styleUrls: ['./header2.component.css']
})
export class Header2Component implements OnInit {

  constructor(private authService : AuthCredService) { }

  ngOnInit(): void {
  }
  isLoggedIn() {
    return this.authService.isLoggedIn;
  }
  retrieveUserType(){
    return this.authService.retrieveUserType;
  }

}
