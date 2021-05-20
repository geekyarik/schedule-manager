import { concatMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SchoolService } from '../../common';

@Injectable({
  providedIn: 'root'
})
export class SubjectsService {

  constructor(private http: HttpClient, private schoolService: SchoolService) { }

  getSubjects() {
    return this.schoolService.school$.pipe(
      concatMap(() => this.http.get('http://localhost:8080/schedule/subject'))
    );
  }

  createSubject(body: any) {
    return this.http.post('http://localhost:8080/schedule/subject', body);
  }
}