import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth-service/auth.service';
import { Router } from '../../../node_modules/@angular/router';

@Component({
selector: 'app-login',
templateUrl: './login.component.html',
styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

username: string;
password: string;
loading: boolean;
errored: boolean;

constructor(private authService: AuthService, private router: Router) { }

ngOnInit() {

}

onSubmit() {
	this.loading = true;
	this.errored = false;

	this.authService.login(this.username, this.password)
	  .subscribe(loginCorrect => {
		this.loading = false;

		if (loginCorrect) {
		  this.router.navigate(['restaurants']);
		}
	}, error => {
	  this.errored = true;
	  this.loading = false;
	});
}

}