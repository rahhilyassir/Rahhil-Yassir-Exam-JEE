import { Component } from '@angular/core';
import { ContractService } from '../services/contract.service';

@Component({
  selector: 'app-new-contract',
  templateUrl: './new-contract.component.html'
})
export class NewContractComponent {
  contractType = 'CAR';
  contract: any = {
    customerId: 1,
    contributionAmount: 3000,
    duration: 12,
    coverageRate: 0.7,
    vehicleRegistrationNumber: '',
    vehicleBrand: '',
    vehicleModel: '',
    housingType: 'APPARTEMENT',
    housingAddress: '',
    surface: 80,
    coverageLevel: 'BASIQUE',
    coveredPersons: 1
  };
  mode = 'new';
  savedContract: any;

  constructor(private contractService: ContractService) {
  }

  saveContract() {
    if (this.contractType == 'CAR') {
      this.contractService.saveCarContract(this.contract).subscribe(data => this.confirm(data));
    } else if (this.contractType == 'HOME') {
      this.contractService.saveHomeContract(this.contract).subscribe(data => this.confirm(data));
    } else {
      this.contractService.saveHealthContract(this.contract).subscribe(data => this.confirm(data));
    }
  }

  confirm(data: any) {
    this.savedContract = data;
    this.mode = 'confirm';
  }
}
