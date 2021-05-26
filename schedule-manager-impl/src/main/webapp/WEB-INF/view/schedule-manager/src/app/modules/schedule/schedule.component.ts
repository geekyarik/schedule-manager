import { of } from 'rxjs';
import { concatMap, filter } from 'rxjs/operators';
import { range, reduce, forEach, without } from 'lodash/fp';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../../core/auth';
import { ScheduleService } from './schedule.service';
import { EditLessonComponent } from './edit-lesson/edit-lesson.component';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss']
})
export class ScheduleComponent implements OnInit {
  groups$ = this.service.getGroups();
  rooms$ = this.service.getRooms();
  teachers$ = this.service.getTeachers();
  week: any = [];
  lessons = range(1, 7);
  controls = ['group', 'classroom', 'teacher'];
  form = this.fb.group(reduce((acc, v) => ({...acc, [v]: '' }), {}, this.controls));
  storageKey = 'scheduleFilter';
  isAuthenticated$ = this.authService.isAuthenticated$;

  constructor(
    private service: ScheduleService,
    private fb: FormBuilder,
    private authService: AuthService,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.restoreSchedule();
  }

  onOptionSelected(event: any, unit: string) {
    const id = event.option.value.id;

    forEach((c: any) => {
      const control = this.form.get(c);
      control?.reset();
      control?.markAsPristine();
    }, without([unit], this.controls));

    localStorage.setItem(this.storageKey, JSON.stringify({ id, unit }));

    this.getSchedule(id, unit);
  }

  editLesson(day: any, ordinalNumber: number) {
    if (day && this.isAuthenticated$.value) {
      this.dialog
        .open(EditLessonComponent, {
          data: { day, ordinalNumber }
        })
        .afterClosed()
        .pipe(
          filter(Boolean),
          concatMap(data => this.service.saveLesson(day, ordinalNumber, data))
        )
        .subscribe(() => this.restoreSchedule());
    }
  }

  remove(id: string) {
    this.service.removeLesson(id).subscribe(() => this.restoreSchedule());
  }

  displayFn(option: any): string {
    return option?.name || '';
  }

  private restoreSchedule() {
    const { id, unit } = JSON.parse(localStorage.getItem(this.storageKey) || '{}');

    id && this.getSchedule(id, unit);
  }

  private getSchedule(id: string, unit: string) {
    this.service.getLessons(id, unit).subscribe(value => this.week = value);
  }
}
