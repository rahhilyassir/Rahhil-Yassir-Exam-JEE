package yassir.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import yassir.enums.HousingType;

@Entity
@DiscriminatorValue("HOME")
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper = true)
public class HomeInsuranceContract extends InsuranceContract {
    @Enumerated(EnumType.STRING)
    private HousingType housingType;
    private String housingAddress;
    private double surface;
}
