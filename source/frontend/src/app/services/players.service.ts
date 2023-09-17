import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Player } from '../models/player.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlayersService {
  //private url: string = 'http://192.168.1.27:9097/api/v1/players';
  //private url: string = 'http://localhost:9097/api/v1/players';
  private url: string = 'https://c24djzb4-9097.uks1.devtunnels.ms/api/v1/players';

  constructor(private _http: HttpClient) { }

  getPlayers(): Observable<Player[]> {
    return this._http.get<Player[]>(this.url);
  }

  getPlayersOrderBy(order: string): Observable<Player[]> {
    return this._http.get<Player[]>(this.url + "/orderedBy/" + order);
  }

  getPlayersByCategory(category: string): Observable<Player[]> {
    return this._http.get<Player[]>(this.url + `/${category}`);
  }

  getPlayerById(id: string): Observable<Player> {
    return this._http.get<Player>(this.url + `/${id}`);
  }

  getTotalNumPlayers(): Observable<string[]> {
    return this._http.get<string[]>(this.url + "/total");
  }

  savePlayer(player: Player): Observable<Player> {
    return this._http.post<Player>(this.url, player);
  }

  updatePlayer(player: Player): Observable<Player> {
    return this._http.put<Player>(this.url, player);
  }

  deletePlayer(id: string): Observable<Player> {
    return this._http.delete<Player>(this.url + `/${id}`);
  }

  deleteAll() {
    return this._http.delete(this.url);
  }

  searchBy(search: string): Observable<Player[]> {
    return this._http.get<Player[]>(this.url + "/search/" + search);
  }
}
