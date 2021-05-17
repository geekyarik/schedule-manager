import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ShellComponent } from './shell.component';

import { HeaderModule, MaterialModule } from '../../common';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    MaterialModule,
    HeaderModule
  ],
  declarations: [ShellComponent],
  exports: [ShellComponent]
})
export class ShellModule { }
