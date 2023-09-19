import { Component } from '@angular/core';
import { Payment } from 'src/app/models/payment.model';
import { Player } from 'src/app/models/player.model';
import { PaymentService } from 'src/app/services/payment.service';
import { PlayersService } from 'src/app/services/players.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss'],
})
export class PaymentComponent {
  payments: Payment[] = [];
  newPayment: Payment = new Payment();
  updatedPayment: Payment = new Payment();
  paymentDetails: Payment = new Payment();
  players: Player[] = [];
  months: string[] = [];
  loaderErrorMsg: string = '';

  constructor(
    private paymentService: PaymentService,
    private playerService: PlayersService
  ) {}

  ngOnInit() {
    this.reloadPaymentsData();
    this.playerService.getPlayers().subscribe((players: Player[]) => {
      this.players = players;
    });
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

  getMonthStr(payment: Payment = this.updatedPayment) {
    return this.months[
      parseInt(this.payments[this.payments.indexOf(payment)].month) - 1
    ];
  }

  toggleSaveModal() {
    document.getElementById('new-payment-modal')?.classList.toggle('hidden');
  }

  saveNewPayment() {
    let btnSave = document.getElementById('btn-save-payment');
    let btnCancel = document.getElementById('btn-cancel-save-payment');
    btnSave?.classList.toggle('pointer-events-none');
    this.paymentService.savePayment(this.newPayment).subscribe({
      next: () => {
        this.transformDisableHideBtns(btnSave, btnCancel, null, 'bg-green-600');

        setTimeout(() => {
          Swal.fire({
            title: `¡Nuevo pago añadido!`,
            toast: true,
            position: 'top-end',
            width: 'max-content',
            icon: 'success',
            confirmButtonColor: '#34285a',
            showConfirmButton: false,
            timer: 2000,
          });

          this.toggleSaveModal();

          this.transformDisableHideBtns(
            btnSave,
            btnCancel,
            null,
            'bg-green-600'
          );
          btnSave?.classList.toggle('pointer-events-none');

          this.newPayment = new Payment();
          this.reloadPaymentsData();
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

  toggleUpdateModal() {
    document.getElementById('update-payment-modal')?.classList.toggle('hidden');
  }

  updatePayment() {
    let btnUpdate = document.getElementById('btn-update-payment');
    let btnCancel = document.getElementById('btn-cancel-update-payment');

    btnUpdate?.classList.toggle('pointer-events-none');
    this.transformDisableHideBtns(btnUpdate, btnCancel, null, 'bg-green-600');

    this.paymentService.updatePayment(this.updatedPayment).subscribe({
      next: () => {
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

          this.transformDisableHideBtns(
            btnUpdate,
            btnCancel,
            null,
            'bg-green-600'
          );
          btnUpdate?.classList.toggle('pointer-events-none');
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

  deletePayment() {
    let btnEdit = document.getElementById('btn-edit-payment');
    let btnCancel = document.getElementById('btn-cancel-update-payment');
    let btnDelete = document.getElementById('btn-delete-payment');
    let btnBack = document.getElementById('btn-details-back');

    btnDelete?.classList.toggle('pointer-events-none');
    btnBack?.classList.toggle('disabled');
    this.transformDisableHideBtns(btnDelete, btnCancel, btnEdit, 'bg-red-700');

    this.paymentService.deletePayment(this.updatedPayment.id).subscribe({
      next: () => {
        setTimeout(() => {
          Swal.fire({
            title: `¡Se eliminó el pago!`,
            toast: true,
            position: 'top-end',
            width: 'max-content',
            icon: 'success',
            showConfirmButton: false,
            timer: 2000,
          });

          this.transformDisableHideBtns(
            btnDelete,
            btnCancel,
            btnEdit,
            'bg-red-700'
          );
          btnBack?.classList.toggle('disabled');
          btnDelete?.classList.toggle('pointer-events-none');

          this.toggleDetails();

          this.updatedPayment = new Payment();
        }, 2000);
      },
      error: () => {
        Swal.fire({
          title: 'Error al eliminar',
          icon: 'error',
          text: 'Se ha producido un error al eliminar el pago. Parece que el pago ya no existe, pruebe a recargar la página. Consulte con el desarrollador si el problema persiste.',
          showConfirmButton: true,
          confirmButtonColor: '#34285a',
          allowOutsideClick: false,
          color: '#34285a',
          iconColor: '#dc2626',
        });
      },
    });
  }

  searchPayments() {
    let searchInput = <HTMLInputElement>document.getElementById('search-input');
    let aux = searchInput.value;

    if (aux.length < 1) {
      aux = 'empty';
    }

    this.paymentService.searchBy(aux).subscribe((payments: Payment[]) => {
      if (payments.length != 0) {
        this.payments = payments;
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
    this.reloadPaymentsData();
  }

  deleteAll() {}

  private reloadPaymentsData() {
    let wrapper = document.getElementById('payment-wrapper');
    let spinner = document.getElementById('payment-spinner');
    let notLoadedWrapper = document.getElementsByTagName('app-not-loaded')[0];
    this.paymentService.getPayments().subscribe({
      next: (payments: Payment[]) => {
        if (payments.length < 1) {
          this.loaderErrorMsg =
            'Parece que no existen pagos aún. ¡Es hora de formalizar un par de ellos!';
          wrapper?.classList.add('hidden');
          notLoadedWrapper?.classList.remove('hidden');
          spinner?.classList.add('hidden');
          document.getElementById('btn-reload')?.classList.add('hidden');
          document
            .getElementById('btn-show_payment_modal')
            ?.classList.remove('hidden');
        } else {
          this.payments = payments;
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
          .getElementById('btn-show_payment_modal')
          ?.classList.add('hidden');
      },
    });
  }

  toggleDetails(payment: Payment = new Payment()) {
    document.getElementById('payment-details')?.classList.toggle('hidden');
    document.getElementById('payment-wrapper')?.classList.toggle('hidden');
    this.updatedPayment = payment;

    if (parseInt(this.updatedPayment.month) < 10) {
      this.updatedPayment.month = '0' + this.updatedPayment.month;
    }

    if (payment.id == '') {
      this.reloadPaymentsData();
    }
  }

  deletePlayerFromPayment(playerToDelete: Player) {
    this.paymentService
      .deletePlayerFromPayment(playerToDelete.id, this.updatedPayment.id)
      .subscribe({
        next: () => {
          Swal.fire({
            icon: 'success',
            title: 'Jugador/a eliminado/a',
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timerProgressBar: true,
            timer: 1500,
          });

          this.updatedPayment.players = this.updatedPayment.players.filter(
            (player) => player != playerToDelete
          );
          this.getNotPaidList();
        },
        error: () => {
          Swal.fire({
            icon: 'error',
            title: 'Error al añadir el jugador',
          });
        },
      });
  }

  addPlayerToPayment(playerToAdd: Player) {
    this.paymentService
      .addPlayerToPayment(playerToAdd, this.updatedPayment.id)
      .subscribe({
        next: () => {
          Swal.fire({
            icon: 'success',
            title: 'Jugador/a añadido/a',
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timerProgressBar: true,
            timer: 1500,
          });

          this.updatedPayment.players.push(playerToAdd);
          this.getNotPaidList();
        },
        error: () => {
          Swal.fire({
            icon: 'error',
            title: 'No furulo',
          });
        },
      });
  }

  getNotPaidList() {
    let notPaidList: Player[] = [];

    notPaidList = this.players.filter(
      (player) =>
        !this.updatedPayment.players.some(
          (excludePlayer) => excludePlayer.id === player.id
        )
    );

    return notPaidList;
  }
}
