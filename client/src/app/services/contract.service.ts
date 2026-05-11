import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { InsuranceContract } from '../model/contract.model';
import { ContractHistory, Payment } from '../model/payment.model';

@Injectable({ providedIn: 'root' })
export class ContractService {
  private host = 'http://localhost:8085';

  constructor(private http: HttpClient) {
  }

  getContracts() {
    return this.http.get<InsuranceContract[]>(this.host + '/contracts');
  }

  getContract(id: string) {
    return this.http.get<InsuranceContract>(this.host + '/contracts/' + id);
  }

  getContractsByCustomer(customerId: number) {
    return this.http.get<InsuranceContract[]>(this.host + '/contracts/customer/' + customerId);
  }

  saveCarContract(contract: any) {
    return this.http.post(this.host + '/contracts/car', contract);
  }

  saveHomeContract(contract: any) {
    return this.http.post(this.host + '/contracts/home', contract);
  }

  saveHealthContract(contract: any) {
    return this.http.post(this.host + '/contracts/health', contract);
  }

  validateContract(id: string) {
    return this.http.put(this.host + '/contracts/' + id + '/validate', {});
  }

  cancelContract(id: string) {
    return this.http.put(this.host + '/contracts/' + id + '/cancel', {});
  }

  deleteContract(id: string) {
    return this.http.delete(this.host + '/contracts/' + id);
  }

  addPayment(payment: any) {
    return this.http.post<Payment>(this.host + '/contracts/payments', payment);
  }

  getPayments(contractId: string) {
    return this.http.get<Payment[]>(this.host + '/contracts/' + contractId + '/payments');
  }

  getPagePayments(contractId: string, page: number, size: number) {
    return this.http.get<ContractHistory>(this.host + '/contracts/' + contractId + '/pagePayments?page=' + page + '&size=' + size);
  }
}
