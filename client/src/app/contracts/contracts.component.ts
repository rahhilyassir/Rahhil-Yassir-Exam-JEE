import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InsuranceContract } from '../model/contract.model';
import { ContractService } from '../services/contract.service';

@Component({
  selector: 'app-contracts',
  templateUrl: './contracts.component.html'
})
export class ContractsComponent implements OnInit {
  contracts: InsuranceContract[] = [];

  constructor(private contractService: ContractService, private router: Router) {
  }

  ngOnInit(): void {
    this.loadContracts();
  }

  loadContracts() {
    this.contractService.getContracts().subscribe(data => this.contracts = data);
  }

  details(contract: InsuranceContract) {
    this.router.navigateByUrl('/contracts/' + contract.id);
  }

  validate(contract: InsuranceContract) {
    this.contractService.validateContract(contract.id!).subscribe(() => this.loadContracts());
  }

  cancel(contract: InsuranceContract) {
    this.contractService.cancelContract(contract.id!).subscribe(() => this.loadContracts());
  }

  delete(contract: InsuranceContract) {
    if (confirm('Delete contract?')) {
      this.contractService.deleteContract(contract.id!).subscribe(() => this.loadContracts());
    }
  }
}
