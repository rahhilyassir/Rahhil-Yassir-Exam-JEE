package yassir.dtos;

import lombok.Data;
import yassir.enums.PaymentType;

import java.util.Date;

@Data
public class PaymentDTO {
    private Long id;
    private Date paymentDate;
    private double amount;
    private PaymentType type;
    private String description;
}
