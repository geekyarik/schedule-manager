import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
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

  toggleAdmin(user: any) {
    const body = {
      username: user.email,
      roleId: 2,
      enable: !user.isAdmin
    };
    return this.http.post('http://localhost:8080/role', body).pipe(
      catchError(resp => of(resp))
    );
  }

  createSchool(body: { name: string, address: string, id?: string }) {
    return this.http.post('http://localhost:8080/schedule/school', body);
  }
}
