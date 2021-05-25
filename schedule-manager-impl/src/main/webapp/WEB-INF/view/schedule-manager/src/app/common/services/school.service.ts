import { map, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SchoolService {
  school$ = new BehaviorSubject(null);

  constructor(private http: HttpClient) {}

  getSchool() {
    return this.http.get('http://localhost:8080/schedule/schools').pipe(
      map((schools: any) => schools?.[0]),
      tap(school => this.school$.next(school))
    );
  }
}
