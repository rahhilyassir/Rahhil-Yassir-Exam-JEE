import { ContractStatus } from './contract.model';

export type PaymentType = 'MENSUALITE' | 'PAIEMENT_ANNUEL' | 'PAIEMENT_EXCEPTIONNEL';

export interface Payment {
  id?: number;
  paymentDate?: Date;
  amount: number;
  type: PaymentType;
  description: string;
}

export interface ContractHistory {
  contractId: string;
  status: ContractStatus;
  contributionAmount: number;
  currentPage: number;
  totalPages: number;
  pageSize: number;
  paymentDTOS: Payment[];
}
