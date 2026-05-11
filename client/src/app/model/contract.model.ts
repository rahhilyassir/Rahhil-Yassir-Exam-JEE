import { Customer } from './customer.model';

export type ContractStatus = 'EN_COURS' | 'VALIDE' | 'RESILIE';
export type HousingType = 'APPARTEMENT' | 'MAISON' | 'LOCAL_COMMERCIAL';
export type CoverageLevel = 'BASIQUE' | 'INTERMEDIAIRE' | 'PREMIUM';

export interface InsuranceContract {
  id?: string;
  type?: string;
  subscriptionDate?: Date;
  status?: ContractStatus;
  validationDate?: Date;
  contributionAmount: number;
  duration: number;
  coverageRate: number;
  customerDTO?: Customer;
}

export interface CarInsuranceContract extends InsuranceContract {
  vehicleRegistrationNumber: string;
  vehicleBrand: string;
  vehicleModel: string;
}

export interface HomeInsuranceContract extends InsuranceContract {
  housingType: HousingType;
  housingAddress: string;
  surface: number;
}

export interface HealthInsuranceContract extends InsuranceContract {
  coverageLevel: CoverageLevel;
  coveredPersons: number;
}
