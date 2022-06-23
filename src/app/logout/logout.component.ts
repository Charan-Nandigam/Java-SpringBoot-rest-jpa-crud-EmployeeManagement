import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor( private authentocationService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
    this.authentocationService.logout();
    this.router.navigate(['login'])
  }

}
