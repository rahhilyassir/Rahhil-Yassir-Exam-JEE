package yassir.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import yassir.entities.CarInsuranceContract;
import yassir.entities.Customer;
import yassir.entities.HealthInsuranceContract;
import yassir.entities.HomeInsuranceContract;
import yassir.entities.InsuranceContract;
import yassir.entities.Payment;
import yassir.enums.ContractStatus;
import yassir.exceptions.ContractNotFoundException;
import yassir.exceptions.CustomerNotFoundException;
import yassir.mappers.InsuranceMapperImpl;
import yassir.repositories.CustomerRepository;
import yassir.repositories.InsuranceContractRepository;
import yassir.repositories.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class InsuranceServiceImpl implements InsuranceService {
    private CustomerRepository customerRepository;
    private InsuranceContractRepository contractRepository;
    private PaymentRepository paymentRepository;
    private InsuranceMapperImpl dtoMapper;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("Saving customer");
        Customer customer = dtoMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return dtoMapper.fromCustomer(savedCustomer);
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> dtoMapper.fromCustomer(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return dtoMapper.fromCustomer(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer = dtoMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return dtoMapper.fromCustomer(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        return customerRepository.findByNameContainsIgnoreCase(keyword).stream().map(customer -> dtoMapper.fromCustomer(customer)).collect(Collectors.toList());
    }

    @Override
    public CarInsuranceContractDTO saveCarContract(CreateCarContractDTO dto) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        CarInsuranceContract contract = new CarInsuranceContract();
        contract.setId(UUID.randomUUID().toString());
        contract.setSubscriptionDate(new Date());
        contract.setStatus(ContractStatus.EN_COURS);
        contract.setContributionAmount(dto.getContributionAmount());
        contract.setDuration(dto.getDuration());
        contract.setCoverageRate(dto.getCoverageRate());
        contract.setCustomer(customer);
        contract.setVehicleRegistrationNumber(dto.getVehicleRegistrationNumber());
        contract.setVehicleBrand(dto.getVehicleBrand());
        contract.setVehicleModel(dto.getVehicleModel());
        CarInsuranceContract savedContract = contractRepository.save(contract);
        return dtoMapper.fromCarContract(savedContract);
    }

    @Override
    public HomeInsuranceContractDTO saveHomeContract(CreateHomeContractDTO dto) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        HomeInsuranceContract contract = new HomeInsuranceContract();
        contract.setId(UUID.randomUUID().toString());
        contract.setSubscriptionDate(new Date());
        contract.setStatus(ContractStatus.EN_COURS);
        contract.setContributionAmount(dto.getContributionAmount());
        contract.setDuration(dto.getDuration());
        contract.setCoverageRate(dto.getCoverageRate());
        contract.setCustomer(customer);
        contract.setHousingType(dto.getHousingType());
        contract.setHousingAddress(dto.getHousingAddress());
        contract.setSurface(dto.getSurface());
        HomeInsuranceContract savedContract = contractRepository.save(contract);
        return dtoMapper.fromHomeContract(savedContract);
    }

    @Override
    public HealthInsuranceContractDTO saveHealthContract(CreateHealthContractDTO dto) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        HealthInsuranceContract contract = new HealthInsuranceContract();
        contract.setId(UUID.randomUUID().toString());
        contract.setSubscriptionDate(new Date());
        contract.setStatus(ContractStatus.EN_COURS);
        contract.setContributionAmount(dto.getContributionAmount());
        contract.setDuration(dto.getDuration());
        contract.setCoverageRate(dto.getCoverageRate());
        contract.setCustomer(customer);
        contract.setCoverageLevel(dto.getCoverageLevel());
        contract.setCoveredPersons(dto.getCoveredPersons());
        HealthInsuranceContract savedContract = contractRepository.save(contract);
        return dtoMapper.fromHealthContract(savedContract);
    }

    @Override
    public List<InsuranceContractDTO> contractList() {
        return contractRepository.findAll().stream().map(contract -> dtoMapper.fromContract(contract)).collect(Collectors.toList());
    }

    @Override
    public InsuranceContractDTO getContract(String contractId) throws ContractNotFoundException {
        InsuranceContract contract = contractRepository.findById(contractId).orElseThrow(() -> new ContractNotFoundException("Contract not found"));
        return dtoMapper.fromContract(contract);
    }

    @Override
    public List<InsuranceContractDTO> getContractsByCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return contractRepository.findByCustomerId(customer.getId()).stream().map(contract -> dtoMapper.fromContract(contract)).collect(Collectors.toList());
    }

    @Override
    public InsuranceContractDTO validateContract(String contractId) throws ContractNotFoundException {
        InsuranceContract contract = contractRepository.findById(contractId).orElseThrow(() -> new ContractNotFoundException("Contract not found"));
        contract.setStatus(ContractStatus.VALIDE);
        contract.setValidationDate(new Date());
        InsuranceContract savedContract = contractRepository.save(contract);
        return dtoMapper.fromContract(savedContract);
    }

    @Override
    public InsuranceContractDTO cancelContract(String contractId) throws ContractNotFoundException {
        InsuranceContract contract = contractRepository.findById(contractId).orElseThrow(() -> new ContractNotFoundException("Contract not found"));
        contract.setStatus(ContractStatus.RESILIE);
        InsuranceContract savedContract = contractRepository.save(contract);
        return dtoMapper.fromContract(savedContract);
    }

    @Override
    public void deleteContract(String contractId) {
        contractRepository.deleteById(contractId);
    }

    @Override
    public PaymentDTO addPayment(PaymentRequestDTO dto) throws ContractNotFoundException {
        InsuranceContract contract = contractRepository.findById(dto.getContractId()).orElseThrow(() -> new ContractNotFoundException("Contract not found"));
        Payment payment = new Payment();
        payment.setPaymentDate(new Date());
        payment.setAmount(dto.getAmount());
        payment.setType(dto.getType());
        payment.setDescription(dto.getDescription());
        payment.setContract(contract);
        Payment savedPayment = paymentRepository.save(payment);
        return dtoMapper.fromPayment(savedPayment);
    }

    @Override
    public List<PaymentDTO> paymentHistory(String contractId) {
        return paymentRepository.findByContractId(contractId).stream().map(payment -> dtoMapper.fromPayment(payment)).collect(Collectors.toList());
    }

    @Override
    public ContractHistoryDTO getContractHistory(String contractId, int page, int size) throws ContractNotFoundException {
        InsuranceContract contract = contractRepository.findById(contractId).orElseThrow(() -> new ContractNotFoundException("Contract not found"));
        Page<Payment> payments = paymentRepository.findByContractId(contractId, PageRequest.of(page, size));
        ContractHistoryDTO historyDTO = new ContractHistoryDTO();
        historyDTO.setPaymentDTOS(payments.getContent().stream().map(payment -> dtoMapper.fromPayment(payment)).collect(Collectors.toList()));
        historyDTO.setContractId(contract.getId());
        historyDTO.setStatus(contract.getStatus());
        historyDTO.setContributionAmount(contract.getContributionAmount());
        historyDTO.setCurrentPage(page);
        historyDTO.setPageSize(size);
        historyDTO.setTotalPages(payments.getTotalPages());
        return historyDTO;
    }
}
