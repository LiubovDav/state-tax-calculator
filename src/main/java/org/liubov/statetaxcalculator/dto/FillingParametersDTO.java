package org.liubov.statetaxcalculator.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FillingParametersDTO {
    private Integer id;
    private Integer userId;
    @NotEmpty(message = "Year should be selected")
    private String year;
    @NotEmpty(message = "State should be selected")
    private String state;
    @NotEmpty(message = "Filling Status should be selected")
    private String fillingStatus;
    @NotEmpty(message = "Income should not be empty")
    private String income;
    private Double stateTaxAmount;
    private Double federalTaxAmount;
    private Double ficaTaxAmount;
    private Double additionalMedicareTaxAmount;
    private Double totalTaxAmount;
    private Double effectiveTaxRate;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
