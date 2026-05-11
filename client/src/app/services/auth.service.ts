import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private host = 'http://localhost:8085';

  constructor(private http: HttpClient, private router: Router) {
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post(this.host + '/auth/login', { username, password });
  }

  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  saveRoles(roles: string[]) {
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  getToken() {
    return localStorage.getItem('token');
  }

  getRoles(): string[] {
    let roles = localStorage.getItem('roles');
    return roles ? JSON.parse(roles) : [];
  }

  isAuthenticated() {
    return this.getToken() != null;
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('roles');
    this.router.navigateByUrl('/login');
  }
}
