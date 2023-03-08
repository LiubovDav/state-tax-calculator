package org.liubov.statetaxcalculator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
@Entity
@Data
public class TaxRate {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Setter(value = AccessLevel.PRIVATE)
    private Integer id;
    private Integer year;
    private String state;
    private String fillingStatus;
    private Integer bracket;
    private Double rate;
}
