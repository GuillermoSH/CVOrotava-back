import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Player } from '../models/player.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlayersService {
  private url: string = 'http://localhost:9097/api/v1/players';

  constructor(private _http: HttpClient) { }

  getPlayers(): Observable<Player[]> {
    return this._http.get<Player[]>(this.url);
  }

  getPlayersByCategory(category: string): Observable<Player[]> {
    return this._http.get<Player[]>(this.url + `/${category}`);
  }

  getPlayerById(id: number): Observable<Player> {
    return this._http.get<Player>(this.url + `/${id}`);
  }

  saveUpdatePlayer(player: Player): Observable<Player> {
    return this._http.post<Player>(this.url, player);
  }

  deletePlayer(id: number): Observable<Player> {
    return this._http.delete<Player>(this.url + `/${id}`);
  }
}
