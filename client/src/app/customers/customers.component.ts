import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../model/customer.model';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html'
})
export class CustomersComponent implements OnInit {
  customers: Customer[] = [];
  keyword = '';

  constructor(private customerService: CustomerService, private router: Router) {
  }

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers() {
    this.customerService.getCustomers().subscribe(data => this.customers = data);
  }

  search() {
    this.customerService.searchCustomers(this.keyword).subscribe(data => this.customers = data);
  }

  details(customer: Customer) {
    this.router.navigateByUrl('/customers/' + customer.id);
  }

  delete(customer: Customer) {
    if (confirm('Delete customer?')) {
      this.customerService.deleteCustomer(customer.id!).subscribe(() => this.loadCustomers());
    }
  }
}
