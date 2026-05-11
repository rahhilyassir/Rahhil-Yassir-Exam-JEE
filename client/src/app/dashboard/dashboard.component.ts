import { Component, OnInit } from '@angular/core';
import { Dashboard } from '../model/dashboard.model';
import { DashboardService } from '../services/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  dashboard?: Dashboard;

  constructor(private dashboardService: DashboardService) {
  }

  ngOnInit(): void {
    this.dashboardService.getDashboard().subscribe(data => this.dashboard = data);
  }
}
