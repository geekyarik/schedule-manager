import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../common';
import { TeachersComponent } from './teachers.component';
import { TeacherComponent } from './teacher/teacher.component';

@NgModule({
  declarations: [
    TeachersComponent,
    TeacherComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild([{
      path: '',
      component: TeachersComponent,
      children: [{
        path: ':id',
        component: TeacherComponent
      }]
    }]),
    MaterialModule
  ]
})
export class TeachersModule { }
