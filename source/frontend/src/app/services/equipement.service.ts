import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Equipement } from '../models/equipement.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EquipementService {
  //private url: string = 'http://192.168.1.27:9097/api/v1/equipements';
  //private url: string = 'http://localhost:9097/api/v1/equipements';
  //private url: string = 'https://zm220cwj-9097.euw.devtunnels.ms/api/v1/equipements';
  private url: string = 'https://c24djzb4-9097.uks1.devtunnels.ms/api/v1/equipements';

  constructor(private _http: HttpClient) { }

  getEquipements(): Observable<Equipement[]> {
    return this._http.get<Equipement[]>(this.url)
  }

  getEquipementById(id: string): Observable<Equipement> {
    return this._http.get<Equipement>(this.url + "/" + id)
  }

  saveEquipement(equipement: Equipement): Observable<Equipement> {
    return this._http.post<Equipement>(this.url, equipement);
  }

  addPlayerToEquipement(player: Equipement, equipement_id: string): Observable<Equipement> {
    return this._http.put<Equipement>(this.url + "/" + equipement_id + "/add/player", player);
  }

  updateEquipement(equipement: Equipement): Observable<Equipement> {
    return this._http.put<Equipement>(this.url, equipement);
  }

  deleteEquipement(id: string): Observable<Equipement> {
    return this._http.delete<Equipement>(this.url + "/" + Number(id));
  }

  deletePlayerFromEquipement(player_id: string, equipement_id: string): Observable<Equipement> {
    return this._http.delete<Equipement>(this.url + "/" + equipement_id + "/delete/player/" + player_id);
  }

  searchBy(search: string): Observable<Equipement[]> {
    return this._http.get<Equipement[]>(this.url + "/search/" + search);
  }
}
