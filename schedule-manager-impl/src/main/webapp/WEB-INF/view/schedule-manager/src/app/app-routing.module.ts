import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './core/auth';
import { AdminGuard } from './modules/admin/admin.guard';
import { ShellComponent } from './modules/shell/shell.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login'
  },
  {
    path: 'login',
    loadChildren: () => import('./modules/login/login.module').then(m => m.LoginModule)
  },
  {
    path: 'shell',
    component: ShellComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'admin',
        loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule),
        canActivate: [AdminGuard]
      },
      {
        path: 'classrooms',
        loadChildren: () => import('./modules/classrooms/classrooms.module').then(m => m.ClassroomsModule),
      },
      {
        path: 'groups',
        loadChildren: () => import('./modules/groups/groups.module').then(m => m.GroupsModule),
      },
      {
        path: 'subjects',
        loadChildren: () => import('./modules/subjects/subjects.module').then(m => m.SubjectsModule),
      },
      {
        path: 'teachers',
        loadChildren: () => import('./modules/teachers/teachers.module').then(m => m.TeachersModule),
      }
    ]
  },
  {
    path: '**',
    pathMatch: 'full',
    redirectTo: 'login'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
