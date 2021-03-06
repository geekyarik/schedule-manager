import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './core/auth';
import { AdminGuard } from './modules/admin/admin.guard';
import { ShellComponent } from './modules/shell/shell.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'schedule'
  },
  {
    path: 'login',
    loadChildren: () => import('./modules/login/login.module').then(m => m.LoginModule)
  },
  {
    path: 'schedule',
    component: ShellComponent,
    canActivateChild: [AuthGuard],
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'board'
      },
      {
        path: 'admin',
        loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule)
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
        loadChildren: () => import('./modules/teachers/teachers.module').then(m => m.TeachersModule)
      },
      {
        path: 'schedule-generation',
        loadChildren: () => import('./modules/schedule-generation/schedule-generation.module').then(m => m.ScheduleGenerationModule)
      },
      {
        path: 'board',
        loadChildren: () => import('./modules/schedule/schedule.module').then(m => m.ScheduleModule)
      }
    ]
  },
  {
    path: '**',
    redirectTo: 'schedule'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
