import { takeWhile, filter } from 'rxjs/operators';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SchoolService } from '../../common';
import { AdminService } from './admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  users!: any;
  school: any = null;
  showAddSchoolForm = false;
  schoolForm: FormGroup = this.fb.group({
    name: ['', Validators.required],
    address: ['', Validators.required]
  });

  private alive = true;

  constructor(
    private adminService: AdminService,
    private fb: FormBuilder,
    private schoolService: SchoolService
  ) { }

  ngOnInit() {
    this.getUsers();
    this.getSchool();
  }

  toggleAdmin(user: any) {
    this.adminService.toggleAdmin(user).subscribe(() => this.getUsers());
  }

  createSchool() {
    const id = this.school?.id;
    const body = {
      ...this.schoolForm.value,
      ...(id ? { id } : {})
    };

    this.adminService.createSchool(body)
      .subscribe(school => {
        this.showAddSchoolForm = false;
        this.school = school;
        !id && this.getSchool();
      });
  }

  openSchoolForm() {
    this.school && this.schoolForm.patchValue({
      name: this.school.name,
      address: this.school.address
    });
    this.showAddSchoolForm = true;
  }

  private getSchool() {
    this.schoolService.school$.pipe(
      filter(Boolean),
      takeWhile(() => this.alive)
    )
    .subscribe(school => this.school = school);
  }

  private getUsers() {
    this.adminService.getUsers().subscribe(users => this.users = users);
  }

  ngOnDestroy() {
    this.alive = false;
  }
}
