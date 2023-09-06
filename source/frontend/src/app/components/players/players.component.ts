import { Component, Input, ViewChild} from '@angular/core';
import { Player } from 'src/app/models/player.model';
import { PlayersService } from 'src/app/services/players.service';
import Swal from 'sweetalert2';
import { NotLoadedComponent } from '../not-loaded/not-loaded.component';

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.scss'],
})
export class PlayersComponent {
  @Input() loaderErrorMsg: string = "";
  @Input() errorType: string = "";
  @ViewChild(NotLoadedComponent) notLoadedComponent: NotLoadedComponent = new NotLoadedComponent;
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

  constructor(private playerService: PlayersService) { }

  ngOnInit() {
    this.reloadPlayersData();
    this.categories = [
      'BEN MAS',
      'BEN FEM',
      'ALE MAS',
      'ALE FEM',
      'INF MAS',
      'INF FEM',
      'CAD MAS',
      'CAD FEM',
      'JUV MAS',
      'JUV FEM',
      'JUN MAS',
      'JUN FEM',
      'SEN MAS',
      'SEN FEM',
    ];
  }

  savePlayer() {
    let btnSave = document.getElementById('btn-new-player');
    let btnCancel = document.getElementById('btn-cancel-new-player');
    this.playerService.savePlayer(this.newPlayer).subscribe({
      next: () => {
        btnSave?.children[0].classList.toggle('hiddenplus');
        btnSave?.children[1].classList.toggle('hiddenplus');
        btnSave?.children[2].classList.toggle('hiddenplus');
        btnSave?.children[3].classList.toggle('hiddenplus');
        btnSave?.classList.toggle('bg-green-600');
        btnCancel?.classList.toggle('disabled');
        if (btnSave) {
          btnSave.children[0].innerHTML = 'Guardando...';
        }

        setTimeout(() => {
          Swal.fire({
            title: `¡Nuevo jugador añadido!`,
            toast: true,
            position: 'top-end',
            width: 'max-content',
            icon: 'success',
            confirmButtonColor: '#34285a',
            showConfirmButton: false,
            timer: 2000,
          });

          this.dismissSaveModal();

          btnSave?.children[0].classList.toggle('hiddenplus');
          btnSave?.children[1].classList.toggle('hiddenplus');
          btnSave?.children[2].classList.toggle('hiddenplus');
          btnSave?.children[3].classList.toggle('hiddenplus');
          btnSave?.classList.toggle('bg-green-600');
          btnCancel?.classList.toggle('disabled');
          if (btnSave) {
            btnSave.children[0].innerHTML = 'Guardar';
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
        }, 2000);
      },
      error: () => {
        Swal.fire({
          title: 'Error al guardar',
          icon: 'error',
          text: 'Se ha producido un error al guardar los cambios. Revise que la información introducida es correcta',
          showConfirmButton: true,
          confirmButtonColor: '#34285a',
          allowOutsideClick: false,
          color: '#34285a',
          iconColor: '#dc2626',
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
    let btnUpdate = document.getElementById('btn-update-player');
    let btnCancel = document.getElementById('btn-cancel-update-player');
    let btnDelete = document.getElementById('btn-delete-player');
    this.playerService.updatePlayer(this.updatedPlayer).subscribe({
      next: () => {
        btnUpdate?.children[0].classList.toggle('hiddenplus');
        btnUpdate?.children[1].classList.toggle('hiddenplus');
        btnUpdate?.children[2].classList.toggle('hiddenplus');
        btnUpdate?.children[3].classList.toggle('hiddenplus');
        btnCancel?.classList.toggle('hidden');
        btnUpdate?.classList.toggle('bg-green-600');
        btnDelete?.classList.toggle('disabled');

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

          btnUpdate?.children[0].classList.toggle('hiddenplus');
          btnUpdate?.children[1].classList.toggle('hiddenplus');
          btnUpdate?.children[2].classList.toggle('hiddenplus');
          btnUpdate?.children[3].classList.toggle('hiddenplus');
          btnCancel?.classList.toggle('hidden');
          btnUpdate?.classList.toggle('bg-green-600');
          btnDelete?.classList.toggle('disabled');

          this.reloadPlayersData();

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
        }, 2000);
      },
      error: () => {
        Swal.fire({
          title: 'Error al guardar',
          icon: 'error',
          text: 'Se ha producido un error al guardar los cambios. Revise que la información introducida es correcta',
          showConfirmButton: true,
          confirmButtonColor: '#34285a',
          allowOutsideClick: false,
          color: '#34285a',
          iconColor: '#dc2626',
        });
      },
    });
  }

  orderPlayersBy(order: string) {
    this.playerService
      .getPlayersOrderBy(order)
      .subscribe((players: Player[]) => {
        this.players = players;
      });
  }

  private reloadPlayersData() {
    let wrapper = document.getElementById("players-wrapper");
    let notLoadedWrapper = document.getElementsByTagName("app-not-loaded")[0];
    this.playerService.getPlayers().subscribe({
      next: (players: Player[]) => {
        if (players.length < 1) {
          this.loaderErrorMsg = "Parece que no existen jugadores aún. ¡Es hora de formar un equipo!";
          wrapper?.classList.add("hidden");
          notLoadedWrapper?.classList.remove("hidden");
          document.getElementById("btn-reload")?.classList.add("hidden");
          document.getElementById("btn-show_modal")?.classList.remove("hidden")
        } else {
          this.players = players;
          wrapper?.classList.remove("hidden");
          notLoadedWrapper?.classList.add("hidden");
        }
      },
      error: () => {
        this.loaderErrorMsg = "Parece que no se han podido cargar los datos, recarga la página y si no se soluciona contacte con un técnico.";
        wrapper?.classList.add("hidden");
        notLoadedWrapper?.classList.remove("hidden");
        document.getElementById("btn-reload")?.classList.remove("hidden");
        document.getElementById("btn-show_modal")?.classList.add("hidden");
      }
    });
  }

  searchPlayers() {
    let searchInput = <HTMLInputElement>document.getElementById('search-input');
    let aux = searchInput.value;
    if (aux.length < 1) {
      aux = "empty";
    }
    this.playerService
      .searchBy(aux)
      .subscribe((players: Player[]) => {
        if (players.length != 0) {
          this.players = players;
        } else {
          Swal.fire({
            title: 'No se han encontrado resultados',
            icon: 'error',
            toast: true,
            position: 'top-end',
            width: 'max-content',
            background: '#dc2626',
            timerProgressBar: true,
            color: 'white',
            iconColor: 'white',
            timer: 1500,
            showConfirmButton: false,
          });
        }
      });
  }

  resetSearch() {
    let searchInput = <HTMLInputElement>document.getElementById('search-input');
    if (searchInput) {
      searchInput.value = '';
    }
    this.reloadPlayersData();
  }

  deletePlayer() {
    let btnUpdate = document.getElementById('btn-update-player');
    let btnCancel = document.getElementById('btn-cancel-update-player');
    let btnDelete = document.getElementById('btn-delete-player');
    this.playerService.deletePlayer(this.updatedPlayer.id).subscribe({
      next: () => {
        btnDelete?.children[0].classList.toggle('hiddenplus');
        btnDelete?.children[1].classList.toggle('hiddenplus');
        btnDelete?.children[2].classList.toggle('hiddenplus');
        btnDelete?.children[3].classList.toggle('hiddenplus');
        btnCancel?.classList.toggle('hidden');
        btnUpdate?.classList.toggle('disabled');

        setTimeout(() => {
          Swal.fire({
            title: `¡Se eliminó el jugador!`,
            toast: true,
            position: 'top-end',
            width: 'max-content',
            icon: 'success',
            showConfirmButton: false,
            timer: 2000,
          });

          this.dismissUpdateModal();

          btnDelete?.children[0].classList.toggle('hiddenplus');
          btnDelete?.children[1].classList.toggle('hiddenplus');
          btnDelete?.children[2].classList.toggle('hiddenplus');
          btnDelete?.children[3].classList.toggle('hiddenplus');
          btnCancel?.classList.toggle('hidden');
          btnUpdate?.classList.toggle('disabled');

          this.reloadPlayersData();

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
        }, 2000);
      },
      error: () => {
        Swal.fire({
          title: 'Error al eliminar',
          icon: 'error',
          text: 'Se ha producido un error al eliminar el jugador. Parece que el jugador ya no existe, pruebe a recargar la página. Consulte con el desarrollador si el problema persiste.',
          showConfirmButton: true,
          confirmButtonColor: '#34285a',
          allowOutsideClick: false,
          color: '#34285a',
          iconColor: '#dc2626',
        });
      },
    });
  }

  deleteAll() {
    Swal.fire({
      icon: "warning",
      showConfirmButton: true,
      showCancelButton: true,
      confirmButtonColor: "#dc2626",
      focusConfirm: false,
      focusCancel: true,
      confirmButtonText: "Confirmar",
      title: "¿Estás seguro/a de borrar todos los jugadores?"
    }).then((result) => {
      if(result.isConfirmed) {
        this.playerService.deleteAll().subscribe({
          next: () => {
            Swal.fire({
              icon: "success",
              title: "¡Se han borrado correctamente!",
              timer: 1500,
              toast: true,
              position: "top-end",
              showConfirmButton: false,
              timerProgressBar: true,
            })
            setTimeout(() => {
              this.reloadPlayersData();
            }, 1500);
          },
          error: () => {
            Swal.fire({
              icon: "error",
              title: "Error al borrar todos los datos",
              text: "Se ha producido un error y no se ha podidon borrar todos los datos de los jugadores...",
              showConfirmButton: true,
              confirmButtonColor: "#34285a",
              confirmButtonText: "Entendido",
            })
          }
        })
      }
    })
  }
}
