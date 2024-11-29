import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap} from 'rxjs';
import { environment } from '../components/environment/environment.dev';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly JWT_TOKEN = "JWT_TOKEN";
  private loggedUser: string;
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);

  private http = inject(HttpClient);
  constructor() { }

  login(user: {
    email:string, password:string 
  }): Observable<any> {
    return this.http.post(environment.apiUrl + "api/v1/auth/authenticate", user).pipe(
      tap((response: any) => this.doLoginUser(user.email, response.token))
    )
  }

  private doLoginUser(username:string, token:any){
    this.loggedUser = username;
    this.storeJwtToken(token);
    this.isAuthenticatedSubject.next(true);

  }

  private storeJwtToken(jwt: string){
    localStorage.setItem(this.JWT_TOKEN, jwt);
  }

  logout(){
    localStorage.removeItem(this.JWT_TOKEN);
    this.isAuthenticatedSubject.next(false);
  }
}
