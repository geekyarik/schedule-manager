import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder } from '@angular/forms';
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

  form = this.fb.group({
    group: '',
    classroom: '',
    teacher: ''
  });
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

    this.groups$ = this.service.getAvailGroups(day.day, ordinalNumber);
    this.rooms$ = this.service.getAvailRooms(day.day, ordinalNumber);
    this.teachers$ = this.service.getAvailTeachers(day.day, ordinalNumber, day.value[ordinalNumber]?.[0]?.subject?.id);
  }

  onOptionSelected(event: any, unit: string) {}

  displayFn(option: any): string {
    return option?.name || '';
  }

}
