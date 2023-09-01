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
  categories: string[] = [];
  updatedPlayer: Player = {
    id: 0,
    dni: '',
    name: '',
    surname1: '',
    surname2: '',
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
    email: '',
    address: '',
    birthday: '',
    category: '',
  };

  constructor(private playerService: PlayersService) {}

  ngOnInit() {
    this.reloadPlayersData();
    this.categories = [
      "BEN MAS",
      "BEN FEM",
      "ALE MAS",
      "ALE FEM",
      "INF MAS",
      "INF FEM",
      "CAD MAS",
      "CAD FEM",
      "JUV MAS",
      "JUV FEM",
      "JUN MAS",
      "JUN FEM",
      "SEN MAS",
      "SEN FEM",
    ]
  }

  savePlayer() {
    let btnSave = document.getElementById("btn-new-player");
    let btnCancel = document.getElementById("btn-cancel-new-player");
    this.playerService.savePlayer(this.newPlayer).subscribe({
      next: () => {
        btnSave?.children[1].classList.toggle("fa-save");
        btnSave?.children[1].classList.toggle("fa-spinner");
        btnSave?.children[1].classList.toggle("animate-spin");
        btnSave?.classList.toggle("bg-green-600");
        btnCancel?.classList.toggle("disabled");
        if (btnSave) {
          btnSave.children[0].innerHTML = "Guardando...";
        }
        
        setTimeout(() => {
          Swal.fire({
            title: `¡Nuevo jugador añadido!`,
            toast: true,
            position: 'top-end',
            width: 'max-content',
            icon: 'success',
            showConfirmButton: false,
            timer: 2000,
          });
          
          this.dismissSaveModal();
          
          btnSave?.children[1].classList.toggle("fa-save");
          btnSave?.children[1].classList.toggle("fa-spinner");
          btnSave?.children[1].classList.toggle("animate-spin");
          btnSave?.classList.toggle("bg-green-600");
          btnCancel?.classList.toggle("disabled");
          if(btnSave) {
            btnSave.children[0].innerHTML = "Guardar cambios";
          }
          
          this.newPlayer = {
            id: 0,
            dni: '',
            name: '',
            surname1: '',
            surname2: '',
            email: '',
            address: '',
            birthday: '',
            category: '',
          };
          this.reloadPlayersData();
        }, 2000)
      },
      error: () => {
        Swal.fire({
          title: 'Error al guardar',
          icon: 'error',
          text: 'Se ha producido un error al guardar los cambios. Revise que la información introducida es correcta',
          showConfirmButton: true,
          confirmButtonColor: "#3b82f6",
          allowOutsideClick: false,
          color: "#34285a",
          iconColor: "#dc2626",
        });
      },
    });
  }

  showUpdateModal(player: Player) {
    this.updatedPlayer = player;
    document.getElementById('update-modal')?.classList.toggle('hidden');
  }

  showSaveModal() {
    document.getElementById('new-player-modal')?.classList.toggle('hidden');
  }

  dismissSaveModal() {
    document.getElementById('new-player-modal')?.classList.toggle('hidden');
    this.newPlayer = {
      id: 0,
      dni: '',
      name: '',
      surname1: '',
      surname2: '',
      email: '',
      address: '',
      birthday: '',
      category: '',
    };
  }

  dismissUpdateModal() {
    document.getElementById('update-modal')?.classList.toggle('hidden');
    this.updatedPlayer = {
      id: 0,
      dni: '',
      name: '',
      surname1: '',
      surname2: '',
      email: '',
      address: '',
      birthday: '',
      category: '',
    };
  }

  updatePlayer() {
    let btnUpdate = document.getElementById("btn-update-player");
    let btnCancel = document.getElementById("btn-cancel-update-player");
    console.log(document.getElementById("btn-update-player"), document.getElementById("btn-update-player")?.children[0])
    this.playerService.updatePlayer(this.updatedPlayer).subscribe({
      next: () => {
        btnUpdate?.children[1].classList.toggle("fa-save");
        btnUpdate?.children[1].classList.toggle("fa-spinner");
        btnUpdate?.children[1].classList.toggle("animate-spin");
        btnUpdate?.classList.toggle("bg-green-600");
        btnCancel?.classList.toggle("disabled");
        if (btnUpdate) {
          btnUpdate.children[0].innerHTML = "Guardando...";
        }
        
        this.reloadPlayersData();
        setTimeout(() => {
          Swal.fire({
            title: `¡Los datos han sido actualizados!`,
            toast: true,
            position: 'top-end',
            width: 'max-content',
            icon: 'success',
            showConfirmButton: false,
            timer: 2000,
          });
          
          this.dismissUpdateModal();
          
          btnUpdate?.children[1].classList.toggle("fa-save");
          btnUpdate?.children[1].classList.toggle("fa-spinner");
          btnUpdate?.children[1].classList.toggle("animate-spin");
          btnUpdate?.classList.toggle("bg-green-600");
          btnCancel?.classList.toggle("disabled");
          if(btnUpdate) {
            btnUpdate.children[0].innerHTML = "Guardar cambios";
          }

          this.updatedPlayer = {
            id: 0,
            dni: '',
            name: '',
            surname1: '',
            surname2: '',
            email: '',
            address: '',
            birthday: '',
            category: '',
          };
        }, 2000)
      },
      error: () => {
        Swal.fire({
          title: 'Error al guardar',
          icon: 'error',
          text: 'Se ha producido un error al guardar los cambios. Revise que la información introducida es correcta',
          showConfirmButton: true,
          confirmButtonColor: "#3b82f6",
          allowOutsideClick: false,
          color: "#34285a",
          iconColor: "#dc2626",
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

  searchPlayers() {
    let search = <HTMLInputElement>document.getElementById("search-input");
    this.playerService.searchBy(search?.value).subscribe((players: Player[]) => {
      if (players.length!=0) {
        this.players = players;
      } else {
        Swal.fire({
          title: "No se han encontrado resultados",
          icon: "error",
          toast: true,
          position: "top-end",
          width: "max-content",
          background: "#dc2626",
          timerProgressBar: true,
          color: "white",
          iconColor: "white",
          timer: 1500,
          showConfirmButton: false,
        })
      }
    })
  }
}
