import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { map as _map, find } from 'lodash/fp';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Roles } from '../../core/auth';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getUsers(): Observable<any> {
    return this.http.get('http://localhost:8080/users').pipe(
      map(users => _map((user: any) => ({
          ...user,
          isAdmin: Boolean(find({ name: Roles.ADMIN })(user.roles))
        }), users)
      )
    );
  }

  getSchools() {
    return this.http.get('http://localhost:8080/schedule/schools');
  }

  setAdmin(user: any) {
    const body = {
      userId: user.email,
      roleId: 1,
      enable: true
    };
    return this.http.post('http://localhost:8080/role', body);
  }
}
