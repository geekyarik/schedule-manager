import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../common';
import { GroupsComponent } from './groups.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild([{
      path: '',
      pathMatch: 'full',
      component: GroupsComponent
    }]),
    MaterialModule
  ],
  declarations: [
    GroupsComponent
  ],
})
export class GroupsModule { }
