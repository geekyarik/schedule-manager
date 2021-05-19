import { of } from 'rxjs';
import { catchError, concatMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SchoolService } from '../../common';

@Injectable({
  providedIn: 'root'
})
export class GroupsService {

  constructor(private http: HttpClient, private schoolService: SchoolService) { }

  getGroups() {
    return this.schoolService.school$.pipe(
      concatMap((school: any) => this.http.get(`http://localhost:8080/schedule/group?schoolId=${school?.id}`))
    );
  }

  createGroup(value: any) {
    return this.schoolService.school$.pipe(
      concatMap((school: any) => this.http.post(`http://localhost:8080/schedule/group?schoolId=${school?.id}`, { ...value, schoolId: school.id }))
    );
  }

  deleteGroup(id: string) {
    return this.http.post(`http://localhost:8080/schedule/group/${id}/delete`, {}).pipe(
      catchError(resp => of(resp))
    );
  }
}
