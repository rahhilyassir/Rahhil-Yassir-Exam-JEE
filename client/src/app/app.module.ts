import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CustomersComponent } from './customers/customers.component';
import { ContractsComponent } from './contracts/contracts.component';
import { NewCustomerComponent } from './new-customer/new-customer.component';
import { NewContractComponent } from './new-contract/new-contract.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ContractDetailsComponent } from './contract-details/contract-details.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { PaymentsComponent } from './payments/payments.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    CustomersComponent,
    ContractsComponent,
    NewCustomerComponent,
    NewContractComponent,
    NavbarComponent,
    ContractDetailsComponent,
    CustomerDetailsComponent,
    PaymentsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
