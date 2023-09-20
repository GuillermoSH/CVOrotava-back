import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Payment } from '../models/payment.model';
import { Player } from '../models/player.model';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  //private url: string = 'http://192.168.1.27:9097/api/v1/payments';
  //private url: string = 'http://localhost:9097/api/v1/payments';
  private url: string = 'https://zm220cwj-9097.euw.devtunnels.ms/api/v1/payments';
  //private url: string = 'https://c24djzb4-9097.uks1.devtunnels.ms/api/v1/payments';

  constructor(private _http: HttpClient) { }

  getPayments(): Observable<Payment[]> {
    return this._http.get<Payment[]>(this.url)
  }

  getPaymentById(id: string): Observable<Payment> {
    return this._http.get<Payment>(this.url + "/" + id)
  }

  savePayment(payment: Payment): Observable<Payment> {
    return this._http.post<Payment>(this.url, payment);
  }

  addPlayerToPayment(player: Player, payment_id: string): Observable<Payment> {
    return this._http.put<Payment>(this.url + "/" + payment_id + "/add/player", player);
  }

  updatePayment(payment: Payment): Observable<Payment> {
    return this._http.put<Payment>(this.url, payment);
  }

  deletePayment(id: string): Observable<Payment> {
    return this._http.delete<Payment>(this.url + "/" + Number(id));
  }

  deletePlayerFromPayment(player_id: string, payment_id: string): Observable<Payment> {
    return this._http.delete<Payment>(this.url + "/" + payment_id + "/delete/player/" + player_id);
  }

  searchBy(search: string): Observable<Payment[]> {
    return this._http.get<Payment[]>(this.url + "/search/" + search);
  }
}
