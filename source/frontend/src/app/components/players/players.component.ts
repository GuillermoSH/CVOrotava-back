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
    document.getElementById('updateModal')?.classList.toggle('hidden');
    this.updatedPlayer = player;
  }

  updatePlayer() {
    this.saveUpdatePlayer(this.updatedPlayer);

    this.updatedPlayer = {
      id: 0,
      name: '',
      surname1: '',
      surname2: '',
      telephone: 0,
      email: '',
      address: '',
      birthday: '',
      category: '',
    };

    document.getElementById('updateModal')?.classList.toggle('hidden');
  }

  private reloadPlayersData() {
    this.playerService.getPlayers().subscribe((players: Player[]) => {
      this.players = players;
    });
  }
}
