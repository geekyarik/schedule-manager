import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../common'
import { RegisterUserModule } from './register-user/register-user.module';
import { LoginComponent } from './login.component';

@NgModule({
  imports: [
    RouterModule.forChild([{
      path: '',
      pathMatch: 'full',
      component: LoginComponent
    }]),
    MaterialModule,
    ReactiveFormsModule,
    RegisterUserModule
  ],
  declarations: [LoginComponent],
  exports: [LoginComponent]
})
export class LoginModule {}