import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../common';
import { ScheduleComponent } from './schedule.component';
import { EditLessonComponent } from './edit-lesson/edit-lesson.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([{
      path: '',
      component: ScheduleComponent
    }]),
    MaterialModule,
    ReactiveFormsModule
  ],
  declarations: [
    ScheduleComponent,
    EditLessonComponent
  ],
})
export class ScheduleModule { }
