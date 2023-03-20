package org.liubov.statetaxcalculator.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilingParametersDTO {
    private Integer id;
    private Integer userId;
    @NotEmpty(message = "Year should be selected")
    private String year;
    @NotEmpty(message = "State should be selected")
    private String state;
    @NotEmpty(message = "Filing Status should be selected")
    private String filingStatus;
    @NotEmpty(message = "Income should not be empty")
    private String income;
    private String stateTaxAmount;
    private String federalTaxAmount;
    private String ficaTaxAmount;
    private String additionalMedicareTaxAmount;
    private String totalTaxAmount;
    private String effectiveTaxRate;
    private String afterTaxAmount;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
