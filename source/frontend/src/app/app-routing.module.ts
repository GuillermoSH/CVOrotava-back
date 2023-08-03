import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HomeComponent } from './components/home/home.component';
import { MoneyComponent } from './components/money/money.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

const routes: Routes = [
  { path: "login", component: LoginComponent, data: {title: "Log In"} },
  { path: "error...", component: NotFoundComponent, data: {title: "Vaya..."} },
  {
    path: "dashboard", component: DashboardComponent, children: [
      { path: "", component: HomeComponent, data: {title: "CVOrotava - Inicio"} },
      { path: "money", component: MoneyComponent, data: {title: "CVOrotava - Registros"} },
      { path: "statistics", component: StatisticsComponent, data: {title: "CVOrotava - Estadisticas"} }
    ]
  },
  { path: "**", redirectTo: "error..." }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
