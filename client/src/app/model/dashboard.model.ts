import { Customer } from './customer.model';
import { InsuranceContract } from './contract.model';
import { Payment } from './payment.model';

export interface Dashboard {
  totalCustomers: number;
  totalContracts: number;
  totalCarContracts: number;
  totalHomeContracts: number;
  totalHealthContracts: number;
  totalPayments: number;
  totalPaymentAmount: number;
  recentCustomers: Customer[];
  recentContracts: InsuranceContract[];
  recentPayments: Payment[];
}
