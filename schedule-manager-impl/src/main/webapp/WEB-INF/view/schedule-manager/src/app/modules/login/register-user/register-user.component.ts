import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../core/auth';

@Component({
  selector: 'sm-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.scss']
})
export class RegisterUserComponent implements OnInit {
  form!: FormGroup;

  constructor(
    private authService: AuthService,
    private dialogRef: MatDialogRef<RegisterUserComponent>,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {
    this.form = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
      firstName: ['', Validators.required],
      middleName: ['', Validators.required],
      lastName: ['', Validators.required],
    });
  }

  register() {
    this.authService.register(this.form.value).subscribe(response => this.close());
  }

  close() {
    this.dialogRef.close()
  }
}
