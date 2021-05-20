import { Observable, of } from 'rxjs';
import { catchError, concatMap, shareReplay } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SchoolService } from '../../common';

@Injectable({
  providedIn: 'root'
})
export class TeachersService {

  constructor(private http: HttpClient, private schoolService: SchoolService) { }

  getSubjects() {
    return this.schoolService.school$.pipe(
      concatMap((school: any) => this.http.get('http://localhost:8080/schedule/subject')),
      shareReplay(1)
    );
  }

  getTeachers(): Observable<any> {
    return this.schoolService.school$.pipe(
      concatMap((school: any) => this.http.get(`http://localhost:8080/schedule/teacher?schoolId=${school?.id}`))
    );
  }

  getTeachersSubjects(id: string) {
    return this.http.get(`http://localhost:8080/schedule/subject/teacher/${id}`);
  }

  addSubject(body: { teacherId: string, subjectId: string }) {
    return this.http.post('http://localhost:8080/schedule/teacher/addSubject', body).pipe(
      catchError(() => of({}))
    );
  }

  dropSubject(body: { teacherId: string, subjectId: string }) {
    return this.http.post('http://localhost:8080/schedule/teacher/dropSubject', body).pipe(
      catchError(() => of({}))
    );
  }
}
