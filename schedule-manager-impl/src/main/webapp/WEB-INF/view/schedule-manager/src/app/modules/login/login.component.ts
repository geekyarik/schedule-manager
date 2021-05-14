import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../../core/auth';
import { RegisterUserComponent } from './register-user/register-user.component';

@Component({
  selector: 'sm-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private dialog: MatDialog,
    private router: Router
  ) {}

  ngOnInit() {
    this.initForm();
  }

  openRegistrationForm() {
    this.dialog.open(RegisterUserComponent, {
      disableClose: true,
      minWidth: '25%'
    });
  }

  login() {
    this.authService.login(this.loginForm.value)
      .subscribe((response: any) => {
        console.log(response?.jwt);
        this.router.navigate(['dashboard']);
      });
  }

  private initForm() {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }
}