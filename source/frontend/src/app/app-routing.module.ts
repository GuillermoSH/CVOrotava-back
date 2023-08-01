import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HomeComponent } from './components/home/home.component';
import { MoneyComponent } from './components/money/money.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "notFound", component: NotFoundComponent },
  {
    path: "dashboard", component: DashboardComponent, children: [
      { path: "home", component: HomeComponent },
      { path: "money", component: MoneyComponent },
      { path: "statistics", component: StatisticsComponent }
    ]
  },
  { path: "**", redirectTo: "notFound" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
