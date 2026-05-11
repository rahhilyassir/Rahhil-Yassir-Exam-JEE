package yassir;

import yassir.dtos.CreateCarContractDTO;
import yassir.dtos.CreateHealthContractDTO;
import yassir.dtos.CreateHomeContractDTO;
import yassir.dtos.CustomerDTO;
import yassir.dtos.InsuranceContractDTO;
import yassir.dtos.PaymentRequestDTO;
import yassir.enums.CoverageLevel;
import yassir.enums.HousingType;
import yassir.enums.PaymentType;
import yassir.security.Role;
import yassir.security.UserEntity;
import yassir.security.UserRepository;
import yassir.services.InsuranceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(InsuranceService insuranceService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(new UserEntity(null, "admin", passwordEncoder.encode("1234"), Role.ROLE_ADMIN));
                userRepository.save(new UserEntity(null, "employe", passwordEncoder.encode("1234"), Role.ROLE_EMPLOYE));
                userRepository.save(new UserEntity(null, "client", passwordEncoder.encode("1234"), Role.ROLE_CLIENT));
            }
            Stream.of("Hassan", "Imane", "Mohamed", "Sara").forEach(name -> {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setName(name);
                customerDTO.setEmail(name.toLowerCase() + "@gmail.com");
                insuranceService.saveCustomer(customerDTO);
            });
            List<CustomerDTO> customers = insuranceService.listCustomers();
            for (CustomerDTO customer : customers) {
                CreateCarContractDTO car = new CreateCarContractDTO();
                car.setCustomerId(customer.getId());
                car.setContributionAmount(3500 + Math.random() * 4000);
                car.setCoverageRate(0.75);
                car.setDuration(12);
                car.setVehicleRegistrationNumber("A-" + customer.getId() + "-123");
                car.setVehicleBrand("Dacia");
                car.setVehicleModel("Logan");
                insuranceService.saveCarContract(car);
                CreateHomeContractDTO home = new CreateHomeContractDTO();
                home.setCustomerId(customer.getId());
                home.setContributionAmount(2500 + Math.random() * 3000);
                home.setCoverageRate(0.65);
                home.setDuration(12);
                home.setHousingType(HousingType.APPARTEMENT);
                home.setHousingAddress("Casablanca");
                home.setSurface(90 + Math.random() * 40);
                insuranceService.saveHomeContract(home);
                CreateHealthContractDTO health = new CreateHealthContractDTO();
                health.setCustomerId(customer.getId());
                health.setContributionAmount(4500 + Math.random() * 5000);
                health.setCoverageRate(0.8);
                health.setDuration(12);
                health.setCoverageLevel(CoverageLevel.INTERMEDIAIRE);
                health.setCoveredPersons(3);
                insuranceService.saveHealthContract(health);
            }
            for (InsuranceContractDTO contract : insuranceService.contractList()) {
                for (int i = 0; i < 4; i++) {
                    PaymentRequestDTO payment = new PaymentRequestDTO();
                    payment.setContractId(contract.getId());
                    payment.setAmount(500 + Math.random() * 1500);
                    payment.setType(i % 2 == 0 ? PaymentType.MENSUALITE : PaymentType.PAIEMENT_EXCEPTIONNEL);
                    payment.setDescription("Payment");
                    insuranceService.addPayment(payment);
                }
            }
        };
    }
}
