import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { InsuranceContract } from '../model/contract.model';
import { ContractHistory } from '../model/payment.model';
import { ContractService } from '../services/contract.service';

@Component({
  selector: 'app-contract-details',
  templateUrl: './contract-details.component.html'
})
export class ContractDetailsComponent implements OnInit {
  contractId = '';
  contract?: InsuranceContract;
  history?: ContractHistory;
  page = 0;
  size = 5;
  payment: any = { amount: 1000, type: 'MENSUALITE', description: 'Payment' };
  pages: number[] = [];

  constructor(private route: ActivatedRoute, private contractService: ContractService) {
  }

  ngOnInit(): void {
    this.contractId = this.route.snapshot.params['id'];
    this.loadContract();
    this.loadHistory();
  }

  loadContract() {
    this.contractService.getContract(this.contractId).subscribe(data => this.contract = data);
  }

  loadHistory() {
    this.contractService.getPagePayments(this.contractId, this.page, this.size).subscribe(data => {
      this.history = data;
      this.pages = Array.from({length: data.totalPages}, (v, k) => k);
    });
  }

  addPayment() {
    this.payment.contractId = this.contractId;
    this.contractService.addPayment(this.payment).subscribe(() => this.loadHistory());
  }

  gotoPage(i: number) {
    this.page = i;
    this.loadHistory();
  }
}
