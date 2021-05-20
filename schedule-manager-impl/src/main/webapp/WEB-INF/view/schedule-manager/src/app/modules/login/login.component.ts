import { catchError, filter, tap } from 'rxjs/operators';
import { of } from 'rxjs';
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
  loginFail = false;
  loginError = 'ERROR';

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
      .pipe(
        catchError(error => {
          console.error(error);
          return of(this.loginError);
        }),
        tap(response => this.loginFail = response === this.loginError),
        filter(response => response !== this.loginError)
      )
      .subscribe(() => this.router.navigate(['schedule']));
  }

  private initForm() {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }
}