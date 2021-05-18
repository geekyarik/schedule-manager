import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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

  constructor(private adminService: AdminService, private fb: FormBuilder) { }

  ngOnInit() {
    this.getUsers();
    this.getSchools();
  }

  setAdmin(user: any) {
    this.adminService.setAdmin(user).subscribe(() => this.getUsers());
  }

  createSchool() {
    const id = this.school.id;
    const body = {
      ...this.schoolForm.value,
      ...(id ? { id } : {})
    };

    this.adminService.createSchool(body)
      .subscribe(school => {
        this.showAddSchoolForm = false;
        this.school = school;
      });
  }

  openSchoolForm() {
    this.school && this.schoolForm.patchValue({
      name: this.school.name,
      address: this.school.address
    });
    this.showAddSchoolForm = true;
  }

  private getUsers() {
    this.adminService.getUsers().subscribe(users => this.users = users);
  }

  private getSchools() {
    this.adminService.getSchools().subscribe((schools: any) => this.school = schools[0]);
  }
}
