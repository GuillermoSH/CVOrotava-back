import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Payment } from '../models/payment.model';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  //private url: string = 'http://192.168.1.27:9097/api/v1/payments';
  private url: string = 'http://localhost:9097/api/v1/payments';

  constructor(private _http: HttpClient) { }

  getPayments(): Observable<Payment[]> {
    return this._http.get<Payment[]>(this.url)
  }
}
