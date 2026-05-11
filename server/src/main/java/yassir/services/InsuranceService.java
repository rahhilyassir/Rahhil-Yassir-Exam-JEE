package yassir.services;

import yassir.dtos.CarInsuranceContractDTO;
import yassir.dtos.ContractHistoryDTO;
import yassir.dtos.CreateCarContractDTO;
import yassir.dtos.CreateHealthContractDTO;
import yassir.dtos.CreateHomeContractDTO;
import yassir.dtos.CustomerDTO;
import yassir.dtos.HealthInsuranceContractDTO;
import yassir.dtos.HomeInsuranceContractDTO;
import yassir.dtos.InsuranceContractDTO;
import yassir.dtos.PaymentDTO;
import yassir.dtos.PaymentRequestDTO;
import yassir.exceptions.ContractNotFoundException;
import yassir.exceptions.CustomerNotFoundException;

import java.util.List;

public interface InsuranceService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> listCustomers();
    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long customerId);
    List<CustomerDTO> searchCustomers(String keyword);
    CarInsuranceContractDTO saveCarContract(CreateCarContractDTO dto) throws CustomerNotFoundException;
    HomeInsuranceContractDTO saveHomeContract(CreateHomeContractDTO dto) throws CustomerNotFoundException;
    HealthInsuranceContractDTO saveHealthContract(CreateHealthContractDTO dto) throws CustomerNotFoundException;
    List<InsuranceContractDTO> contractList();
    InsuranceContractDTO getContract(String contractId) throws ContractNotFoundException;
    List<InsuranceContractDTO> getContractsByCustomer(Long customerId) throws CustomerNotFoundException;
    InsuranceContractDTO validateContract(String contractId) throws ContractNotFoundException;
    InsuranceContractDTO cancelContract(String contractId) throws ContractNotFoundException;
    void deleteContract(String contractId);
    PaymentDTO addPayment(PaymentRequestDTO dto) throws ContractNotFoundException;
    List<PaymentDTO> paymentHistory(String contractId);
    ContractHistoryDTO getContractHistory(String contractId, int page, int size) throws ContractNotFoundException;
}
