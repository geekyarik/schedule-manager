import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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

  constructor(private http: HttpClient) { }

  login(body: { username: string, password: string }) {
    return this.http.post('http://localhost:8080/auth', body).pipe(
      tap(() => this.isAuthenticated$.next(true))
    );
  }

  register(body: NewUser) {
    return this.http.post('http://localhost:8080/register', body);
  }
}
