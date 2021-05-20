import { takeWhile, map, tap, concatMap, finalize, take } from 'rxjs/operators';
import { last, differenceBy, reject } from 'lodash';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TeachersService } from '../teachers.service';

@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.scss']
})
export class TeacherComponent implements OnInit, OnDestroy {
  teacherId!: string;
  subjects$ = this.service.getSubjects();
  subjects: any = [];
  allSubjects: any = [];
  loading: any = {};

  private alive = true;

  constructor(private route: ActivatedRoute, private service: TeachersService) { }

  ngOnInit() {
    this.route.url.pipe(
      takeWhile(() => this.alive),
      map(last),
      map((segment: any) => segment.path),
      tap(id => this.teacherId = id),
      concatMap(id => this.service.getTeachersSubjects(id))
    )
    .subscribe(subjects => {
      this.subjects = subjects;
      this.setAllSubjects();
    });
  }

  remove(subjectId: string) {
    this.loading[subjectId] = true;
    this.service.dropSubject({ teacherId: this.teacherId, subjectId })
    .pipe(
      finalize(() => this.loading[subjectId] = false)
    )
    .subscribe(() => {
      this.subjects = reject(this.subjects, { id: subjectId });
      this.setAllSubjects();
    });
  }

  add(subject: any) {
    this.loading[subject.id] = true;
    this.service.addSubject({ teacherId: this.teacherId, subjectId: subject.id })
    .pipe(
      finalize(() => this.loading[subject.id] = false)
    )
    .subscribe(() => {
      this.allSubjects = reject(this.allSubjects, { id: subject.id });
      this.subjects = [...this.subjects, subject];
    });
  }

  private setAllSubjects() {
    this.subjects$.pipe(
      take(1),
      map((subjects: any) => differenceBy(subjects, this.subjects, (s: any) => s.id))
    )
    .subscribe(subjects => this.allSubjects = subjects)
  }

  ngOnDestroy() {
    this.alive = false;
  }

}
