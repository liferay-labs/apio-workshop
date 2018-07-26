import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth-service/auth.service';
import { Router } from '../../../node_modules/@angular/router';

@Component({
selector: 'app-header',
templateUrl: './header.component.html',
styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

userLogged: string;

constructor(private authService: AuthService, private router: Router) { }

ngOnInit() {
	this.authService.authenticatedSubject.subscribe(name => this.userLogged = name);
}

logout() {
	this.authService.logout();
	this.router.navigate(['login']);
}

}