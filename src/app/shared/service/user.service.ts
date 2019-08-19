import { UserPlotInfo } from './../model/userPlotInfo';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { User } from '../model/user';
import { settings } from '../util/settings';
import { HttpClient,HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { Subject } from 'rxjs/Subject';
@Injectable()
export class UserService {

  private loggedInUser = new Subject<User>();
    constructor(private http: Http, private httpClient: HttpClient) { }
    login(userName: string, password: string): Observable<User> {

      const httpOptions = {
        params: new HttpParams()
        .append("username", userName)
        .append("password", password)
      };
      // .append("rolename", "Admin");
      return this.httpClient.post<User>(settings.baseUrl + '/login',{},httpOptions) as Observable<User>;
        // return this.http.post(settings.baseUrl + '/login', {},  { params:{'username': userName, 'password': password }});
    }

    logout(username: string): any {
        console.log('Loging out');
        return this.http.get(settings.baseUrl + '/logout', { params: { 'username': username } });
    }

    findUsers() {
        return this.http.get(settings.baseUrl + '/listUsers');
    }
    register(user: User) {
        return this.http.post(settings.baseUrl + '/user/register', user, null);
    }
    public updateUserLoginSubject(user: User) {
      this.loggedInUser.next(user);
    }

    getloggedInUsero(): Observable<User> {
      return this.loggedInUser.asObservable();
    }

}
