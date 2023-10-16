import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Configuration } from '../models/configuration.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {
  //private url: string = 'http://192.168.1.27:9097/api/v1/config';
  //private url: string = 'http://localhost:9097/api/v1/config';
  private url: string = 'https://zm220cwj-9097.euw.devtunnels.ms/api/v1/config';
  // private url: string = 'https://c24djzb4-9097.uks1.devtunnels.ms/api/v1/config';

  constructor(private _http: HttpClient) { }

  getConfiguration(): Observable<Configuration[]> {
    return this._http.get<Configuration[]>(this.url);
  }

  saveConfiguration(configuration: Configuration): Observable<Configuration> {
    return this._http.post<Configuration>(this.url, configuration);
  }

  deleteConfiguration(): Observable<Configuration> {
    return this._http.delete<Configuration>(this.url);
  }
}
