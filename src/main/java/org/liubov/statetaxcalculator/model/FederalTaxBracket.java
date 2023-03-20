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
public class FederalTaxBracket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PRIVATE)
    private Integer id;
    @Column(name = "my_year", nullable = false)
    private Integer year;
    @Column(nullable = false)
    private String filingStatus;
    @Column(nullable = false)
    private BigDecimal bracketLower;
    @Column(nullable = false)
    private BigDecimal bracketUpper;
    @Column(nullable = false)
    private BigDecimal accumulatedAmount;
    @Column(nullable = false)
    private BigDecimal rate;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
}
