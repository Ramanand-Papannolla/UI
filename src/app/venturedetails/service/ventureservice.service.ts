import { settings } from './../../shared/util/settings';
import { Injectable } from '@angular/core';
import { VentureDtls } from '../model/venture-dtls';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
@Injectable()
export class VentureService {

  constructor(private http: HttpClient) {
  }

  getVentureDetails(userId: number): Observable<VentureDtls[]> {
    return this.http.get<VentureDtls[]>(
      settings.baseUrl + `/area/venture/user/${userId}`) as Observable<VentureDtls[]>;
  }
}
