import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../common';
import { TeachersComponent } from './teachers.component';

@NgModule({
  declarations: [
    TeachersComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild([{
      path: '',
      pathMatch: 'full',
      component: TeachersComponent
    }]),
    MaterialModule
  ]
})
export class TeachersModule { }
