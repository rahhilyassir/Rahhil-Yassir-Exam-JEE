package yassir.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yassir.enums.ContractStatus;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 10)
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class InsuranceContract {
    @Id
    private String id;
    private Date subscriptionDate;
    @Enumerated(EnumType.STRING)
    private ContractStatus status;
    private Date validationDate;
    private double contributionAmount;
    private int duration;
    private double coverageRate;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "contract", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Payment> payments;
}
