import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, map} from 'rxjs';
import {FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _isLoggedIn$ = new BehaviorSubject<boolean>(false);
  isLoggedIn$ = this._isLoggedIn$.asObservable();


  constructor( private http : HttpClient) {
    try{
      // @ts-ignore
      const token = JSON.parse(localStorage.getItem('currentUser'));
      console.log(!!token);
      this._isLoggedIn$.next(!!token);
    }catch (e) {
      console.log(e);
      this._isLoggedIn$.next(false);
    }
  }

  login(form: FormGroup) {
    return this.http.post('/rest/login', form.value , {observe: "response"})
      .pipe(map(user => {
        this._isLoggedIn$.next(true);
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('currentUser', JSON.stringify(user.headers.get('authorization')?.split(' ')[1]));
        return;
    }));
  }

  logout(){
    localStorage.removeItem('currentUser');
    localStorage.removeItem('tempPoints');
  }


}
