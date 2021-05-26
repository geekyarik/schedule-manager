import { range } from 'lodash/fp';
import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { ScheduleService } from '../schedule.service';

@Component({
  selector: 'app-edit-lesson',
  templateUrl: './edit-lesson.component.html',
  styleUrls: ['./edit-lesson.component.scss']
})
export class EditLessonComponent implements OnInit {
  groups$!: Observable<any>;
  rooms$!: Observable<any>;
  teachers$!: Observable<any>;
  week = this.service.week;
  slots = range(1, 7);

  form!: FormGroup;
  lesson!: any;

  constructor(
    private fb: FormBuilder,
    private service: ScheduleService,
    public dialogRef: MatDialogRef<EditLessonComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit() {
    const { day, ordinalNumber } = this.data;

    this.lesson = day?.value[ordinalNumber]?.[0];

    this.initForm();

    this.groups$ = this.service.getAvailGroups(day.day, ordinalNumber);
    this.rooms$ = this.service.getAvailRooms(day.day, ordinalNumber);
    this.teachers$ = this.service.getAvailTeachers(day.day, ordinalNumber, day.value[ordinalNumber]?.[0]?.subject?.id);
  }

  save() {
    this.dialogRef.close(this.form.value);
  }

  displayFn(option: any): string {
    return option?.name || '';
  }

  displayTeacherFn(option: any): string {
    return `${option?.firstName} ${option?.lastName}` || '';
  }

  private initForm() {
    const { day, ordinalNumber } = this.data;
    const { group, classroom, teacher } = this.lesson;

    this.form = this.fb.group({
      day: day.day,
      ordinalNumber,
      group,
      classroom,
      teacher
    });
  }
}
