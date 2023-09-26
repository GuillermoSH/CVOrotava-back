import { Component } from '@angular/core';
import { Player } from 'src/app/models/player.model';
import { PlayersService } from 'src/app/services/players.service';
import { PaymentService } from 'src/app/services/payment.service';
import { Payment } from 'src/app/models/payment.model';

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
  paymentsData: any;
  months: any = [];
  options: any;

  constructor(
    private playersService: PlayersService,
    private paymentsService: PaymentService
  ) {}

  ngOnInit() {
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

    this.options = {
      responsive: false,
      maintainAspectRatio: false
    };

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
      let labels: any = [];
      let data: any = [];
      payments.forEach((payment)=> {
          labels.push((payment.concept + " - " + this.months[payment.month - 1]).toUpperCase())
          data.push(payment.players.length)
      })
      this.paymentsData = {
        labels: labels,
        datasets: [
          {
            label: 'Jugadores que han pagado por mes',
            data: data,
          },
        ],
      };
    });
  }
}
