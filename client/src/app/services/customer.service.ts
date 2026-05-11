import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Customer } from '../model/customer.model';

@Injectable({ providedIn: 'root' })
export class CustomerService {
  private host = 'http://localhost:8085';

  constructor(private http: HttpClient) {
  }

  getCustomers() {
    return this.http.get<Customer[]>(this.host + '/customers');
  }

  getCustomer(id: number) {
    return this.http.get<Customer>(this.host + '/customers/' + id);
  }

  searchCustomers(keyword: string) {
    return this.http.get<Customer[]>(this.host + '/customers/search?keyword=' + keyword);
  }

  saveCustomer(customer: Customer) {
    return this.http.post<Customer>(this.host + '/customers', customer);
  }

  deleteCustomer(id: number) {
    return this.http.delete(this.host + '/customers/' + id);
  }
}
