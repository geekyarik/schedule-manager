import { map as _map } from 'lodash/fp';
import { of } from 'rxjs';
import { catchError, concatMap, map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SchoolService } from '../../common';

@Injectable({
  providedIn: 'root'
})
export class ScheduleGenerationService {

  constructor(private http: HttpClient, private schoolService: SchoolService) {}

  getRules() {
    return this.schoolService.school$.pipe(
      concatMap((school: any) => this.http.get(`http://localhost:8080/schedule/rule?schoolId=${school?.id}`))
    );
  }

  getGroups(): any {
    return this.schoolService.school$.pipe(
      concatMap((school: any) => this.http.get(`http://localhost:8080/schedule/group?schoolId=${school?.id}`))
    );
  }

  getSubjects(): any {
    return this.schoolService.school$.pipe(
      concatMap(() => this.http.get('http://localhost:8080/schedule/subject'))
    );
  }

  getRooms(): any {
    return this.schoolService.school$.pipe(
      concatMap((school: any) => this.http.get(`http://localhost:8080/schedule/classroom?schoolId=${school?.id}`))
    );
  }

  getTeachers(): any {
    return this.schoolService.school$.pipe(
      concatMap((school: any) => this.http.get(`http://localhost:8080/schedule/teacher?schoolId=${school?.id}`)),
      map(teachers => _map((t: any) => ({ ...t, name: `${ t.firstName } ${ t.lastName }`}), teachers))
    );
  }

  saveRule(value: any) {
    return this.http.post(`http://localhost:8080/schedule/rule`, this.mapToBackend(value))
  }

  removeRule(id: string) {
    return this.http.post(`http://localhost:8080/schedule/rule/${id}/delete`, {});
  }

  generate() {
    return this.schoolService.school$.pipe(
      concatMap((school: any) => this.http.get(`http://localhost:8080/schedule/generate?schoolId=${school?.id}`))
    );
  }

  mapToBackend(value: any) {
    const {
      name = '',
      timesPerWeek = 1,
      group = {},
      subject = {},
      preferredRoom = {},
      preferredTeacher = {}
    } = value || {};

    return {
      name,
      timesPerWeek,
      group: { id: group?.id },
      subject: { id: subject?.id },
      preferredRoom: { id: preferredRoom?.id },
      preferredTeacher: { id: preferredTeacher?.id }
    };
  }
}
