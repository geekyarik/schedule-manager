import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { ScheduleGenerationComponent } from './schedule-generation.component';
import { MaterialModule } from '../../common';

@NgModule({
  declarations: [ScheduleGenerationComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([{
      path: '',
      component: ScheduleGenerationComponent
    }]),
    MaterialModule,
    ReactiveFormsModule
  ]
})
export class ScheduleGenerationModule { }
