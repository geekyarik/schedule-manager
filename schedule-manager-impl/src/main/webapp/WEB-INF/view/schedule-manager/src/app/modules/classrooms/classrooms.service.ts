import { of } from 'rxjs';
import { catchError, concatMap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SchoolService } from '../../common';

@Injectable({
  providedIn: 'root'
})
export class ClassroomsService {

  constructor(private http: HttpClient,  private schoolService: SchoolService) { }

  getRooms() {
    return this.schoolService.school$.pipe(
      concatMap((school: any) => this.http.get(`http://localhost:8080/schedule/classroom?schoolId=${school?.id}`))
    );
  }

  createRoom(value: any) {
    return this.schoolService.school$.pipe(
      concatMap((school: any) => this.http.post(`http://localhost:8080/schedule/classroom?schoolId=${school?.id}`, { ...value, schoolId: school.id }))
    );
  }

  deleteRoom(id: string) {
    return this.http.delete(`http://localhost:8080/schedule/classroom/${id}`).pipe(
      catchError(resp => of(resp))
    );
  }
}
