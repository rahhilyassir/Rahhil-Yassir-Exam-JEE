import { Component } from '@angular/core';
import { Customer } from '../model/customer.model';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html'
})
export class NewCustomerComponent {
  customer: Customer = { name: '', email: '' };
  mode = 'new';

  constructor(private customerService: CustomerService) {
  }

  saveCustomer() {
    this.customerService.saveCustomer(this.customer).subscribe(data => {
      this.customer = data;
      this.mode = 'confirm';
    });
  }

  newCustomer() {
    this.customer = { name: '', email: '' };
    this.mode = 'new';
  }
}
