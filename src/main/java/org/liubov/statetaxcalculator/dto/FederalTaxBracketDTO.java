package org.liubov.statetaxcalculator.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FederalTaxBracketDTO {
    private Integer id;
    private Integer year;
    private String fillingStatus;
    private Integer bracketLower;
    private Integer bracketUpper;
    private Double accumulatedAmount;
    private Double rate;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
