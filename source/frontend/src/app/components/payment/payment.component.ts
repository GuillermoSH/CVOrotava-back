import { Component } from '@angular/core';
import { Payment } from 'src/app/models/payment.model';
import { Player } from 'src/app/models/player.model';
import { PaymentService } from 'src/app/services/payment.service';
import { PlayersService } from 'src/app/services/players.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent {
  payments: Payment[] = [];
  players: Player[] = [];
  months: string[] = [];

  constructor(private paymentservice: PaymentService, private playerservice: PlayersService) { }

  ngOnInit() {
    this.paymentservice.getPayments().subscribe((payments: Payment[]) => { this.payments = payments })
    this.playerservice.getPlayers().subscribe((players: Player[]) => { this.players = players})
    this.months = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"];
  }
}
