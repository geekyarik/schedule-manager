import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { of } from 'rxjs';
import { ScheduleGenerationService } from './schedule-generation.service';

@Component({
  selector: 'app-schedule-generation',
  templateUrl: './schedule-generation.component.html',
  styleUrls: ['./schedule-generation.component.scss']
})
export class ScheduleGenerationComponent implements OnInit {
  form = this.fb.group({
    name: ['', Validators.required],
    timesPerWeek: ['', Validators.required],
    group: ['', Validators.required],
    subject: ['', Validators.required],
    preferredRoom: '',
    preferredTeacher: ''
  });

  groups$ = this.service.getGroups();
  subject$ = this.service.getSubjects();
  rooms$ = this.service.getRooms();
  teachers$ = this.service.getTeachers();

  rules: any = [];

  constructor(private service: ScheduleGenerationService, private fb: FormBuilder) { }

  ngOnInit() {
    this.getRules();
  }

  saveRule() {
    this.service.saveRule(this.form.value).subscribe(() => {
      this.form.reset();
      this.getRules();
    });
  }

  remove(rule: any) {
    this.service.removeRule(rule.id).subscribe(() => this.getRules());
  }

  generate() {
      this.service.generate().subscribe();
  }

  displayFn(option: any): string {
    return option?.name || '';
  }

  private getRules() {
    return this.service.getRules().subscribe(rules => this.rules = rules);
  }

}
