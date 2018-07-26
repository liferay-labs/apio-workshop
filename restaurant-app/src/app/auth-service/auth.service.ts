import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { tap } from 'rxjs/operators';
import { BehaviorSubject } from '../../../node_modules/rxjs';

@Injectable({
providedIn: 'root'
})
export class AuthService {

public authenticatedSubject = new BehaviorSubject(localStorage.getItem('username'));

constructor(private httpClient: Http) {}

saveAuth(basicAuth: string, username: string) {
	localStorage.setItem('auth', basicAuth);
	localStorage.setItem('username', username);
}

getAuth(): string {
	return localStorage.getItem('auth');
}

isAuthenticated(): boolean {
	return this.getAuth() != null;
}

login(username: string, password: string) {
	const basic = 'Basic ' + btoa(`${username}:${password}`);
	const headers = new Headers();
	headers.set('Authorization', basic);

	return this.httpClient.get('http://localhost:8080/o/api/login', {headers})
	  .pipe(tap(response => {
		const {userWrapper: {name}} = response.json();
		this.authenticatedSubject.next(name);

		this.saveAuth(basic, name);
	  }));
}

logout() {
	localStorage.removeItem('auth');
	this.authenticatedSubject.next(null);
}
}