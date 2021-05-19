import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { SubjectsService } from './subjects.service';

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.scss']
})
export class SubjectsComponent implements OnInit {
  form = this.fb.group({
    name: ['', Validators.required]
  });
  subjects: any = [];
  editSubject: any = null;

  constructor(private service: SubjectsService, private fb: FormBuilder) { }

  ngOnInit() {
    this.getSubjects();
  }

  edit(subject: any) {
    this.editSubject = subject;
    this.form.patchValue({
      name: subject.name
    });
  }

  saveSubject() {
    const body = {
      ...this.form.value,
      ...(this.editSubject?.id ? { id: this.editSubject?.id } : {})
    };

    this.service.createSubject(body).subscribe(() => {
      this.editSubject = null;
      this.form.reset();
      this.getSubjects();
    });
  }

  private getSubjects() {
    this.service.getSubjects().subscribe(subjects => this.subjects = subjects);
  }

}
