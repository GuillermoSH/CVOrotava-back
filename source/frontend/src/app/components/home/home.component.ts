import { Component } from '@angular/core';
import { Player } from 'src/app/models/player.model';
import { PlayersService } from 'src/app/services/players.service';
import { PaymentService } from 'src/app/services/payment.service';
import { Payment } from 'src/app/models/payment.model';
import { Chart } from 'chart.js';
import { count } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  totalNumPlayers: any = {
    TFem: 0,
    TMas: 0,
    Total: 0,
  };
  players: Player[] = [];
  payments: Payment[] = [];
  months: any = [];
  notPayers: number = 0;

  constructor(
    private playersService: PlayersService,
    private paymentsService: PaymentService
  ) { }

  ngOnInit() {
    let currentDate = new Date();
    let actMonth = currentDate.getMonth() + 1;
    let actYear = currentDate.getFullYear().toString();

    this.months = [
      'enero',
      'febrero',
      'marzo',
      'abril',
      'mayo',
      'junio',
      'julio',
      'agosto',
      'septiembre',
      'octubre',
      'noviembre',
      'diciembre',
    ];

    this.playersService.getTotalNumPlayers().subscribe((total: string[]) => {
      this.totalNumPlayers.TFem = total[0];
      this.totalNumPlayers.TMas = total[1];
      document
        .getElementById('players-count-squeleton')
        ?.classList.toggle('hidden');
      document.getElementById('players-count')?.classList.toggle('hidden');
    });

    this.playersService.getPlayers().subscribe((players: Player[]) => {
      this.players = players;
      this.totalNumPlayers.Total = players.length;
    });

    this.paymentsService.getPayments().subscribe((payments: Payment[]) => {
      this.payments = payments;

      this.createCharts(payments.filter((payment) => { return payment.month == actMonth }));
    });
  }

  createCharts(payments: Payment[]) {
    this.playersService.getPlayers().subscribe((players: Player[]) => {
      const chartContainer = document.getElementById("paymentsCharts");
      const chartCount = payments.length;

      payments.forEach((payment) => {
        const labels = [
          `${payment.concept} - ${this.months[payment.month - 1]}`.toUpperCase(),
          "MOROSOS",
        ];

        const data = [payment.players.length, players.length - payment.players.length];

        const canvas = document.createElement("canvas");

        chartContainer?.appendChild(canvas);

        new Chart(canvas, {
          type: "doughnut",
          data: {
            labels: labels,
            datasets: [
              {
                label: 'Jugadores',
                data: data,
                backgroundColor: ['#16a34a', '#052e16'],
              }
            ]
          },
          options: {
            responsive: true,
            maintainAspectRatio: true,
          }
        });

        chartContainer?.classList.add("grid-rows-" + chartCount, "md:grid-cols-" + chartCount)
      });
    });
  }
}

