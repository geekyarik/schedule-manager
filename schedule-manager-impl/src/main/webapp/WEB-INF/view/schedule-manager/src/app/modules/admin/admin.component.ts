import { Component, OnInit } from '@angular/core';
import { AdminService } from './admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  users!: any;
  schools$ = this.adminService.getSchools();

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getUsers();
  }

  setAdmin(user: any) {
    this.adminService.setAdmin(user).subscribe(() => this.getUsers());
  }

  private getUsers() {
    this.adminService.getUsers().subscribe(users => this.users = users);
  }
}
