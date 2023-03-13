package org.liubov.statetaxcalculator.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class FillingParameters {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Setter(value = AccessLevel.PRIVATE)
    private Integer id;
    private Integer userId;
    @Column(name = "my_year")
    private Integer year;
    private String state;
    private String fillingStatus;
    private Integer income;
    private Double stateTaxAmount;
    private Double federalTaxAmount;
    private Double ficaTaxAmount;
    private Double additionalMedicareTaxAmount;
    private Double totalTaxAmount;
    private Double effectiveTaxRate;
}
