import { map as _map, groupBy, find, reduce } from 'lodash/fp';
import { catchError, concatMap, map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SchoolService } from '../../common';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {
  week = ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY'];

  constructor(private http: HttpClient, private schoolService: SchoolService) {}

  getGroups(): any {
    return this.schoolService.school$.pipe(
      concatMap((school: any) => this.http.get(`http://localhost:8080/available/group?schoolId=${school?.id}`))
    );
  }

  getRooms(): any {
    return this.schoolService.school$.pipe(
      concatMap((school: any) => this.http.get(`http://localhost:8080/available/classroom?schoolId=${school?.id}`))
    );
  }

  getTeachers(): any {
    return this.schoolService.school$.pipe(
      concatMap((school: any) => this.http.get(`http://localhost:8080/available/teacher?schoolId=${school?.id}`)),
      map(teachers => _map((t: any) => ({ ...t, name: `${ t.firstName } ${ t.lastName }`}), teachers))
    );
  }

  getAvailGroups(day: string, ordinalNumber: number): any {
    return this.schoolService.school$.pipe(
      concatMap(
        (school: any) => this.http.get(`http://localhost:8080/group/available?day=${day}&ordinalNumber=${ordinalNumber}&schoolId=${school?.id}`)
      )
    );
  }

  getAvailRooms(day: string, ordinalNumber: number): any {
    return this.schoolService.school$.pipe(
      concatMap(
        (school: any) => this.http.get(`http://localhost:8080/classroom/available?day=${day}&ordinalNumber=${ordinalNumber}&schoolId=${school?.id}`)
      )
    );
  }

  getAvailTeachers(day: string, ordinalNumber: number, subjectId: string): any {
    return this.schoolService.school$.pipe(
      concatMap(
        (school: any) => this.http.get(`http://localhost:8080/teacher/available?day=${day}&ordinalNumber=${ordinalNumber}&subjectId=${subjectId}&schoolId=${school?.id}`)
      ),
      map(teachers => _map((t: any) => ({ ...t, name: `${ t.firstName } ${ t.lastName }`}), teachers))
    );
  }

  getLessons(id: string, unit: string) {
    return this.http.get(`http://localhost:8080/schedule/lesson/${unit}/${id}`).pipe(
      map(data => groupBy('dayOfWeek', data)),
      map(grouped => reduce(
          (acc: any[], day: string) => [...acc, { day, value: groupBy('ordinalNumber', grouped[day]) || [] }],
          [],
          this.week
        )
      )
    );
  }

  saveLesson() {}

  removeLesson(id: string) {
    return this.http.post(`http://localhost:8080/schedule/lesson/${id}/delete`, {}).pipe(
      catchError(() => of({}))
    );
  }
}
