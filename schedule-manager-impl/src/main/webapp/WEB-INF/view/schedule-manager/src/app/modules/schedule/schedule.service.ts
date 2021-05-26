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
        (school: any) => this.http.post(`http://localhost:8080/schedule/group/available`, {
          day,
          ordinalNumber,
          schoolId: school?.id
        })
      )
    );
  }

  getAvailRooms(day: string, ordinalNumber: number): any {
    return this.schoolService.school$.pipe(
      concatMap(
        (school: any) => this.http.post(`http://localhost:8080/schedule/classroom/available`, {
          day,
          ordinalNumber,
          schoolId: school?.id
        })
      )
    );
  }

  getAvailTeachers(day: string, ordinalNumber: number, subjectId: string): any {
    return this.schoolService.school$.pipe(
      concatMap(
        (school: any) => this.http.post(`http://localhost:8080/schedule/teacher/available`, {
          day,
          ordinalNumber,
          subjectId,
          schoolId: school?.id
        })
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

  saveLesson(dayOfWeek: any, slot: number, value: any) {
    const lesson = dayOfWeek?.value[slot]?.[0];
    const { name, subject } = lesson;
    const { day, classroom, group, ordinalNumber, teacher } = value;

    return this.schoolService.school$.pipe(
      concatMap(
        (school: any) => this.http.post(`http://localhost:8080/schedule/lesson`, {
          day,
          name,
          ordinalNumber,
          subject: { id: subject.id },
          group: { id: group.id },
          classroom: { id: classroom.id },
          teacher: { id: teacher.id },
          schoolId: school?.id
        })
      )
    );
  }

  removeLesson(id: string) {
    return this.http.post(`http://localhost:8080/schedule/lesson/${id}/delete`, {}).pipe(
      catchError(() => of({}))
    );
  }
}
