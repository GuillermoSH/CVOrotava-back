import { Component } from '@angular/core';
import { Player } from 'src/app/models/player.model';
import { PlayersService } from 'src/app/services/players.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.scss'],
})
export class PlayersComponent {
  players: Player[] = [];
  updatedPlayer: Player = {
    id: 0,
    dni: '',
    name: '',
    surname1: '',
    surname2: '',
    telephone: 0,
    email: '',
    address: '',
    birthday: '',
    category: '',
  };
  newPlayer: Player = {
    id: 0,
    dni: '',
    name: '',
    surname1: '',
    surname2: '',
    telephone: 0,
    email: '',
    address: '',
    birthday: '',
    category: '',
  };

  constructor(private playerService: PlayersService) {}

  ngOnInit() {
    this.reloadPlayersData();
  }

  saveUpdatePlayer(player: any = this.newPlayer) {
    this.playerService.saveUpdatePlayer(player).subscribe({
      next: () => {
        Swal.fire({
          title: `¡Datos de ${player.name + ' ' + player.surname1 + ' ' + player.surname2} han sido guardados correctamente!`,
          icon: 'success',
          showConfirmButton: false,
          timer: 1500,
        });
        this.reloadPlayersData();
      },
      error: (error) => {
        Swal.fire({
          title: error.error.message,
          icon: 'error',
          showConfirmButton: false,
          timer: 1500,
        });
      },
    });
  }

  showUpdateModal(player: Player) {
    this.updatedPlayer = player;
    document.getElementById('updateModal')?.classList.toggle('hidden');
  }

  dismissModal() {
    document.getElementById('updateModal')?.classList.toggle('hidden');
    this.reloadPlayersData();
  }

  updatePlayer() {
    this.playerService.saveUpdatePlayer(this.updatedPlayer).subscribe({
      next: () => {
        Swal.fire({
          title: `¡Los datos han sido actualizados!`,
          toast: true,
          position: 'top-end',
          width: 'max-content',
          icon: 'success',
          showConfirmButton: false,
          timer: 1500,
        });
        this.reloadPlayersData();
        setTimeout(() => {
          this.dismissModal();

          this.updatedPlayer = {
            id: 0,
            dni: '',
            name: '',
            surname1: '',
            surname2: '',
            telephone: 0,
            email: '',
            address: '',
            birthday: '',
            category: '',
          };
        }, 1500)
      },
      error: (error) => {
        Swal.fire({
          title: error.error.message,
          icon: 'error',
          showConfirmButton: false,
          timer: 1500,
        });
      },
    });

  }

  orderPlayersBy(order: string) {
    this.playerService.getPlayersOrderBy(order).subscribe((players: Player[]) => {
      this.players = players;
    })
  }

  private reloadPlayersData() {
    this.playerService.getPlayers().subscribe((players: Player[]) => {
      this.players = players;
    });
  }
}
