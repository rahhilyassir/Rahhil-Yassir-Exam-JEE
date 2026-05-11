import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Dashboard } from '../model/dashboard.model';

@Injectable({ providedIn: 'root' })
export class DashboardService {
  private host = 'http://localhost:8085';

  constructor(private http: HttpClient) {
  }

  getDashboard() {
    return this.http.get<Dashboard>(this.host + '/dashboard');
  }
}
