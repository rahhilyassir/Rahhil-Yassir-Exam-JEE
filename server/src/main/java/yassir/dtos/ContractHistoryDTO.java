package yassir.dtos;

import lombok.Data;
import yassir.enums.ContractStatus;

import java.util.List;

@Data
public class ContractHistoryDTO {
    private String contractId;
    private ContractStatus status;
    private double contributionAmount;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<PaymentDTO> paymentDTOS;
}
