package org.liubov.statetaxcalculator.dto;

import lombok.Data;

@Data
public class StateTaxBracketDTO {
    private Integer id;
    private Integer year;
    private String state;
    private String fillingStatus;
    private Integer bracketLower;
    private Integer bracketUpper;
    private Double accumulatedAmount;
    private Double rate;
}
