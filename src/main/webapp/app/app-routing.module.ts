import { RoleGuard } from './guards/role.guard';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AdminComponent } from './components/admin/admin.component';
import { EmployeComponent } from './components/employe/employe.component';
import { ClientComponent } from './components/client/client.component';
import { UnauthorizedComponent } from './components/unauthorized/unauthorized.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'admin', component: AdminComponent, canActivate: [RoleGuard], data: { roles: ['ROLE_ADMIN'] } },
  { path: 'employe', component: EmployeComponent, canActivate: [RoleGuard], data: { roles: ['ROLE_EMPLOYE'] } },
  { path: 'client', component: ClientComponent, canActivate: [RoleGuard], data: { roles: ['ROLE_CLIENT'] } },
  { path: 'unauthorized', component: UnauthorizedComponent },
  { path: '**', redirectTo: '/dashboard' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
