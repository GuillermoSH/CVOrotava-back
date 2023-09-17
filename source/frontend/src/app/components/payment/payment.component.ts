import { Component } from '@angular/core';
import { Payment } from 'src/app/models/payment.model';
import { Player } from 'src/app/models/player.model';
import { PaymentService } from 'src/app/services/payment.service';
import { PlayersService } from 'src/app/services/players.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent {
  payments: Payment[] = [];
  newPayment: Payment = new Payment();
  updatedPayment: Payment = new Payment();
  players: Player[] = [];
  months: string[] = [];
  loaderErrorMsg: string = "";

  constructor(private paymentService: PaymentService, private playerService: PlayersService) { }

  ngOnInit() {
    this.reloadPaymentsData();
    this.playerService.getPlayers().subscribe((players: Player[]) => { this.players = players })
    this.months = ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"];
  }

  getMonthStr(payment: Payment) {
    return this.months[parseInt(this.payments[this.payments.indexOf(payment)].month) - 1]
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
        btnSave?.children[0].classList.toggle('hiddenplus');
        btnSave?.children[1].classList.toggle('hiddenplus');
        btnSave?.children[2].classList.toggle('hiddenplus');
        btnSave?.children[3].classList.toggle('hiddenplus');
        btnSave?.classList.toggle('bg-green-600');
        btnCancel?.classList.toggle('hidden');
        if (btnSave) {
          btnSave.children[0].innerHTML = 'Guardando...';
        }

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

          btnSave?.children[0].classList.toggle('hiddenplus');
          btnSave?.children[1].classList.toggle('hiddenplus');
          btnSave?.children[2].classList.toggle('hiddenplus');
          btnSave?.children[3].classList.toggle('hiddenplus');
          btnSave?.classList.toggle('bg-green-600');
          btnSave?.classList.toggle('pointer-events-none');
          btnCancel?.classList.toggle('hidden');
          if (btnSave) {
            btnSave.children[0].innerHTML = 'Guardar';
          }

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

  toggleUpdateModal(payment: Payment = new Payment()) {
    this.updatedPayment = payment;
    document.getElementById('update-payment-modal')?.classList.toggle('hidden');
  }

  updatePayment() {
    let btnUpdate = document.getElementById('btn-update-payment');
    let btnCancel = document.getElementById('btn-cancel-update-payment');
    let btnDelete = document.getElementById('btn-delete-payment');
    btnUpdate?.classList.toggle('pointer-events-none');
    this.paymentService.updatePayment(this.updatedPayment).subscribe({
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

          this.toggleUpdateModal();

          btnUpdate?.children[0].classList.toggle('hiddenplus');
          btnUpdate?.children[1].classList.toggle('hiddenplus');
          btnUpdate?.children[2].classList.toggle('hiddenplus');
          btnUpdate?.children[3].classList.toggle('hiddenplus');
          btnCancel?.classList.toggle('hidden');
          btnUpdate?.classList.toggle('pointer-events-none');
          btnUpdate?.classList.toggle('bg-green-600');
          btnDelete?.classList.toggle('disabled');

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

  deletePayment() {
    let btnUpdate = document.getElementById('btn-update-payment');
    let btnCancel = document.getElementById('btn-cancel-update-payment');
    let btnDelete = document.getElementById('btn-delete-payment');
    btnDelete?.classList.toggle('pointer-events-none');
    this.paymentService.deletePayment(this.updatedPayment.id).subscribe({
      next: () => {
        btnDelete?.children[0].classList.toggle('hiddenplus');
        btnDelete?.children[1].classList.toggle('hiddenplus');
        btnDelete?.children[2].classList.toggle('hiddenplus');
        btnDelete?.children[3].classList.toggle('hiddenplus');
        btnCancel?.classList.toggle('hidden');
        btnUpdate?.classList.toggle('disabled');

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

          this.toggleUpdateModal();

          btnDelete?.children[0].classList.toggle('hiddenplus');
          btnDelete?.children[1].classList.toggle('hiddenplus');
          btnDelete?.children[2].classList.toggle('hiddenplus');
          btnDelete?.children[3].classList.toggle('hiddenplus');
          btnCancel?.classList.toggle('hidden');
          btnUpdate?.classList.toggle('disabled');
          btnDelete?.classList.toggle('pointer-events-none');

          this.reloadPaymentsData();

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
      aux = "empty";
    }

    this.paymentService
      .searchBy(aux)
      .subscribe((payments: Payment[]) => {
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

  deleteAll() {

  }

  private reloadPaymentsData() {
    let wrapper = document.getElementById("payment-wrapper");
    let spinner = document.getElementById("payment-spinner");
    let notLoadedWrapper = document.getElementsByTagName("app-not-loaded")[0];
    this.paymentService.getPayments().subscribe({
      next: (payments: Payment[]) => {
        if (payments.length < 1) {
          this.loaderErrorMsg = "Parece que no existen pagos aún. ¡Es hora de formalizar un par de ellos!";
          wrapper?.classList.add("hidden");
          notLoadedWrapper?.classList.remove("hidden");
          spinner?.classList.add("hidden");
          document.getElementById("btn-reload")?.classList.add("hidden");
          document.getElementById("btn-show_payment_modal")?.classList.remove("hidden");
        } else {
          this.payments = payments;
          spinner?.classList.add("hidden");
          wrapper?.classList.remove("hidden");
          notLoadedWrapper?.classList.add("hidden");
        }
      },
      error: () => {
        this.loaderErrorMsg = "Parece que no se han podido cargar los datos, recarga la página y si no se soluciona contacte con un técnico.";
        wrapper?.classList.add("hidden");
        notLoadedWrapper?.classList.remove("hidden");
        spinner?.classList.add("hidden");
        document.getElementById("btn-reload")?.classList.remove("hidden");
        document.getElementById("btn-show_payment_modal")?.classList.add("hidden");
      }
    });
  }
}
