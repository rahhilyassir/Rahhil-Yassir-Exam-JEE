package yassir.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DashboardDTO {
    private long totalCustomers;
    private long totalContracts;
    private long totalCarContracts;
    private long totalHomeContracts;
    private long totalHealthContracts;
    private long totalPayments;
    private double totalPaymentAmount;
    private List<CustomerDTO> recentCustomers;
    private List<InsuranceContractDTO> recentContracts;
    private List<PaymentDTO> recentPayments;
}
