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

  constructor(
    private playersService: PlayersService,
    private paymentsService: PaymentService
  ) {}

  ngOnInit() {
    this.playersService.getTotalNumPlayers().subscribe((total: string[]) => {
      this.totalNumPlayers.TFem = total[0];
      this.totalNumPlayers.TMas = total[1];
      document.getElementById('players-count-squeleton')?.classList.toggle('hidden');
      document.getElementById('players-count')?.classList.toggle('hidden');
    });

    this.playersService.getPlayers().subscribe((players: Player[]) => {
      this.players = players;
      this.totalNumPlayers.Total = players.length;
    });

    this.paymentsService.getPayments().subscribe((payments: Payment[]) => {
      this.payments = payments;
    });
  }
}
