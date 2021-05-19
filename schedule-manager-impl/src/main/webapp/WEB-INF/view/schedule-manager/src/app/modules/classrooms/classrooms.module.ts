import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../common';
import { ClassroomsComponent } from './classrooms.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild([{
      path: '',
      pathMatch: 'full',
      component: ClassroomsComponent
    }]),
    MaterialModule
  ],
  declarations: [
    ClassroomsComponent
  ]
})
export class ClassroomsModule { }
