import { CanActivate, Router } from '@angular/router';
import { AuthService} from '../auth-service/auth.service';
import { Injectable } from '../../../node_modules/@angular/core';

@Injectable({
providedIn: 'root'
})
export class AuthGuard implements CanActivate {

constructor(private authService: AuthService, private router: Router) {}

canActivate(): boolean {
	if (!this.authService.isAuthenticated()) {
	  this.router.navigate(['login']);
	  return false;
	}

	return true;
}
}