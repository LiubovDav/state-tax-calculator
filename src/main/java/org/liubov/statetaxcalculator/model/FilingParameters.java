package org.liubov.statetaxcalculator.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class FilingParameters {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PRIVATE)
    private Integer id;
    private Integer userId;
    @Column(name = "my_year")
    private Integer year;
    private String state;
    private String filingStatus;
    private BigDecimal income;
    private BigDecimal stateTaxAmount;
    private BigDecimal federalTaxAmount;
    private BigDecimal ficaTaxAmount;
    private BigDecimal additionalMedicareTaxAmount;
    private BigDecimal totalTaxAmount;
    private BigDecimal effectiveTaxRate;
    private BigDecimal afterTaxAmount;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
}
