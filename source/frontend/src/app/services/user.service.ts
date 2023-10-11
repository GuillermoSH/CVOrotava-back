import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  //private url: string = 'http://192.168.1.27:9097/api/v1/users';
  //private url: string = 'http://localhost:9097/api/v1/users';
  private url: string = 'https://zm220cwj-9097.euw.devtunnels.ms/api/v1/users';
  // private url: string = 'https://c24djzb4-9097.uks1.devtunnels.ms/api/v1/users';

  constructor(private _http: HttpClient) { }

  getUserByUsername(username: string): Observable<User> {
    return this._http.get<User>(this.url + "/" + username);
  }

  validateLogin(user: User): Observable<any> {
    return this._http.post<User>(this.url + "/login", user);
  }
}
