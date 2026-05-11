import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CustomersComponent } from './customers/customers.component';
import { NewCustomerComponent } from './new-customer/new-customer.component';
import { ContractsComponent } from './contracts/contracts.component';
import { NewContractComponent } from './new-contract/new-contract.component';
import { ContractDetailsComponent } from './contract-details/contract-details.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
  { path: 'customers', component: CustomersComponent, canActivate: [AuthGuard] },
  { path: 'customers/:id', component: CustomerDetailsComponent, canActivate: [AuthGuard] },
  { path: 'new-customer', component: NewCustomerComponent, canActivate: [AuthGuard] },
  { path: 'contracts', component: ContractsComponent, canActivate: [AuthGuard] },
  { path: 'contracts/:id', component: ContractDetailsComponent, canActivate: [AuthGuard] },
  { path: 'new-contract', component: NewContractComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
