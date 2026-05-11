import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Customer } from '../model/customer.model';
import { InsuranceContract } from '../model/contract.model';
import { CustomerService } from '../services/customer.service';
import { ContractService } from '../services/contract.service';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html'
})
export class CustomerDetailsComponent implements OnInit {
  customer?: Customer;
  contracts: InsuranceContract[] = [];

  constructor(private route: ActivatedRoute, private customerService: CustomerService, private contractService: ContractService) {
  }

  ngOnInit(): void {
    let id = +this.route.snapshot.params['id'];
    this.customerService.getCustomer(id).subscribe(data => this.customer = data);
    this.contractService.getContractsByCustomer(id).subscribe(data => this.contracts = data);
  }
}
