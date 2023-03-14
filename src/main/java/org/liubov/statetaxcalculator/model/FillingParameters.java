package org.liubov.statetaxcalculator.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class FillingParameters {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
}
