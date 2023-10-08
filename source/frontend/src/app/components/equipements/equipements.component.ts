import { Component } from '@angular/core';
import { Equipement } from 'src/app/models/equipement.model';
import { EquipementService } from 'src/app/services/equipement.service';
import { PlayersService } from 'src/app/services/players.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-equipements',
  templateUrl: './equipements.component.html',
  styleUrls: ['./equipements.component.scss'],
})
export class EquipementsComponent {
  equipements: Equipement[] = [];
  newEquipement: Equipement = new Equipement();
  updatedEquipement: Equipement = new Equipement();
  equipementDetails: Equipement = new Equipement();
  loaderErrorMsg: string = '';
  equipement_types: string[] = [];
  equipement_uses: string[] = [];
  equipement_sizes: string[] = [];

  constructor(
    private equipementService: EquipementService,
    private playerService: PlayersService
  ) {}

  ngOnInit() {
    this.reloadEquipementsData();

    this.equipement_sizes = [
      '10 años',
      '12 años',
      '14 años',
      '16 años',
      'XS',
      'S',
      'M',
      'L',
      'XL',
      'XXL',
      'XXXL',
    ];

    this.equipement_types = [
      'Camiseta',
      'Chaqueta',
      'Pantalón chandal',
      'Pantalón corto',
      'Malla', 
    ];

    this.equipement_uses = [
      'Competición',
      'Entrenamiento',
      'Calentamiento',
    ];
  }

  transformDisableHideBtns(
    transformBtn: any,
    hideBtn: any,
    disabledBtn: any = null,
    transformBtnColor: string
  ) {
    transformBtn?.classList.toggle('pointer-events-none');
    transformBtn?.children[0].classList.toggle('hiddenplus');
    transformBtn?.children[1].classList.toggle('hiddenplus');
    transformBtn?.children[2].classList.toggle('hiddenplus');
    transformBtn?.children[3].classList.toggle('hiddenplus');
    transformBtn?.classList.toggle(transformBtnColor);
    hideBtn?.classList.toggle('hidden');
    if (disabledBtn != null) {
      disabledBtn?.classList.toggle('disabled');
    }
  }

  toggleSaveModal() {
    document.getElementById('new-equipement-modal')?.classList.toggle('hidden');
  }

  saveNewEquipement() {
    let btnSave = document.getElementById('btn-save-equipement');
    let btnCancel = document.getElementById('btn-cancel-save-equipement');
    btnSave?.classList.toggle('pointer-events-none');
    this.transformDisableHideBtns(btnSave, btnCancel, null, 'bg-green-600');

    this.equipementService.saveEquipement(this.newEquipement).subscribe({
      next: () => {
        Swal.fire({
          title: `¡Nueva equipación añadida!`,
          toast: true,
          position: 'top-end',
          width: 'max-content',
          icon: 'success',
          confirmButtonColor: '#34285a',
          showConfirmButton: false,
          timer: 2000,
        });

        this.toggleSaveModal();

        this.transformDisableHideBtns(btnSave, btnCancel, null, 'bg-green-600');
        btnSave?.classList.toggle('pointer-events-none');

        this.newEquipement = new Equipement();
        this.reloadEquipementsData();
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

  searchEquipements() {
    let searchInput = <HTMLInputElement>document.getElementById('search-input');
    let aux = searchInput.value;

    if (aux.length < 1) {
      aux = 'empty';
    }

    this.equipementService
      .searchBy(aux)
      .subscribe((equipements: Equipement[]) => {
        if (equipements.length != 0) {
          this.equipements = equipements;
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
    this.reloadEquipementsData();
  }

  deleteAll() {}

  private reloadEquipementsData() {
    let wrapper = document.getElementById('equipement-wrapper');
    let spinner = document.getElementById('equipement-spinner');
    let notLoadedWrapper = document.getElementsByTagName('app-not-loaded')[0];
    this.equipementService.getEquipements().subscribe({
      next: (equipements: Equipement[]) => {
        if (equipements.length < 1) {
          this.loaderErrorMsg =
            'Parece que no existen pagos aún. ¡Es hora de formalizar un par de ellos!';
          wrapper?.classList.add('hidden');
          notLoadedWrapper?.classList.remove('hidden');
          spinner?.classList.add('hidden');
          document.getElementById('btn-reload')?.classList.add('hidden');
          document
            .getElementById('btn-show_equipement_modal')
            ?.classList.remove('hidden');
        } else {
          this.equipements = equipements;
          spinner?.classList.add('hidden');
          wrapper?.classList.remove('hidden');
          notLoadedWrapper?.classList.add('hidden');
        }
      },
      error: () => {
        this.loaderErrorMsg =
          'Parece que no se han podido cargar los datos, recarga la página y si no se soluciona contacte con un técnico.';
        wrapper?.classList.add('hidden');
        notLoadedWrapper?.classList.remove('hidden');
        spinner?.classList.add('hidden');
        document.getElementById('btn-reload')?.classList.remove('hidden');
        document
          .getElementById('btn-show_equipement_modal')
          ?.classList.add('hidden');
      },
    });
  }
}
