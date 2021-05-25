import { find } from 'lodash/fp';
import { BehaviorSubject } from 'rxjs';
import { concatMap, tap, map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Roles } from './roles';

interface NewUser {
  email: string;
  password: string;
  firstName: string;
  middleName: string;
  lastName: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isAuthenticated$ = new BehaviorSubject(false);
  roles$ = new BehaviorSubject([]);
  jwt$ = new BehaviorSubject('');
  isAdmin$ = new BehaviorSubject(false);

  constructor(private http: HttpClient, private router: Router) { }

  login(body: { username: string, password: string }) {
    return this.http.post('http://localhost:8080/auth', body).pipe(
      tap((response: any) => {
        this.isAuthenticated$.next(true);
        this.jwt$.next(response?.jwt);
      }),
      concatMap(
        response => this.http.get('http://localhost:8080/principal').pipe(
          tap((roles: any) => {
            const isAdmin = () => Boolean(find({ name: Roles.ADMIN })(roles));

            this.isAdmin$.next(isAdmin());
            this.roles$.next(roles);
          }),
          map(() => response)
        )
      )
    );
  }

  register(body: NewUser) {
    return this.http.post('http://localhost:8080/register', body);
  }

  toLoginPage() {
    this.router.navigate(['login']);
  }

  logout() {
    this.router.navigate(['schedule']);
    this.isAuthenticated$.next(false);
    this.roles$.next([]);
    this.jwt$.next('');
    this.isAdmin$.next(false);
  }
}
