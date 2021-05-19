import { reject } from 'lodash/fp';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { GroupsService } from './groups.service';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.scss']
})
export class GroupsComponent implements OnInit {
  form = this.fb.group({
    name: ['', Validators.required]
  });
  groups: any = [];

  constructor(private service: GroupsService, private fb: FormBuilder) { }

  ngOnInit() {
    this.getGroups();
  }

  createGroup() {
    this.service.createGroup(this.form.value)
    .subscribe(group => {
      this.form.reset();
      this.groups = [...this.groups, group];
    });
  }

  deleteGroup(id: string) {
    this.service.deleteGroup(id).subscribe(() => this.groups = reject({ id }, this.groups));
  }

  private getGroups() {
    this.service.getGroups().subscribe((groups: any) => this.groups = groups);
  }
}
