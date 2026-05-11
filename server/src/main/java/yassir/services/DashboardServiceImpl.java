package yassir.services;

import lombok.AllArgsConstructor;
import yassir.dtos.DashboardDTO;
import yassir.entities.CarInsuranceContract;
import yassir.entities.HealthInsuranceContract;
import yassir.entities.HomeInsuranceContract;
import yassir.mappers.InsuranceMapperImpl;
import yassir.repositories.CustomerRepository;
import yassir.repositories.InsuranceContractRepository;
import yassir.repositories.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private CustomerRepository customerRepository;
    private InsuranceContractRepository contractRepository;
    private PaymentRepository paymentRepository;
    private InsuranceMapperImpl dtoMapper;

    @Override
    public DashboardDTO getDashboard() {
        DashboardDTO dashboardDTO = new DashboardDTO();
        dashboardDTO.setTotalCustomers(customerRepository.count());
        dashboardDTO.setTotalContracts(contractRepository.count());
        dashboardDTO.setTotalPayments(paymentRepository.count());
        dashboardDTO.setTotalCarContracts(contractRepository.findAll().stream().filter(contract -> contract instanceof CarInsuranceContract).count());
        dashboardDTO.setTotalHomeContracts(contractRepository.findAll().stream().filter(contract -> contract instanceof HomeInsuranceContract).count());
        dashboardDTO.setTotalHealthContracts(contractRepository.findAll().stream().filter(contract -> contract instanceof HealthInsuranceContract).count());
        dashboardDTO.setTotalPaymentAmount(paymentRepository.findAll().stream().mapToDouble(payment -> payment.getAmount()).sum());
        dashboardDTO.setRecentCustomers(customerRepository.findAll().stream().limit(5).map(customer -> dtoMapper.fromCustomer(customer)).collect(Collectors.toList()));
        dashboardDTO.setRecentContracts(contractRepository.findAll().stream().limit(5).map(contract -> dtoMapper.fromContract(contract)).collect(Collectors.toList()));
        dashboardDTO.setRecentPayments(paymentRepository.findAll().stream().limit(5).map(payment -> dtoMapper.fromPayment(payment)).collect(Collectors.toList()));
        return dashboardDTO;
    }
}
