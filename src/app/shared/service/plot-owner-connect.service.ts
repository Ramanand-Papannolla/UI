import { Survey } from "./../model/survey";
import { Village } from "./../model/village";
import { Mandal } from "./../model/mandal";
import { UserPlotInfo } from "./../model/userPlotInfo";
import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { settings } from "../util/settings";
import { District } from "./../model/district";
import { State } from "./../model/state";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Plot } from "../model/plot";

@Injectable()
export class PlotOwnerConnectService {
  constructor(private http: HttpClient) {}
  getStates(): Observable<State[]> {
    return this.http.get<State[]>(
      settings.baseUrl + "/area/states"
    ) as Observable<State[]>;
  }
  getDistricts(stateId: number): Observable<District[]> {
    return this.http.get<District[]>(
      settings.baseUrl + `/area/districts/${stateId}`
    ) as Observable<District[]>;
  }

  getMandals(districtId: number): Observable<Mandal[]> {
    return this.http.get<Mandal[]>(
      settings.baseUrl + `/area/mandals/${districtId}`
    ) as Observable<Mandal[]>;
  }
  getVillages(mandalId: number): Observable<Village[]> {
    return this.http.get<Village[]>(
      settings.baseUrl + `/area/villages/${mandalId}`
    ) as Observable<Village[]>;
  }
  getSurveys(villageId: number): Observable<Survey[]> {
    return this.http.get<Survey[]>(
      settings.baseUrl + `/area/surveys/${villageId}`
    ) as Observable<Survey[]>;
  }
  getPlots(surveyId: number): Observable<Plot[]> {
    return this.http.get<Plot[]>(
      settings.baseUrl + `/area/plots/${surveyId}`
    ) as Observable<Plot[]>;
  }

  save(details: UserPlotInfo): Observable<UserPlotInfo> {
    return this.http.post<UserPlotInfo>(
      settings.baseUrl + "/area/plotDetails",
      details
    ) as Observable<UserPlotInfo>;
  }

  findUsersBySurveryId(surveyId:string):Observable<UserPlotInfo[]> {
    return this.http.get<UserPlotInfo[]>(settings.baseUrl + `/area/listUsers/survey/${surveyId}`)  as Observable<UserPlotInfo[]>;;
}

}
