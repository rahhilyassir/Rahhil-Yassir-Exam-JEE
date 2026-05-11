package yassir.mappers;

import yassir.dtos.CarInsuranceContractDTO;
import yassir.dtos.CustomerDTO;
import yassir.dtos.HealthInsuranceContractDTO;
import yassir.dtos.HomeInsuranceContractDTO;
import yassir.dtos.InsuranceContractDTO;
import yassir.dtos.PaymentDTO;
import yassir.entities.CarInsuranceContract;
import yassir.entities.Customer;
import yassir.entities.HealthInsuranceContract;
import yassir.entities.HomeInsuranceContract;
import yassir.entities.InsuranceContract;
import yassir.entities.Payment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class InsuranceMapperImpl {
    public CustomerDTO fromCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public CarInsuranceContractDTO fromCarContract(CarInsuranceContract contract) {
        CarInsuranceContractDTO dto = new CarInsuranceContractDTO();
        BeanUtils.copyProperties(contract, dto);
        dto.setCustomerDTO(fromCustomer(contract.getCustomer()));
        dto.setType(contract.getClass().getSimpleName());
        return dto;
    }

    public HomeInsuranceContractDTO fromHomeContract(HomeInsuranceContract contract) {
        HomeInsuranceContractDTO dto = new HomeInsuranceContractDTO();
        BeanUtils.copyProperties(contract, dto);
        dto.setCustomerDTO(fromCustomer(contract.getCustomer()));
        dto.setType(contract.getClass().getSimpleName());
        return dto;
    }

    public HealthInsuranceContractDTO fromHealthContract(HealthInsuranceContract contract) {
        HealthInsuranceContractDTO dto = new HealthInsuranceContractDTO();
        BeanUtils.copyProperties(contract, dto);
        dto.setCustomerDTO(fromCustomer(contract.getCustomer()));
        dto.setType(contract.getClass().getSimpleName());
        return dto;
    }

    public InsuranceContractDTO fromContract(InsuranceContract contract) {
        if (contract instanceof CarInsuranceContract) {
            return fromCarContract((CarInsuranceContract) contract);
        }
        if (contract instanceof HomeInsuranceContract) {
            return fromHomeContract((HomeInsuranceContract) contract);
        }
        return fromHealthContract((HealthInsuranceContract) contract);
    }

    public PaymentDTO fromPayment(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        BeanUtils.copyProperties(payment, paymentDTO);
        return paymentDTO;
    }
}
