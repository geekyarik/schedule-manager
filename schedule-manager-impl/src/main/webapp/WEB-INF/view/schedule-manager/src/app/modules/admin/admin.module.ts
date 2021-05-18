import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { AdminComponent } from './admin.component';

import { MaterialModule } from '../../common';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([{
      path: '',
      pathMatch: 'full',
      component: AdminComponent
    }]),
    MaterialModule,
    ReactiveFormsModule
  ],
  declarations: [AdminComponent]
})
export class AdminModule { }
