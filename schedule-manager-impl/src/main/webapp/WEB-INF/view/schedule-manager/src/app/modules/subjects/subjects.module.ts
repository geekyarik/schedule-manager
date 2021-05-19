import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../common';
import { SubjectsComponent } from './subjects.component';

@NgModule({
  declarations: [
    SubjectsComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild([{
      path: '',
      pathMatch: 'full',
      component: SubjectsComponent
    }]),
    MaterialModule
  ]
})
export class SubjectsModule { }
