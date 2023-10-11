import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ChartModule } from 'primeng/chart';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BottombarComponent } from './components/bottombar/bottombar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { EquipementsComponent } from './components/equipements/equipements.component';
import { HomeComponent } from './components/home/home.component';
import { IndexComponent } from './components/index/index.component';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { NotLoadedComponent } from './components/not-loaded/not-loaded.component';
import { PaymentComponent } from './components/payment/payment.component';
import { PaymentsDetailsComponent } from './components/payments-details/payments-details.component';
import { PlayersComponent } from './components/players/players.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SettingsComponent } from './components/settings/settings.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { TopbarComponent } from './components/topbar/topbar.component';
import { DateFormatPipe } from './pipes/date-format.pipe';
import { EquipementsDetailsComponent } from './components/equipements-details/equipements-details.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    TopbarComponent,
    BottombarComponent,
    MainComponent,
    LoginComponent,
    StatisticsComponent,
    HomeComponent,
    NotFoundComponent,
    DashboardComponent,
    IndexComponent,
    ProfileComponent,
    PlayersComponent,
    DateFormatPipe,
    NotLoadedComponent,
    PaymentComponent,
    PaymentsDetailsComponent,
    SettingsComponent,
    EquipementsComponent,
    EquipementsDetailsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ChartModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
